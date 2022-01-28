package com.example.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.Aluno
import android.widget.AdapterView.AdapterContextMenuInfo as AdapterContextMenuInfo1

class MainActivity : AppCompatActivity() {

    private lateinit var tv_alunos: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_alunos = findViewById(R.id.lista_alunos)
        editarAluno()
        adicionarAluno()
        registerForContextMenu(tv_alunos)
    }

    override fun onResume() {
        //toda vez que sai da tela de lista e volta carrega a lista de Alunos
        super.onResume()
        criarLista()
    }

    private fun criarLista(){
        //busca de todos os alunos no banco de dados
        val alunoDAO: AlunoDAO =  AlunoDAO(this)
        val alunos = alunoDAO.buscarTodosAlunos()
        alunoDAO.close()
        //lista todos os alunos

        val adapter: ArrayAdapter<Aluno> = ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos)
        tv_alunos.adapter = adapter
    }

    private fun adicionarAluno(){
        //chama uma nova activity para add Aluno
        val btn_aluno: Button = findViewById(R.id.btn_add)
        btn_aluno.setOnClickListener(View.OnClickListener {
            val intentVaiProFormulario : Intent = Intent(this@MainActivity, FormularioActivy::class.java)
            startActivity(intentVaiProFormulario)
        })
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.add(0, v!!.id, 0, "Deletar")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val selectedItemOrder = item.order
        val selectedItemTitle = item.title
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val aluno: Aluno = tv_alunos.getItemAtPosition(info.position) as Aluno
        val alunoDAO: AlunoDAO =  AlunoDAO(this)
        alunoDAO.deleta(aluno)
        alunoDAO.close()

        criarLista()
        return false
    }

    private fun editarAluno(){
        tv_alunos.setOnItemClickListener { lista, item, position, id ->
            val aluno: Aluno = tv_alunos.getItemAtPosition(position) as Aluno
            val intentVaiFormulario: Intent = Intent(this@MainActivity, FormularioActivy::class.java )
            intentVaiFormulario.putExtra("aluno", aluno)
            startActivity(intentVaiFormulario)
        }
    }


}