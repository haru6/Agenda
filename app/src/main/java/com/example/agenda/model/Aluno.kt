package com.example.agenda.model

import java.io.Serializable

class Aluno(
    var id: Long? = null,
    var nome: String = "",
    var endereco:String = "",
    var telefone: String = "",
    var email: String = "",
    var estrela: Int = 0
): Serializable{
    override fun toString(): String {
        return  id.toString() + " - " + nome
    }
}
