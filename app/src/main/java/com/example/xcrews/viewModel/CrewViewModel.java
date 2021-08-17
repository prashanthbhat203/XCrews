package com.example.xcrews.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.xcrews.db.CrewRepository;
import com.example.xcrews.model.Crew;

import java.util.List;

public class CrewViewModel extends AndroidViewModel {

    private final CrewRepository repository;
    private final LiveData<List<Crew>> allCrew;

    public CrewViewModel(@NonNull Application application) {
        super(application);
        repository = new CrewRepository(application);
        allCrew = repository.getAllCrews();
    }

    public void insert(Crew crew) {
        repository.insertCrew(crew);
    }

    public LiveData<List<Crew>> getAllCrew() {
        return allCrew;
    }

    public void deleteAllCrew() {
        repository.deleteAllCrew();
    }
}
