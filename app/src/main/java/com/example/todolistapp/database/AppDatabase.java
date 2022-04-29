package com.example.todolistapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;

import com.example.todolistapp.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //variavel para acessar a dataBase
    private static AppDatabase database;
    //metodo para tarefa Dao
    public abstract TarefaDao getTarefaDao();

    public static AppDatabase getDatabase(Context context){
        //verefico se a database é nula
        if(database == null){
            //instancia a database
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "toodolist").build();
        }
        //retorna database
        return database;
    }


}
