package com.example.matthew.marvelmvvm.viewmodel;

import com.example.matthew.marvelmvvm.client.MarvelClient;
import com.example.matthew.marvelmvvm.model.ComicResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComicListingViewModelTest
{
    @Mock
    MarvelClient marvelClient;

    private Gson gson;

    @Before
    public void setup() throws Exception
    {
        gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

        when(marvelClient.getComics(anyString(),anyString(),anyString(), anyInt())).thenReturn(Observable.just(comicsPage()));
    }

    private ComicResponse comicsPage() throws Exception
    {
        return gson.fromJson(IOUtils.toString(getClass().getResourceAsStream("/comicPage.json")), ComicResponse.class);
    }

    /* I changed the architecture completely so the tests I had built off became redundant */
}