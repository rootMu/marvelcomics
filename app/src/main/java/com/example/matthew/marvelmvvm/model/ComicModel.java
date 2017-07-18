package com.example.matthew.marvelmvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import lombok.Getter;

public class ComicModel {

    @SerializedName("id")
    @Expose @Getter
    private Integer id;
    @SerializedName("title")
    @Expose @Getter
    private String title;
    @SerializedName("description")
    @Expose @Getter
    private String description;
    @SerializedName("pageCount")
    @Expose @Getter
    private Integer pageCount;
////    @SerializedName("prices")
////    @Expose
////    public List<Price> prices = null;
    @SerializedName("thumbnail")
    @Expose @Getter
    private Thumbnail thumbnail;
    @SerializedName("creators")
    @Getter
    public Creators creators;

}
