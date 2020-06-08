package com.example.deepinfo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = PersonInfo.class, version = 4)
public abstract class PersonDatabase extends RoomDatabase {

    public abstract PersonDao personDao();

    public static PersonDatabase INSTANCE;

//    static Migration migration = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE person_table ADD COLUMN deliveryAddress TEXT NOT NULL DEFAULT ''");
//        }
//    };

    static PersonDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (PersonDatabase.class){
                if(INSTANCE == null){
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),PersonDatabase.class,"person_database")
                                    .fallbackToDestructiveMigration()
                                    .allowMainThreadQueries()
                                    .build();

                }
            }
        }
        return INSTANCE;
    }
}
// .addMigrations(migration)