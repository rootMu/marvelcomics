package com.example.matthew.marvelmvvm;

import android.app.Application;

import com.example.matthew.marvelmvvm.dependencyinjection.ApplicationComponent;
import com.example.matthew.marvelmvvm.dependencyinjection.DaggerApplicationComponent;
import com.example.matthew.marvelmvvm.dependencyinjection.module.ApplicationModule;

import org.androidannotations.annotations.EApplication;

@EApplication
public class MarvelComicExampleApplication extends Application
{
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(new ApplicationModule(this))
            .build();
    }

    public ApplicationComponent component()
    {
        return applicationComponent;
    }
}
