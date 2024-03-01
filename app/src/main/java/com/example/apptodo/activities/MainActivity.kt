package com.example.apptodo.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.apptodo.R
import com.example.apptodo.data.Task
import com.example.apptodo.databinding.ActivityMainBinding
import com.example.apptodo.provider.TaskDAO


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val task : Task = Task (-1 , "Comprar Leche ", false)

        val taskDAO = TaskDAO(this)
        taskDAO.insert(task)

        val taskList = taskDAO.findAll()

        // para imprimir la tarea

        for(task in taskList){
            Log.i ("DATABASE", task.toString())
        }

        var task2 : Task?= taskDAO.find (2)

        if (task2 != null) {
            Log.i ("DATABASE", task2.toString())

            //asignar nuevo valor
            task2.done = true
            task2.task = "Pagar facturas"

            taskDAO.update(task2)

        }
        task2 = taskDAO.find(2)
        Log.i("DATABASE", task2.toString())

        if (task2 != null)
            taskDAO.delete(task2)
            task2











        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}