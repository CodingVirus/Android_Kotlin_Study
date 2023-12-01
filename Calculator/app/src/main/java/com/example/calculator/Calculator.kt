package com.example.calculator

class Calculator(var test: AbstractOperation) {
    fun Operate(num1: Int, num2: Int): Double {
        return test.Operate(num1, num2)
    }
}
