package com.example.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.Aluno

class FormularioActivy : AppCompatActivity() {

    private lateinit var formularioHelper: FormularioHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_activy)
        formularioHelper = FormularioHelper(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_formulario, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_formulario_ok -> {
                val aluno: Aluno = formularioHelper.getAluno()
                val alunoDAO: AlunoDAO = AlunoDAO(this)
                alunoDAO.inserir(aluno)
                alunoDAO.close()
                Toast.makeText(this@FormularioActivy, "Aluno " + aluno.nome + " salvo", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}