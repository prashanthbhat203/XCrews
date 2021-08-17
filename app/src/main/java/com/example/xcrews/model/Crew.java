package com.example.xcrews.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "crew_table")
public class Crew {

    @SerializedName("name")
    private String name;

    @SerializedName("agency")
    private String agency;

    @SerializedName("image")
    private String image;

    @SerializedName("wikipedia")
    private String wikipedia;



    @SerializedName("status")
    private String status;


    @PrimaryKey
    @SerializedName("id")
    @NotNull
    private String id;

    public Crew() {
    }

    public Crew(String name, String agency, String image, String wikipedia, String status, String id) {
        this.name = name;
        this.agency = agency;
        this.image = image;
        this.wikipedia = wikipedia;
        this.status = status;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
