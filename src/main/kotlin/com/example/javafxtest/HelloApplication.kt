package com.example.javafxtest

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import java.lang.System.gc


class HelloApplication : Application() {
    override fun start(stage: Stage) {
        /*
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()*/

        val canvas = Canvas()
        canvas.width = 512.0
        canvas.height = 512.0

        val graphicsContext = canvas.graphicsContext2D

        graphicsContext.fill = Color.BLUEVIOLET
        graphicsContext.fillRoundRect(100.0, 100.0, 100.0, 100.0, 20.0, 10.0)

        val vbox = VBox(canvas)
        val scene = Scene(vbox)

        stage.scene = scene
        stage.show()
        var x = 200.0
        var y = 200.0

        val keyMap = mutableMapOf<KeyCode, Boolean>()
        scene.setOnKeyPressed {
            keyMap[it.code] = true
        }

        scene.setOnKeyReleased {
            keyMap[it.code] = false
        }

        val startNanoTime = System.nanoTime()
        object : AnimationTimer() {
            override fun handle(currentNanoTime: Long) {
                val t: Double = (currentNanoTime - startNanoTime) / 1000000000.0

                if (keyMap[KeyCode.UP] == true) {
                    y--
                }
                if (keyMap[KeyCode.DOWN] == true) {
                    y++
                }
                if (keyMap[KeyCode.LEFT] == true) {
                    x--
                }
                if (keyMap[KeyCode.RIGHT] == true) {
                    x++
                }


                graphicsContext.clearRect(0.0, 0.0, 512.0, 512.0)
                // background image clears canvas
                graphicsContext.fillRoundRect(x, y, 100.0, 100.0, 20.0, 10.0)
            }
        }.start()

    }
}

class Key(val code: KeyCode, var isPressed: Boolean)

fun main() {
    Application.launch(HelloApplication::class.java)
}