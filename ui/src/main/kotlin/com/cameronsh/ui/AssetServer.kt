package com.cameronsh.ui

import com.cameronsh.utils.Id
import java.util.UUID

import java.io.File
import com.sun.net.httpserver.HttpsServer
import com.sun.net.httpserver.HttpsConfigurator
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpExchange
import java.net.InetSocketAddress
import java.io.ByteArrayInputStream
import java.security.KeyFactory
import java.security.KeyStore
import java.security.spec.PKCS8EncodedKeySpec
import java.security.cert.CertificateFactory
import java.util.Base64
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext

class AssetServer() {
    val id: UUID = Id.genId(this)
    private val server: HttpsServer = HttpsServer.create(InetSocketAddress("localhost", 0), 0)
    val port: Int get() = server.address.port

    init {
        server.httpsConfigurator = HttpsConfigurator(buildSslContext())
        server.createContext("/", AssetHandler(javaClass.classLoader))
        server.executor = null
    }

    private fun buildSslContext(): SSLContext {
        fun pem(path: String) = javaClass.getResourceAsStream(path)!!.readBytes()
            .decodeToString()
            .replace(Regex("-----[A-Z ]+-----|\\s"), "")
            .let { Base64.getDecoder().decode(it) }

        val key = KeyFactory.getInstance("EC")
            .generatePrivate(PKCS8EncodedKeySpec(pem("/access-local.key")))
        val cert = CertificateFactory.getInstance("X.509")
            .generateCertificate(ByteArrayInputStream(
                javaClass.getResourceAsStream("/access-local.crt")!!.readBytes()))

        val ks = KeyStore.getInstance("PKCS12").apply {
            load(null, null)
            setKeyEntry("access-local", key, charArrayOf(), arrayOf(cert))
        }
        val kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
            .apply { init(ks, charArrayOf()) }
        return SSLContext.getInstance("TLS").apply { init(kmf.keyManagers, null, null) }
    }

    fun start() = server.start()
    fun stop() = server.stop(0)
}

private class AssetHandler(private val classLoader: ClassLoader): HttpHandler {
    override fun handle(exchange: HttpExchange) {
        val requested = if(exchange.requestURI.path == "/") "/index.html" else exchange.requestURI.path
        val resourcePath = "web-dist$requested"

        val stream = classLoader.getResourceAsStream(resourcePath)
        if(stream == null) {
            val msg = "404 Not Found".toByteArray()
            exchange.sendResponseHeaders(404, msg.size.toLong())
            exchange.responseBody.use { it.write(msg) }
            return
        }
        val mime = when(requested.substringAfterLast('.', "")) {
            "html" -> "text/html"
            "css" -> "text/css"
            "js" -> "application/javascript"
            "wasm" -> "application/wasm"
            "json" -> "application/json"
            else -> "application/octet-stream"
        }

        val bytes = stream.readBytes()
        exchange.responseHeaders.set("Content-Type", mime)
        exchange.sendResponseHeaders(200, bytes.size.toLong())
        exchange.responseBody.use { it.write(bytes) }
    }
}
