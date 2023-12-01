package com.example.calculator

class CalculatorControl {

    private var result: Double = 0.0
    private var input1 : Int = 0
    private var input2 : Int = 0
    private var inputOperator : String = ""

    fun Start() {
        println("숫자를 입력하세요...")
        print("입력 : ")
        input1 = readLine()!!.toInt()

        println("연산자를 입력하세요...")
        print("입력 : ")
        inputOperator = readLine()!!

        println("숫자를 입력하세요...")
        print("입력 : ")
        input2 = readLine()!!.toInt()

        OperatorStart(inputOperator, input1, input2)
    }

    fun ContinueOperation() {
        println("현재 값 : $result")
        println("연산을 끝내려면 '='를 입력해주세요.")
        println("연산을 계속 하려면 연산자를 입력하세요.")
        print("입력 : ")
        inputOperator = readLine()!!

        if (inputOperator == "=") println("결과 값은 $result 입니다.")
        else {
            println("숫자를 입력하세요...")
            print("입력 : ")
            input1 = readLine()!!.toInt()

            OperatorStart(inputOperator, result.toInt(), input1)
        }
    }

    fun OperatorStart(input : String, num1 : Int, num2 : Int) {
        when(input) {
            "+" -> {
                result = Calculator(AddOperator()).Operate(num1, num2)
                ContinueOperation()
            }
            "-" -> {
                result = Calculator(SubtractOperation()).Operate(num1, num2)
                ContinueOperation()
            }
            "*" -> {
                result = Calculator(MultiplyOperation()).Operate(num1, num2)
                ContinueOperation()
            }
            "/" -> {
                result = Calculator(DivideOperation()).Operate(num1, num2)
                ContinueOperation()
            }
            "%" -> {
                result = Calculator(RemainderOperation()).Operate(num1, num2)
                ContinueOperation()
            }
        }
    }
}