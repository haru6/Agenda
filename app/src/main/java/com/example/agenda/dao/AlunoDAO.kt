package com.example.agenda.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.agenda.model.Aluno

class AlunoDAO (var context: Context): SQLiteOpenHelper(context, "Agenda", null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql : String = "CREATE TABLE Alunos (Id INTEGER PRIMARY KEY, Nome TEXT NOT NULL, Endereco TEXT, Telefone TEXT, Email TEXT, Nota INTEGER)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int,  newVersion: Int){
        val sql : String = "DROP TABLE IF EXISTS Alunos"
        db?.execSQL(sql)
        onCreate(db)

    }

    fun inserir(aluno: Aluno) {
        val db: SQLiteDatabase = writableDatabase
        val contentValues = dados(aluno)
        db.insert("Alunos", null, contentValues)
    }

    private fun dados(aluno: Aluno): ContentValues{
        val contentValues: ContentValues = ContentValues()
        contentValues.put("Nome", aluno.nome)
        contentValues.put("Endereco", aluno.endereco)
        contentValues.put("Telefone", aluno.telefone)
        contentValues.put("Email", aluno.email)
        contentValues.put("Nota", aluno.estrela)
        return contentValues
    }


    @SuppressLint("Range")
    fun buscarTodosAlunos(): MutableList<Aluno> {
        val sql: String = "SELECT * FROM Alunos"
        val db: SQLiteDatabase = readableDatabase
        val cursor: Cursor = db.rawQuery(sql, null)
        val alunos: MutableList<Aluno> = ArrayList()

        while (cursor.moveToNext()){
            val aluno: Aluno = Aluno()
            aluno.id = cursor.getLong(cursor.getColumnIndex("Id"))
            aluno.nome = cursor.getString(cursor.getColumnIndex("Nome"))
            aluno.endereco = cursor.getString(cursor.getColumnIndex("Endereco"))
            aluno.telefone = cursor.getString(cursor.getColumnIndex("Telefone"))
            aluno.email = cursor.getString(cursor.getColumnIndex("Email"))
            aluno.estrela = cursor.getInt(cursor.getColumnIndex("Nota"))
            alunos.add(aluno)
        }
        cursor.close()
        return alunos
    }

    fun deleta(aluno: Aluno) {
        val db: SQLiteDatabase = writableDatabase
        val params: Array<String> = arrayOf( aluno.id.toString() )
        db.delete("Alunos", "id= ?", params)
    }

    fun altera(aluno: Aluno) {
        val db: SQLiteDatabase = writableDatabase
        val params: Array<String> = arrayOf(aluno.id.toString())
        val contentValues = dados(aluno)
        db.update("Alunos", contentValues, "id = ?", params)
    }

}