package org.hls.check

import com.cra.figaro.language.{Flip, Select}
import com.cra.figaro.library.compound.If
import com.cra.figaro.algorithm.factored.VariableElimination


object HelloWorld {
    val sunnyToday = Flip(0.2)
    val greetingToday = If(sunnyToday,
        Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
        Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again"))
    val sunnyTomorrow = If(sunnyToday, Flip(0.8), Flip(0.05))
    val greetingTomorrow = If(sunnyTomorrow,
        Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
        Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again"))

    def predict() {
        val result = VariableElimination.probability(greetingToday,
            "Hello, world!")
        println("Today’s greeting is \"Hello, world!\" " +
            "with probability " + result + ".")
    }

    def infer() {
        greetingToday.observe("Hello, world!")
        val result = VariableElimination.probability(sunnyToday, true)
        println("If today's greeting is \"Hello, world!\", today’s " +
            "weather is sunny with probability " + result + ".")
    }

    def learnAndPredict() {
        greetingToday.observe("Hello, world!")
        val result = VariableElimination.probability(greetingTomorrow,
            "Hello, world!")
        println("If today's greeting is \"Hello, world!\", " +
            "tomorrow's greeting will be \"Hello, world!\" " +
            "with probability " + result + ".")
    }

    def main(args: Array[String]) {
        predict()
        infer()
        learnAndPredict()
    }
}
