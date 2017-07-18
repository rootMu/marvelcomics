package com.example.matthew.marvelmvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Thumbnail {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("extension")
    @Expose
    private String extension;

    public String URL(){return path+"."+extension;}


}
