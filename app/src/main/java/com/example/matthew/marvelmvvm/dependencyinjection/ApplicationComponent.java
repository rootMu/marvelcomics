package com.example.matthew.marvelmvvm.dependencyinjection;

import com.example.matthew.marvelmvvm.dependencyinjection.module.ApplicationModule;
import com.example.matthew.marvelmvvm.dependencyinjection.module.ClientModule;
import com.example.matthew.marvelmvvm.view.ComicListingActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ClientModule.class})
public interface ApplicationComponent
{
    void inject(ComicListingActivity activity);
}
