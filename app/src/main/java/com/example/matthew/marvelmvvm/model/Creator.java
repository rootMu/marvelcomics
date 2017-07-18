package com.example.matthew.marvelmvvm.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class Creator {

    @SerializedName("resourceURI")
    @Getter
    private String resourceURI;
    @SerializedName("name")
    @Getter
    private String name;
    @SerializedName("role")
    @Getter
    private String role;

}