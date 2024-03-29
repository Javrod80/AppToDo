package com.example.apptodo.data

import com.example.apptodo.utils.DatabaseManager

class Task(var id: Int, var task: String, var done:Boolean){

    companion object {

        const val TABLE_NAME = "appToDo.db"
        const val COLUMN_NAME_TASK = "task"
        const val COLUMN_NAME_DONE = "done"
         val COLUMN_NAMES = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME_TASK,
            COLUMN_NAME_DONE
        )


    }



    override fun toString(): String {
        return "$id -> Task : $task - $done"
    }


}



