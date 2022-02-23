package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.helper.FormularioHelper
import com.example.agenda.model.Aluno

class FormularioActivy : AppCompatActivity() {

    private lateinit var formularioHelper: FormularioHelper
    private val CHAVE_ALUNO: String = "aluno"
    private val alunoDAO = AlunoDAO(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_activy)
        var aluno = Aluno()
        formularioHelper = FormularioHelper(this)
        if(intent.hasExtra(CHAVE_ALUNO)) aluno = intent.getSerializableExtra("aluno") as Aluno
        else aluno = Aluno()
        formularioHelper.preencheFormulario(aluno)
    }

    private fun recuperaAluno(): Aluno = formularioHelper.getAluno()

    private fun saveAluno(){
        val aluno = recuperaAluno()
        if (aluno.id != null) alunoDAO.altera(aluno)
        else alunoDAO.inserir(aluno)
        trocaParaActivityListaAluno()
        finish()
    }

    private fun trocaParaActivityListaAluno() {
        val intent = Intent(this@FormularioActivy, ListaAlunosActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_formulario_salvar) saveAluno()
        return super.onOptionsItemSelected(item)
    }
}