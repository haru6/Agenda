package com.example.agenda.model

class Aluno(
    var id: Long? = 0,
    var nome: String = "",
    var endereco:String = "",
    var telefone: String = "",
    var email: String = "",
    var estrela: Int = 0
){
    override fun toString(): String {
        return  id.toString() + " - " + nome
    }
}
