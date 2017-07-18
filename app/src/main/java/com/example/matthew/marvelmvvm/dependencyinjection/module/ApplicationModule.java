package com.example.matthew.marvelmvvm.dependencyinjection.module;

import com.example.matthew.marvelmvvm.MarvelComicExampleApplication;

import dagger.Module;

@Module
public class ApplicationModule
{
    private MarvelComicExampleApplication application;

    public ApplicationModule(MarvelComicExampleApplication application)
    {
        this.application = application;
    }
}
