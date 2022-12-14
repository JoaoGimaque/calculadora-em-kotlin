package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var num1 = 0.0
    private var num2 = 0.0
    private var operacao = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resultadoText.text = "0"
        operacao = SEM_OPERACAO

        binding.umBtn.setOnClickListener { numberPressed("1") }
        binding.doisBtn.setOnClickListener { numberPressed("2") }
        binding.tresBtn.setOnClickListener { numberPressed("3") }
        binding.quatroBtn.setOnClickListener { numberPressed("4") }
        binding.cincoBtn.setOnClickListener { numberPressed("5") }
        binding.seisBtn.setOnClickListener { numberPressed("6") }
        binding.seteBtn.setOnClickListener { numberPressed("7") }
        binding.oitoBtn.setOnClickListener { numberPressed("8") }
        binding.noveBtn.setOnClickListener { numberPressed("9") }
        binding.zeroBtn.setOnClickListener { numberPressed("0") }
        binding.pontoBtn.setOnClickListener { numberPressed(".") }

        binding.limparBtn.setOnClickListener { limparTudo() }

        binding.somaBtn.setOnClickListener { operaracaoPressed(SOMA) }
        binding.restoBtn.setOnClickListener { operaracaoPressed(RESTO) }
        binding.multiplicarBtn.setOnClickListener { operaracaoPressed(MULT) }
        binding.divisaoBtn.setOnClickListener { operaracaoPressed(DIVISAO) }

        binding.igualBtn.setOnClickListener { resolvePressed() }
    }

    private fun numberPressed(num: String){
        if(binding.resultadoText.text == "0" && num != ".") {
            binding.resultadoText.text = "$num"
        } else {
            binding.resultadoText.text = "${binding.resultadoText.text}$num"
        }

        if(operacao == SEM_OPERACAO){
            num1 = binding.resultadoText.text.toString().toDouble()
        } else {
            num2 = binding.resultadoText.text.toString().toDouble()
        }
    }

    private fun operaracaoPressed(operacion: Int){
        this.operacao = operacion
        num1 = binding.resultadoText.text.toString().toDouble()

        binding.resultadoText.text = "0"
    }

    private fun resolvePressed(){

        val result = when(operacao) {
            SOMA -> num1 + num2
            RESTO -> num1 - num2
            MULT -> num1 * num2
            DIVISAO -> num1 / num2
            else -> 0
        }

        num1 = result as Double

        binding.resultadoText.text = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }
    }

    private fun limparTudo(){
        binding.resultadoText.text = "0"
        num1 = 0.0
        num2 = 0.0
    }

    companion object {
        const val SOMA = 1
        const val RESTO = 2
        const val MULT = 3
        const val DIVISAO = 4
        const val SEM_OPERACAO = 0
    }
}