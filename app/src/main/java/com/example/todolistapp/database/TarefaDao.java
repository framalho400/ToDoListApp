package com.example.todolistapp.database;

import androidx.core.text.util.LinkifyCompat;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolistapp.model.Tarefa;

import java.util.List;

@Dao
public interface TarefaDao {
    @Insert
    void insert (Tarefa t);
    @Update
    void update(Tarefa t);
    @Delete
    void delete(Tarefa t);

    @Query("select * from tarefa")
    List<Tarefa> getAll();
}
