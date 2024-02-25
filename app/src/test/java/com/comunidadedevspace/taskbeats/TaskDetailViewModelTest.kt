package com.comunidadedevspace.taskbeats

import com.comunidadedevspace.taskbeats.data.Task
import com.comunidadedevspace.taskbeats.data.taskDao
import com.comunidadedevspace.taskbeats.presentation.ActionType
import com.comunidadedevspace.taskbeats.presentation.TaskAction
import com.comunidadedevspace.taskbeats.presentation.TaskDetailViewModel
import com.comunidadedevspace.taskbeats.presentation.TaskListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)

class TaskDetailViewModelTest {

    private val taskDao: taskDao = mock()
    private val underTest: TaskDetailViewModel by lazy {

        TaskDetailViewModel(taskDao,
            UnconfinedTestDispatcher()
        )
    }
    @Test

    fun update_task () = runTest {
        val task = Task(id = 2,
            title = "title",
            description = "description")

        val taskAction = TaskAction(

            task = task,
            actionType = ActionType.UPDATE.name
        )

        underTest.execute(taskAction)
        verify(taskDao).update(task)


    }
    @Test

    fun delete_task () = runTest {
        val task = Task(id = 2,
            title = "title",
            description = "description")

        val taskAction = TaskAction(

            task = task,
            actionType = ActionType.DELETE.name
        )

        underTest.execute(taskAction)
        verify(taskDao).deleteById(task.id)


    }
    @Test

    fun create_task () = runTest{

        val task = Task(id = 0,
            title = "title",
            description = "description")

        val taskAction = TaskAction(

            task = task,
            actionType = ActionType.CREATE.name
        )

        underTest.execute(taskAction)
        verify(taskDao).insert(task)
    }
}
