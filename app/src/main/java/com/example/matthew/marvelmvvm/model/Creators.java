package com.example.matthew.marvelmvvm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

public class Creators {

    @SerializedName("available")
    @Getter
    private Integer available;
    @SerializedName("collectionURI")
    @Getter
    private String collectionURI;
    @SerializedName("items")
    @Getter
    private List<Creator> creators = null;
    @SerializedName("returned")
    @Getter
    private Integer returned;
    public String getAuthors(){
        StringBuilder sb = new StringBuilder();
        for(Creator creator : creators){
            if(creator.getRole().equals("writer")){
                sb.append(creator.getName());
                sb.append(" ");
            }
        }
        return sb.toString();
    }

}

