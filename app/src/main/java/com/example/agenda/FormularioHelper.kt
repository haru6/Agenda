package com.example.agenda

import android.widget.EditText
import android.widget.RatingBar
import com.example.agenda.model.Aluno

class FormularioHelper(var activity: FormularioActivy){

    var campoNome: EditText
    var campoEndereco: EditText
    var campoTelefone: EditText
    var campoEmail: EditText
    var campoEstrela: RatingBar

    private var aluno: Aluno

    init {
        campoNome = activity.findViewById(R.id.formulario_name)
        campoEndereco = activity.findViewById(R.id.formulario_address)
        campoTelefone = activity.findViewById(R.id.formulario_phone)
        campoEmail = activity.findViewById(R.id.formulario_email)
        campoEstrela = activity.findViewById(R.id.formulario_stars)
        aluno = Aluno()
    }


    fun getAluno(): Aluno{
        this.aluno = Aluno(this.aluno.id, campoNome.text.toString(), campoEndereco.text.toString(),
            campoTelefone.text.toString(), campoEmail.text.toString(), campoEstrela.numStars)
        return aluno
    }

    fun preencheFormulario(aluno: Aluno) {
        campoNome.setText(aluno.nome)
        campoEndereco.setText(aluno.endereco)
        campoTelefone.setText(aluno.telefone)
        campoEmail.setText(aluno.email)
        campoEstrela.progress = aluno.estrela
        this.aluno = aluno
    }
}