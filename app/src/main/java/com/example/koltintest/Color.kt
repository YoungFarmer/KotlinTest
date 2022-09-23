package com.example.koltintest

sealed class Color {
    class Red(val value: Int) : Color()
    class Green(val value: Int) : Color()
    class Blue(val name: String) : Color()
}

fun isInstance(color: Color) {
    when (color){
        is Color.Blue -> TODO()
        is Color.Green -> TODO()
        is Color.Red -> TODO()
    }
}