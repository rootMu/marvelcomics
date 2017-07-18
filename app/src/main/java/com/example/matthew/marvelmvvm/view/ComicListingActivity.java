package com.example.matthew.marvelmvvm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.matthew.marvelmvvm.MarvelComicExampleApplication;
import com.example.matthew.marvelmvvm.R;
import com.example.matthew.marvelmvvm.viewmodel.ComicListingViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

@EActivity(R.layout.activity_comic_listing)
@OptionsMenu(R.menu.feed)
public class ComicListingActivity extends AppCompatActivity
{
    @ViewById(R.id.comic_list)
    RecyclerView postList;

    @ViewById(R.id.budget)
    EditText budget;

    @OptionsMenuItem(R.id.progress)
    MenuItem loadingMenuItem;

    @Inject
    ComicListingViewModel viewModel;

    private ComicAdapter comicAdapter;
    private LinearLayoutManager postListLayoutManager;

    /** Hold active loading observable subscriptions, so that they can be unsubscribed from when the activity is destroyed */
    private CompositeSubscription subscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((MarvelComicExampleApplication) getApplication()).component().inject(this);

        subscriptions = new CompositeSubscription();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        subscriptions.unsubscribe();
    }

    @AfterViews
    void init()
    {
        initViews();
        initBindings();

        // Initial page load
        loadNextPage();
    }

    private void initViews()
    {
        postListLayoutManager =  new LinearLayoutManager(this);

        postList.setLayoutManager(postListLayoutManager);

        comicAdapter = new ComicAdapter();
        postList.setAdapter(comicAdapter);
    }

    private void initBindings()
    {
        subscriptions.addAll(
            // Bind list of posts to the RecyclerView
            viewModel.postsObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(items -> comicAdapter.setItems(items)),

            // Bind loading status to show/hide loading spinner
            viewModel.isLoadingObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(this::setIsLoading)
        );
    }

    private void loadNextPage()
    {
        subscriptions.add(
            //viewModel.loadMorePosts().subscribe()
            viewModel.loadMoreComics().subscribe()
        );
    }

    private void setIsLoading(boolean isLoading)
    {
        if (loadingMenuItem != null)
        {
            loadingMenuItem.setVisible(isLoading);
        }
    }
}
