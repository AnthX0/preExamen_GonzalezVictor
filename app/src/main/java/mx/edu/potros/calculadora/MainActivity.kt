package mx.edu.potros.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var resultTextView: TextView? = null
    private val numberBuilder = StringBuilder()
    private var firstNumber = 0
    private var secondNumber = 0
    private var operator = 0.toChar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTextView = findViewById(R.id.resultTextView)
        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        addButton.setOnClickListener(this)
        subtractButton.setOnClickListener(this)
        multiplyButton.setOnClickListener(this)
        divideButton.setOnClickListener(this)
        calculateButton.setOnClickListener(this)
        clearButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val buttonText = (v as Button).text.toString()
        val buttonChar = buttonText[0]
        if (Character.isDigit(buttonChar)) {
            numberBuilder.append(buttonChar)
            updateResultTextView()
        } else if (buttonChar == 'C') {
            clearCalculator()
        } else if (buttonChar == '+' || buttonChar == '-' || buttonChar == '*' || buttonChar == '/') {
            if (numberBuilder.length > 0) {
                firstNumber = numberBuilder.toString().toInt()
                numberBuilder.setLength(0)
                operator = buttonChar
            }
        } else if (buttonChar == '=') {
            if (numberBuilder.length > 0) {
                secondNumber = numberBuilder.toString().toInt()
                val result = calculateResult()
                resultTextView!!.text = "Resultado: $result"
                numberBuilder.setLength(0)
            }
        }
    }

    private fun updateResultTextView() {
        resultTextView!!.text = "Número: $numberBuilder"
    }

    private fun clearCalculator() {
        numberBuilder.setLength(0)
        resultTextView!!.text = "0"
    }

    private fun calculateResult(): Int {
        var result = 0
        when (operator) {
            '+' -> result = firstNumber + secondNumber
            '-' -> result = firstNumber - secondNumber
            '*' -> result = firstNumber * secondNumber
            '/' -> if (secondNumber != 0) {
                result = firstNumber / secondNumber
            } else {
                resultTextView!!.text = "Error: Division por cero"
            }
        }
        return result
    }
}