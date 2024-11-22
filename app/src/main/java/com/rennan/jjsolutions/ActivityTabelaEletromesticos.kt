package com.rennan.jjsolutions

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.rennan.jjsolutions.databinding.ActivityTabelaEletromesticosBinding

class ActivityInserirDados : AppCompatActivity() {

    private val binding by lazy {
        ActivityTabelaEletromesticosBinding.inflate(layoutInflater)
    }
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Botão para salvar os dados
        binding.btnSalvar.setOnClickListener {
            val nome = binding.editNomeEletrodomestico.text.toString()
            val consumo = binding.editConsumo.text.toString()
            val horasUso = binding.editHorasUso.text.toString()

            if (nome.isNotEmpty() && consumo.isNotEmpty() && horasUso.isNotEmpty()) {
                salvarDados(nome, consumo.toDouble(), horasUso.toInt())
            } else {
                Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarDados(nome: String, consumo: Double, horasUso: Int) {
        val dado = hashMapOf(
            "nome" to nome,
            "consumo" to consumo,
            "horasUso" to horasUso,
            "custoMensal" to calcularCustoMensal(consumo, horasUso)
        )

        db.collection("eletrodomesticos")
            .add(dado)
            .addOnSuccessListener {
                Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
                finish() // Fecha a tela após salvar
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao salvar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun calcularCustoMensal(consumo: Double, horasUso: Int): Double {
        val tarifa = 0.50 // Exemplo de tarifa por kWh
        return consumo * horasUso * 30 * tarifa
    }
}
