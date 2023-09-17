package com.anushka.roommigrationdemo1

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Student::class],
    version = 3,
    autoMigrations = [AutoMigration(from = 2, to = 3)]
)
abstract class StudentDatabase : RoomDatabase() {

    abstract val subscriberDAO : StudentDAO

    companion object{
      @Volatile
      private var INSTANCE : StudentDatabase? = null
          fun getInstance(context: Context):StudentDatabase{
              synchronized(this){
                  var instance = INSTANCE
                      if(instance == null){
                          instance = Room.databaseBuilder(
                                 context.applicationContext,
                              StudentDatabase::class.java,
                                 "student_data_database"
                          ).build()
                          INSTANCE = instance
                      }
                  return instance
              }
          }
    }
}

