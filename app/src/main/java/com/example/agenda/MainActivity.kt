package com.example.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        criarLista()
        adicionarAluno()
    }

    private fun criarLista(){
        val alunos: MutableList<String> = mutableListOf( "Robson", "Fernanda", "Isaque" )
        val tv_alunos: ListView = findViewById(R.id.lista_alunos)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos)
        tv_alunos.adapter = adapter
    }

    private fun adicionarAluno(){
        val btn_aluno: Button = findViewById(R.id.btn_add)
        btn_aluno.setOnClickListener(View.OnClickListener {
            val intentVaiProFormulario: Intent = Intent(this@MainActivity, FormularioActivy::class.java)
            startActivity(intentVaiProFormulario)
        })
    }

}