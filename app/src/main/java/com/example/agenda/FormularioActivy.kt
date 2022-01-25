package com.example.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class FormularioActivy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_activy)
        botaoSalvar()
    }

    private fun botaoSalvar(){
        val btn_salvar: Button = findViewById(R.id.formulario_btn_salvar)
        btn_salvar.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                this@FormularioActivy,
                "Botão Clicado",
                Toast.LENGTH_SHORT
            ).show() })
    }
}