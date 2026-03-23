package com.messenger.sirium

import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.skia.Image
import java.io.File

fun main() = application {
    val iconPainter = try {
        val iconFile = File("src/commonMain/composeResources/drawable/ic_linux.png")
        if (iconFile.exists()) {
            val bitmap = Image.makeFromEncoded(iconFile.readBytes()).toComposeImageBitmap()
            BitmapPainter(bitmap)
        } else {
            null
        }
    } catch (e: Exception) {
        println("Ошибка загрузки иконки: ${e.message}")
        null
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "Sirium",
        icon = iconPainter
    ) {
        App()
    }
}