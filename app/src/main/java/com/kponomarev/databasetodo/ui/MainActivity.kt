package com.kponomarev.databasetodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kponomarev.databasetodo.R
import com.kponomarev.databasetodo.databinding.ActivityMainBinding
import com.kponomarev.databasetodo.ui.feature_list_task.ui.TaskListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            replace(binding.commonContainer.id, TaskListFragment.newInstance())
        }
    }
}
