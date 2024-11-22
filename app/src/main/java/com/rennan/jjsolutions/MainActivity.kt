package com.rennan.jjsolutions

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.rennan.jjsolutions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    class LogarActivity : AppCompatActivity() {

        private val binding by lazy{
            ActivityMainBinding.inflate(layoutInflater)
        }

        private val autenticacao by lazy{
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
            binding.btnLogar.setOnClickListener{
                logarUsario()
            }
            binding.textViewEsqueceuSenha.setOnClickListener{
                startActivity(Intent(this, EsqueceuSenhaActivity::class.java))
            }
            binding.textViewCadastrese.setOnClickListener{
                startActivity(Intent(this, ActivityCadastro::class.java))
            }
        }
        private fun logarUsario(){
            val email  = binding.editTextEmailLogar.text.toString()
            val senha = binding.EmailLogin.text.toString()

            autenticacao.signInWithEmailAndPassword(email,senha)
                .addOnSuccessListener { authResult->
                    startActivity(Intent(this,MainActivity::class.java))
                }
                .addOnFailureListener{exception->
                    AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("Verificar e-mail e senha digitados..")
                        .setNegativeButton("OK"){dialog,posicao->}
                        .create()
                        .show()
                }
        }
    }
}