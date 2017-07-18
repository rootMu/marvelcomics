package com.example.matthew.marvelmvvm.viewmodel;

import com.example.matthew.marvelmvvm.model.ComicModel;
import com.example.matthew.marvelmvvm.model.Creators;
import com.example.matthew.marvelmvvm.model.Thumbnail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class ComicViewModel
{
    private int id;
    private String title;
    private String description;
    private int pageCount;
    private Thumbnail thumbnail;
    private Creators creators;

    public ComicViewModel(ComicModel comicModel)
    {
        this.id = comicModel.getId();
        this.thumbnail = comicModel.getThumbnail();
        this.title = comicModel.getTitle();
        this.description = comicModel.getDescription();
        this.pageCount = comicModel.getPageCount();
        this.creators = comicModel.getCreators();
    }
}
