package com.example.calculator

class MultiplyOperation: AbstractOperation() {
    override fun Operate(num1: Int, num2: Int): Double {
        return (num1 * num2).toDouble()
    }
}