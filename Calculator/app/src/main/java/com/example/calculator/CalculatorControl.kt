package com.example.calculator

class CalculatorControl {

    private var result: Double = 0.0
    private var input1 : Int = 0
    private var input2 : Int = 0
    private var inputOperator : String = ""

    fun Start() {

        input1 = InputNum()
        InputOperator()
        input2 = InputNum()

        OperatorStart(inputOperator, input1, input2)
    }

    fun InputOperator()  {
        inputOperator = ""

        println("연산자를 입력하세요...(+, -, *, /, %)")
        print("입력 : ")
        inputOperator = readLine()!!

        if (inputOperator != "+" && inputOperator != "-" && inputOperator != "*" && inputOperator != "/" && inputOperator != "%" && inputOperator != "=") {
            println("연산자가 아닙니다!!")
            this.InputOperator()
        }
    }

    fun InputNum() : Int {
        var num = 0
        try {
            println("숫자를 입력하세요...")
            print("입력 : ")
            num = readLine()!!.toInt()
        } catch (e:java.lang.NumberFormatException) {
            println("숫자가 아닙니다!!")
            this.InputNum()
        }
        return num
    }

    fun ContinueOperation() {
        println("현재 값 : $result")
        println("연산을 끝내려면 '='를 입력해주세요.")
        print("연산을 계속 하려면 ")
        InputOperator()

        if (inputOperator == "=") println("결과 값은 $result 입니다.")
        else {
            input1 = InputNum()
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