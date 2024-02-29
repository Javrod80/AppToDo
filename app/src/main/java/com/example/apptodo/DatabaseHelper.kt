package com.example.apptodo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION ) {


    companion object {

        const val  DATABASE_NAME = "appToDo.db"
        const val  DATABASE_VERSION = 1

        private const val  SQL_CREATE_TABLE =
            "CREATE TABLE Task (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "task TEXT," +
                    "done BOOLEAN)"

        private const val  SQL_DELETE_TABLE = "DROP TABLE IF EXISTS Task"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)




    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        drestroyDatabase(db)
        onCreate(db)
    }

    private fun drestroyDatabase (db: SQLiteDatabase){
        db.execSQL(SQL_DELETE_TABLE)


    }

    fun createTask (){
        var  db = writableDatabase
       // db.execSQL("INSERT INTO Task(task,done)VALUES('Comprar leche',FALSE)")

        val values = ContentValues()
        values.put("task", "Comprar leche")
        values.put("done",false)










    }

}