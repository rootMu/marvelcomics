package com.example.matthew.marvelmvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import lombok.Getter;

public class ComicResponse {

    @SerializedName("data")
    @Expose
    @Getter private ComicListing comicListing;
}
