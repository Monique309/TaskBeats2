package com.comunidadedevspace.taskbeats.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.comunidadedevspace.taskbeats.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomBarView = findViewById<BottomNavigationView>(R.id.bottomBarView)
        val floatActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        floatActionButton.setOnClickListener {
            openTaskListDetail()

        }


        val taskFragment = TaskFragment.newInstance()
        val taskNewsFragment = NewsFragment.newInstance()

        supportFragmentManager.commit {


            replace(R.id.fragment_container_view, taskFragment)
            setReorderingAllowed(true)

        }




        bottomBarView.setOnItemSelectedListener { menuItem ->
            val fragment = when (menuItem.itemId) {

                R.id.task_list -> taskFragment
                R.id.task_news -> taskNewsFragment
                else -> TaskFragment

            }

            supportFragmentManager.commit {

                replace(
                    R.id.fragment_container_view, taskNewsFragment)
                setReorderingAllowed(true)
            }

            true
        }

}



    private fun openTaskListDetail() {
        val intent = TaskDetailActivity.start(this, null)
        startActivity(intent)
    }
}

