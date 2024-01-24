package com.comunidadedevspace.taskbeats.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.comunidadedevspace.taskbeats.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomBarView = findViewById<BottomNavigationView>(R.id.bottomBarView)


        val taskListFragment = TaskListFragment.newInstance()
        val taskNewsFragment = TaskNewsFragment.newInstance()

        supportFragmentManager.commit {

            replace(R.id.fragment_container_view, taskListFragment)
            setReorderingAllowed(true)
        }


        bottomBarView.setOnItemSelectedListener { menuItem ->
            val fragment = when (menuItem.itemId) {

                R.id.task_list -> taskListFragment
                R.id.task_news -> taskNewsFragment
                else -> TaskListFragment
            }


            supportFragmentManager.commit {

                replace(R.id.fragment_container_view, fragment)
                setReorderingAllowed(true)
            }

            true
        }
    }
}

