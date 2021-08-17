package com.example.xcrews.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.xcrews.model.Crew;

import java.util.List;

public class CrewRepository {


    private final CrewDAO crewDAO;
    private final LiveData<List<Crew>> allCrew;

    public CrewRepository(Application application) {
        CrewDatabase database = CrewDatabase.getInstance(application);
        crewDAO = database.crewDao();
        allCrew = crewDAO.getAllCrew();
    }

    public void insertCrew(Crew crew) {
        new InsertCrewAsyncTask(crewDAO).execute(crew);
    }

    public LiveData<List<Crew>> getAllCrews() {
        return allCrew;

    }

    public void deleteAllCrew() {
        new DeleteAllCrewAsyncTask(crewDAO).execute();
    }

    private static class InsertCrewAsyncTask extends AsyncTask<Crew, Void, Void> {
        private CrewDAO dao;

        private InsertCrewAsyncTask(CrewDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Crew... model) {
            dao.insertCrew(model[0]);
            return null;
        }
    }

    private static class DeleteAllCrewAsyncTask extends AsyncTask<Void, Void, Void> {
        private CrewDAO dao;
        private DeleteAllCrewAsyncTask(CrewDAO dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {

            dao.deleteAllCrew();
            return null;
        }
    }



}
