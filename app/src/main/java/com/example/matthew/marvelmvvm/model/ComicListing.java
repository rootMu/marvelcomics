package com.example.matthew.marvelmvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class ComicListing {

    @SerializedName("results")
    @Expose
    @Getter private List<ComicModel> comics;

}
