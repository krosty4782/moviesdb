package com.testapplication.moviesplaying.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder implements OnBindViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }
}
