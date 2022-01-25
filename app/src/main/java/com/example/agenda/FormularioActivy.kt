package com.example.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.zip.Inflater

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
                Toast.LENGTH_SHORT,
            ).show()
        finish()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_formulario, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_formulario_ok ->
                Toast.makeText(this@FormularioActivy, "Aluno Salvo", Toast.LENGTH_SHORT).show()
        }
        finish()
        return super.onOptionsItemSelected(item)
    }
}