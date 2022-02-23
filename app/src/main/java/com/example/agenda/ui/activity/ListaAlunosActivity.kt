package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.Aluno
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaAlunosActivity : AppCompatActivity() {

    private lateinit var adapterPersonalizado: ArrayAdapter<Aluno>
    private lateinit var tv_alunos: ListView
    private val alunoDAO =  AlunoDAO(this)
    val CHAVE_ALUNO: String = "aluno"
    val CHAVE_LISTA_ALUNOS: String = "Lista Alunos"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)
        title = CHAVE_LISTA_ALUNOS
        tv_alunos = findViewById(R.id.lista_alunos)
        configuraAdapter(tv_alunos, alunoDAO.buscarTodosAlunos())
        editarAluno()
        btnAdicionarAluno()
        registerForContextMenu(tv_alunos)
    }

    override fun onResume() {
        super.onResume()
        adapterPersonalizado.clear()
        adapterPersonalizado.addAll(alunoDAO.buscarTodosAlunos())
    }

    private fun configuraAdapter(tv_alunos: ListView, alunos: List<Aluno> ){
        adapterPersonalizado = ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos)
        tv_alunos.adapter = adapterPersonalizado
    }

    private fun btnAdicionarAluno(){
        val btn_aluno: FloatingActionButton = findViewById(R.id.btn_add)
        btn_aluno.setOnClickListener(View.OnClickListener {
            val intentVaiProFormulario = Intent(this@ListaAlunosActivity, FormularioActivy::class.java)
            startActivity(intentVaiProFormulario)
        })
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_lista_aluno, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if(itemId == R.id.menu_lista_aluno_remove) {
            val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val aluno: Aluno = adapterPersonalizado.getItem(info.position) as Aluno
            alunoDAO.deleta(aluno)
            alunoDAO.close()
            adapterPersonalizado.remove(aluno)
        }
        return false
    }

    private fun editarAluno(){
        tv_alunos.setOnItemClickListener { lista, item, position, id ->
            val aluno: Aluno = tv_alunos.getItemAtPosition(position) as Aluno
            val intentVaiFormulario = Intent(this@ListaAlunosActivity, FormularioActivy::class.java )
            intentVaiFormulario.putExtra(CHAVE_ALUNO, aluno)
            startActivity(intentVaiFormulario)
        }
    }


}