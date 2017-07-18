package com.example.matthew.marvelmvvm.view;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matthew.marvelmvvm.R;
import com.example.matthew.marvelmvvm.viewmodel.ComicViewModel;
import com.squareup.picasso.Picasso;

import org.apache.commons.validator.routines.UrlValidator;

public class ginComicViewHolder extends RecyclerView.ViewHolder
{
    private View view;
    private TextView title;
    private ImageView thumbnail;

    public ComicViewHolder(View view)
    {
        super(view);

        this.view = view;
        this.title = (TextView) view.findViewById(R.id.title);
        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
    }

    public void bind(ComicViewModel viewModel)
    {
        title.setText(viewModel.getTitle());

        UrlValidator urlValidator = new UrlValidator();
        boolean hasThumbnail = viewModel.getThumbnail() != null && urlValidator.isValid(viewModel.getThumbnail().URL());

        // Show/hide the thumbnail if there is/isn't one
        thumbnail.setVisibility(hasThumbnail ? View.VISIBLE : View.GONE);

        // Load the thumbnail if there is one
        if (hasThumbnail)
        {
            Picasso.with(view.getContext()).load(viewModel.getThumbnail().URL()).into(thumbnail);
        }

        thumbnail.setOnClickListener(arg0 -> {

            // custom dialog
            final Dialog dialog = new Dialog(view.getContext());
            dialog.setContentView(R.layout.dialog_comic);

            View tg,dg,pcg,pg,ag;
            tg = dialog.findViewById(R.id.titleGroup);
            dg = dialog.findViewById(R.id.descriptionGroup);
            pcg = dialog.findViewById(R.id.pageCountGroup);
            pg = dialog.findViewById(R.id.priceGroup);
            ag = dialog.findViewById(R.id.authorsGroup);

            String _title = viewModel.getTitle() != null ? viewModel.getTitle() : "";
            String _description = viewModel.getDescription() != null ? viewModel.getDescription() : "";
            int _pageCount = viewModel.getPageCount() != 0 ? viewModel.getPageCount() : 0;
            //String _price = viewModel.getPrice() != null ? viewModel.getPrice() : "";
            String _authors = viewModel.getCreators() != null ? viewModel.getCreators().getAuthors() : "";

            if(!_title.isEmpty()){
                TextView title = dialog.findViewById(R.id.title);
                title.setText(_title);
                tg.setVisibility(View.VISIBLE);
            }

            if(!_description.isEmpty()){
                TextView description = dialog.findViewById(R.id.description);
                description.setText(_description);
                dg.setVisibility(View.VISIBLE);
            }

            if(_pageCount > 0){
                TextView pageCount = dialog.findViewById(R.id.pc);
                pageCount.setText(Integer.toString(_pageCount));
                pcg.setVisibility(View.VISIBLE);
            }

//            if(!viewModel.getPrice().isEmpty()){
//                TextView price = dialog.findViewById(R.id.price);
//                price.setText(viewModel.getPrice());
//                pg.setVisibility(View.VISIBLE);
//            }

            if(!_authors.trim().isEmpty()){
                TextView authors = dialog.findViewById(R.id.authors);
                authors.setText(_authors);
                ag.setVisibility(View.VISIBLE);
            }

            View close = (View) dialog.findViewById(R.id.close);
            // if button is clicked, close the custom dialog
            close.setOnClickListener(v -> dialog.dismiss());

            dialog.show();
        });
    }




}
