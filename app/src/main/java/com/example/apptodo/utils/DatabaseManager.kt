package com.example.apptodo.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.apptodo.data.Task

class DatabaseManager(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {

        const val DATABASE_NAME = "appToDo.db"
        const val DATABASE_VERSION = 1
        const val COLUMN_NAME_ID = "id"

        //crear tabla nombre, id, columna y columna ! crear constantes antes

        private const val SQL_CREATE_TABLE =
            "CREATE TABLE ${Task.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Task.COLUMN_NAME_TASK} TEXT," +
                    "${Task.COLUMN_NAME_DONE} BOOLEAN)"

        private const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS Task"
    }


    // implementar metodos : onCreate , onUpdate , destroyDatabase

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        drestroyDatabase(db)
        onCreate(db)
    }

    private fun drestroyDatabase(db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_TABLE)


    }

    // poner valores a la tabla

    fun createTask() {
        var db = writableDatabase
        // db.execSQL("INSERT INTO Task(task,done)VALUES('Comprar leche',FALSE)")

        var values = ContentValues()
        values.put("task", "Comprar leche")
        values.put("done", false)


        var newRowId = db.insert("Task", null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        values = ContentValues()
        values.put("task", "Limpiar el coche")
        values.put("done", false)

        newRowId = db.insert("Task", null, values)
        Log.i("DATABASE", "New record id: $newRowId")

    }

    // leer datos


    @SuppressLint("Range")
    fun readTasks() {
        val db = readableDatabase
        val cursor = db.query(
            "Task",                 // The table to query
            arrayOf(
                "id",
                "task",
                "done"
            ),     // The array of columns to return (pass null to get all)
            null,                // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            "done DESC"               // The sort order
        )
        while (cursor.moveToNext()) {
            val id = cursor.getString(cursor.getColumnIndex("id"))
            val task = cursor.getString(cursor.getColumnIndex("task"))
            val done = cursor.getInt(cursor.getColumnIndex("done")) == 1
            Log.i("DATABASE", "$id -> Task: $task, Done: $done")
        }
        cursor.close()
    }

    // actualizar datos


    fun updateTask() {
        val db = writableDatabase
        //db.execSQL("INSERT INTO Task (task, done) VALUES ('Comprar leche', false)")

        var values = ContentValues()
        values.put("done", true)

        var updatedRows = db.update("Task", values, "id = ? OR id = ?", arrayOf("1", "3"))
        Log.i("DATABASE", "Updated records: $updatedRows")
    }

    // borrar tabla

    fun deleteTask (){

        val db = writableDatabase
        val deletedRows = db.delete("Task","id =1",null)
        Log.i ("DATABASE","Deleted rows : $deletedRows")
    }


}

