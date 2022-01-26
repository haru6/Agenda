package com.example.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.Aluno

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adicionarAluno()
    }

    override fun onResume() {
        super.onResume()
        criarLista()
    }

    private fun criarLista(){
        val alunoDAO: AlunoDAO =  AlunoDAO(this)
        val alunos = alunoDAO.buscarTodosAlunos()
        alunoDAO.close()
        val tv_alunos: ListView = findViewById(R.id.lista_alunos)
        val adapter: ArrayAdapter<Aluno> = ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos)
        tv_alunos.adapter = adapter
    }

    private fun adicionarAluno(){
        val btn_aluno: Button = findViewById(R.id.btn_add)
        btn_aluno.setOnClickListener(View.OnClickListener {
            val intentVaiProFormulario : Intent = Intent(this@MainActivity, FormularioActivy::class.java)
            startActivity(intentVaiProFormulario)
        })
    }

}