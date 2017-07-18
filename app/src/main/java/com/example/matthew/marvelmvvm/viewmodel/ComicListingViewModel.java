package com.example.matthew.marvelmvvm.viewmodel;

import com.example.matthew.marvelmvvm.client.MarvelClient;
import com.example.matthew.marvelmvvm.model.ComicListing;
import com.example.matthew.marvelmvvm.model.ComicModel;
import com.example.matthew.marvelmvvm.model.ComicResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

public class ComicListingViewModel
{
    private static final String TAG = ComicListingViewModel.class.getSimpleName();

    private MarvelClient marvelClient;

    private BehaviorSubject<List<ComicViewModel>> postComic = BehaviorSubject.create(new ArrayList<>());
    private BehaviorSubject<Boolean> isLoadingComic = BehaviorSubject.create(false);

    @Inject
    public ComicListingViewModel(MarvelClient marvelClient)
    {
        this.marvelClient = marvelClient;
    }

    public Observable<List<ComicViewModel>> loadMoreComics(){

        // Don't try and load if we're already loading
        if (isLoadingComic.getValue())
        {
            return Observable.empty();
        }

        isLoadingComic.onNext(true);

        String ts = Calendar.getInstance().getTime().toString();
        String publicKey = "54306733de0f5cd1418aa05a85fa062a";
        String privateKey = "5de1fabcda2ea08912bd8b09bca4321f50563655";
        String hash = MD5(ts + privateKey + publicKey);

        Observable<List<ComicViewModel>> comicList = marvelClient
                .getComics(ts,publicKey,hash, 100)
                .subscribeOn(Schedulers.io())
                .map(ComicResponse::getComicListing)
                .flatMapIterable(ComicListing::getComics)
                .filter(object -> object != null)
                // Transform model to viewmodel
                .map(comic -> new ComicViewModel((ComicModel) comic))
                .toList()
                .doOnNext(list -> {
                    List<ComicViewModel> fullList = new ArrayList<>(postComic.getValue());
                    fullList.addAll(list);
                    postComic.onNext(fullList);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(() -> isLoadingComic.onNext(false));


        return comicList;
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public Observable<List<ComicViewModel>> postsObservable()
    {
        return postComic.asObservable();
    }

    public Observable<Boolean> isLoadingObservable()
    {
        return isLoadingComic.asObservable();
    }

}
