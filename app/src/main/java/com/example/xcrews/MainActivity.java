package com.example.xcrews;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.xcrews.adapter.CrewAdapter;
import com.example.xcrews.api.RetrofitClient;
import com.example.xcrews.databinding.ActivityMainBinding;
import com.example.xcrews.model.Crew;
import com.example.xcrews.viewModel.CrewViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private CrewViewModel crewViewModel;
    CrewAdapter adapter = new CrewAdapter();
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        builder = new AlertDialog.Builder(this);


        binding.rvCrew.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.rvCrew.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        binding.rvCrew.setAdapter(adapter);

        crewViewModel = ViewModelProviders.of(this).get(CrewViewModel.class);

        makeNetworkRequest();

        binding.btnRefresh.setOnClickListener(view -> makeNetworkRequest());

        binding.btnDeleteAll.setOnClickListener(view -> {
            builder.setMessage("Are you sure want to delete all?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, which) -> {
                        crewViewModel.deleteAllCrew();
                        Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();

        });


    }

    private void makeNetworkRequest() {
        Call<List<Crew>> call = RetrofitClient.getInstance().getCrewAPI().getCrewList();

        call.enqueue(new Callback<List<Crew>>() {
            @Override
            public void onResponse(@NonNull Call<List<Crew>> call, @NonNull Response<List<Crew>> response) {
                List<Crew> crews = response.body();
                Log.e("Response", response.message());

                assert crews != null;
                for (Crew crew : crews) {
                    String id = crew.getId();
                    String name = crew.getName();
                    String agency = crew.getAgency();
                    String image = crew.getImage();
                    String wiki = crew.getWikipedia();
                    String status = crew.getStatus();
                    Crew crewModel = new Crew(name, agency, image, wiki, status, id);
                    crewViewModel.insert(crewModel);
                    adapter.asyncListDiffer.submitList(crews);
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Crew>> call, @NonNull Throwable t) {
                Log.e("ErrorResponse", t.getMessage());

                crewViewModel.getAllCrew().observe(MainActivity.this, new Observer<List<Crew>>() {
                    @Override
                    public void onChanged(List<Crew> crews) {
                        adapter.asyncListDiffer.submitList(crews);
                    }
                });
            }
        });
    }
}