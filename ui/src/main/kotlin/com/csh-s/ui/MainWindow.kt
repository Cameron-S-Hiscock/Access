package com.csh_s.ui

import com.csh_s.utils.Constants
import com.csh_s.utils.Id
import java.util.UUID

import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.SwingUtilities
import java.io.File
import com.csh_s.ui.Composer

class MainWindow(): JFrame("Access") {
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT)
        layout = BorderLayout()

        add(Composer.browser.uiComponent, BorderLayout.CENTER)

        addWindowListener(object: java.awt.event.WindowAdapter() {
            override fun windowClosing(e: java.awt.event.WindowEvent) {
                Composer.shutdown()
            }
        })
    }
}
