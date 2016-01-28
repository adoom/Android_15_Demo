package com.qianfeng.android_15_demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemMainrecyPresenter extends RecyclerView.ViewHolder {
    private TextView textItleItemMainrecy;
    private TextView textPublictimeItemMainrecy;
    private ImageView imgItemMainrecy;
    private R.anim data;

    public ItemMainrecyPresenter(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_mainrecy, parent, false));
    }

    public ItemMainrecyPresenter(View view) {
        super(view);
        textItleItemMainrecy = (TextView) itemView.findViewById(R.id.text_itle_item_mainrecy);
        textPublictimeItemMainrecy = (TextView) itemView.findViewById(R.id.text_publictime_item_mainrecy);
        imgItemMainrecy = (ImageView) itemView.findViewById(R.id.img_item_mainrecy);
    }

    public R.anim getData() {
        return data;
    }

    public void refresh() {
        if (data != null) {
            itemView.setVisibility(View.VISIBLE);
        } else {
            itemView.setVisibility(View.GONE);
        }
    }

    public void swapData(R.anim data) {
        this.data = data;
        refresh();
    }
}
