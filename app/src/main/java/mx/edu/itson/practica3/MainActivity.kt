package mx.edu.itson.practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random
import java.util.*


class MainActivity : AppCompatActivity() {
    var minValue = 0
    var maxValue = 100
    var num:Int=0
    var won=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessing: TextView= findViewById(R.id.guessings)
        val down: Button = findViewById(R.id.down)
        val up:Button = findViewById(R.id.up)
        val generate:Button = findViewById(R.id.generate)
        val guessed:Button = findViewById(R.id.guessed)

        down.isEnabled = false
        up.isEnabled = false

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessing.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
            down.isEnabled = true
            up.isEnabled = true
        }

        up.setOnClickListener {
            minValue = num
            if(checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessing.setText(num.toString())

            } else {
                guessing.setText("No puede ser, ganaste")
                guessed.setText("Volver a jugar")
                won = false
            }
        }

        down.setOnClickListener {
            maxValue = num
            if(checkingLimits()) {
                num = Random.nextInt(minValue , maxValue)
                guessing.setText(num.toString())

            } else {
                guessing.setText("No puede ser, ganaste")
                guessed.setText("Volver a jugar")
                won = false
            }
        }

        guessed.setOnClickListener {
            if(!won && guessing.text != "No puede ser, ganaste") {
                guessing.setText("Adiviné tú número es el "+ num)
                guessed.setText("Volver a jugar")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                guessing.setText("Tap on generate to start")
                guessed.setText("guessed")
                guessed.visibility=View.GONE
                down.isEnabled = false
                up.isEnabled = false
                resetValues()
            }
        }

    }

    fun resetValues(){
        minValue=0
        maxValue=100
        num=0
        won=false

    }

    fun checkingLimits(): Boolean {
        return minValue!=maxValue
    }
}