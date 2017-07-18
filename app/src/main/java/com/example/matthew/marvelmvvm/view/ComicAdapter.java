package com.example.matthew.marvelmvvm.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.matthew.marvelmvvm.R;
import com.example.matthew.marvelmvvm.viewmodel.ComicViewModel;

import java.util.ArrayList;
import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicViewHolder>
{
    private List<ComicViewModel> items = new ArrayList<>();

    public ComicAdapter()
    {
        setHasStableIds(true);
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_comic, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComicViewHolder holder, int position)
    {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public long getItemId(int position)
    {
        return getItem(position).getId();
    }

    public ComicViewModel getItem(int position)
    {
        return items.get(position);
    }

    public void setItems(List<ComicViewModel> items)
    {

        /*pseudocode

             ---------display only items that are within budget

             collections.sort(price)
             for(ComicViewModel vm : items){
             if(accumulatedprice += price < budget){
             this.items.add(vm)
             }

             --------display total page count

             for(ComicViewModel vm : items){
             pc+=pageCount;
             }
             pc passed back through subscription to update view
         */

        if (items == null)
        {
            return;
        }

        this.items = new ArrayList<>(items);
        notifyDataSetChanged();
    }
}
