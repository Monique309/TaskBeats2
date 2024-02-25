package com.comunidadedevspace.taskbeats.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface taskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (task: Task)

    @Query("Select * from task")
    fun getAll(): LiveData<List<Task>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    @Query("Delete from task")
    fun deleteAll()

    @Query("Delete from task WHERE id =:id")
    fun deleteById(id: Int)


}