package com.example.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        criarLista()
    }

    private fun criarLista(){
        val alunos: MutableList<String> = mutableListOf( "Robson", "Fernanda", "Isaque" )
        val tv_alunos: ListView = findViewById(R.id.lista_alunos)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos)
        tv_alunos.adapter = adapter
    }

}