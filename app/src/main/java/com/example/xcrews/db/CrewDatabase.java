package com.example.xcrews.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.xcrews.model.Crew;

@Database(entities = {Crew.class}, version = 1, exportSchema = false)
public abstract class CrewDatabase extends RoomDatabase {
    public abstract CrewDAO crewDao();

    private static CrewDatabase mInstance;

    public static synchronized CrewDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    CrewDatabase.class, "Crew_Database")
                    .addCallback(roomCallback)
                    .build();
        }
        return mInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(mInstance).execute();
        }
    };

    private static  class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(CrewDatabase instance) {
            CrewDAO dao = instance.crewDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
