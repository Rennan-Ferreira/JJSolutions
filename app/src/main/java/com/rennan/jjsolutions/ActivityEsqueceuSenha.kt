package com.rennan.jjsolutions

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.rennan.jjsolutions.databinding.ActivityEsqueceuSenhaBinding

class EsqueceuSenhaActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityEsqueceuSenhaBinding.inflate(layoutInflater)
    }
    private val autenticacao by lazy{
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_esqueceu_senha)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnEnviarEmail.setOnClickListener{
            esqueceuSenha()
        }
        binding.imgVoltarSenha.setOnClickListener{
            voltarMain()
        }

    }
    private fun voltarMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun esqueceuSenha(){
        val email = binding.editTextEsqueceuSenha.text.toString()
        autenticacao.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                AlertDialog.Builder(this)
                    .setTitle("Sucesso")
                    .setMessage("Enviado e-mail de redefinição")
                    .setNegativeButton("OK"){dialog,posicao->}
                    .create()
                    .show()
            }
            .addOnFailureListener{
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Não foi possível enviar e-mail de redefinição")
                    .setNegativeButton("OK"){dialog,posicao->}
                    .create()
                    .show()
            }
    }
}