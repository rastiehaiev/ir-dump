package io.github.rastiehaiev

fun main() {
    println("Hello World!")

    Example().doSomething()
    println(AnotherExample)
}

class Example {

    @IrDump
    fun doSomething() {
    }
}

@IrDump
object AnotherExample
