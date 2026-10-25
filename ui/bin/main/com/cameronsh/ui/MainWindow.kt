package com.cameronsh.ui

import com.cameronsh.utils.Constants
import com.cameronsh.utils.Id
import java.util.UUID

import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.SwingUtilities
import java.io.File

class MainWindow(private val composer: Composer): JFrame("Access") {
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT)
        layout = BorderLayout()

        add(composer.browser.uiComponent, BorderLayout.CENTER)

        addWindowListener(object: java.awt.event.WindowAdapter() {
            override fun windowClosing(e: java.awt.event.WindowEvent) {
                composer.shutdown()
            }
        })
    }
}
