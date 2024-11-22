package com.rennan.jjsolutions

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.rennan.jjsolutions.databinding.ActivityCadastroBinding

class ActivityCadastro : AppCompatActivity() {class CadastrarActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnCadastrar.setOnClickListener {
            cadastroUsuario()
        }

        binding.imgVoltarCadastro.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun cadastroUsuario() {
        val email = binding.editTextEmailCadastrar.text.toString()
        val senha = binding.editTextSenhaCadastrar.text.toString()

        autenticacao.createUserWithEmailAndPassword(email, senha)
            .addOnSuccessListener { authResult ->
                val id = authResult.user?.uid
                val email = authResult.user?.email

                AlertDialog.Builder(this)
                    .setTitle("Usuario Criado")
                    .setMessage("Usuario Criado com sucesso")
                    .setPositiveButton("OK") { dialog, posicao ->
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    .create()
                    .show()
            }.addOnFailureListener { exception ->
                val mensagemErro = exception.message

                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Error ao criar usuario $mensagemErro")
                    .setNegativeButton("OK") { dialog, posicao -> }
                    .create()
                    .show()
            }
    }
}
}