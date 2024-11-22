package com.rennan.jjsolutions

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rennan.jjsolutions.databinding.ActivityHomeBinding
import com.rennan.jjsolutions.databinding.ActivityTabelaEletromesticosBinding

class ActivityHome : AppCompatActivity() {

    private val binding by lazy{
        ActivityHomeBinding.inflate(layoutInflater)
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

        binding.imgHome.setOnClickListener{
            startActivity(Intent(this, ActivityHome::class.java))
        }
        binding.imgViewTabela.setOnClickListener{
            startActivity(Intent(this, ActivityTabelaEletromesticosBinding::class.java))
        }
        binding.imgCadastrarTabela.setOnClickListener{
            startActivity(Intent(this, ActivityCadastrarEletrodomestico::class.java))
        }
    }
}