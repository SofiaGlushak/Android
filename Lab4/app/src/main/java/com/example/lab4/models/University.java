package com.example.lab4.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.lab4.network.StringListAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class University implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String country;

    @SerializedName("web_pages")
    @JsonAdapter(StringListAdapter.class)
    public String webPage;

    @SerializedName("domains")
    @JsonAdapter(StringListAdapter.class)
    public String domain;

    public University(String name, String country, String webPage, String domain) {
        this.name = name;
        this.country = country;
        this.webPage = webPage;
        this.domain = domain;
    }
    public University() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getWebPage() {
        return webPage;
    }

    public String getDomain() {
        return domain;
    }
}
