package com.comunidadedevspace.taskbeats.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.Task
import com.google.android.material.snackbar.Snackbar

class TaskDetailActivity : AppCompatActivity() {

    private var task: Task? = null
    private lateinit var btnDone: Button


    private val viewModel: TaskDetailViewModel by viewModels {
        TaskDetailViewModel.getVMFactory(application)
    }


    companion object {

        private const val Task_Detail_Extra = "task.extra.detail"

        fun start(context: Context, task: Task?): Intent {
            val intent = Intent(context, TaskDetailActivity::class.java)
                .apply {
                    putExtra(Task_Detail_Extra, task)

                }
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        task = intent.getSerializableExtra(Task_Detail_Extra) as Task?


        val edtTitle = findViewById<EditText>(R.id.edt_task_title)
        val edtDescription = findViewById<EditText>(R.id.edt_task_description)
        btnDone = findViewById(R.id.btn_done)

         if (task != null) {
            edtTitle.setText(task!!.title)
            edtDescription.setText(task!!.description)
        }

        btnDone.setOnClickListener {

            val title = edtTitle.text.toString()
            val desc = edtDescription.text.toString()

            if (title.isNotEmpty() && desc.isNotEmpty()) {
                if (task == null) {

                    addOrUpdate(0, title, desc,ActionType.CREATE)

            } else {
                    addOrUpdate(task!!.id, title, desc,ActionType.UPDATE)

            }
            }else{
            showMessage(it, "Fields are required")
        }

        }
    }
    private fun addOrUpdate (id: Int, title: String, description: String, actionType: ActionType){
        val task = Task(id, title, description)
        performAction(task,ActionType.CREATE)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_task_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.delete_task -> {
                if (task != null) {
                    performAction(task!!,ActionType.DELETE)
                } else {
                    showMessage(btnDone, "Item not found")

                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }



        }
    private fun performAction(task: Task, actionType:ActionType) {

                val taskAction = TaskAction(task, actionType.name)
              viewModel.execute(taskAction)
        finish()


        }



        private fun showMessage(view: View, message: String) {

        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }
}
