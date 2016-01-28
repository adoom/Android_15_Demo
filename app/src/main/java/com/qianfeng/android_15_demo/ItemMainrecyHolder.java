package com.qianfeng.android_15_demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemMainrecyHolder extends RecyclerView.ViewHolder {
    private TextView textItleItemMainrecy;
    private TextView textPublictimeItemMainrecy;
    private ImageView imgItemMainrecy;

    public ItemMainrecyHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_mainrecy, parent, false));
    }

    public ItemMainrecyHolder(View view) {
        super(view);
        textItleItemMainrecy = (TextView) view.findViewById(R.id.text_itle_item_mainrecy);
        textPublictimeItemMainrecy = (TextView) view.findViewById(R.id.text_publictime_item_mainrecy);
        imgItemMainrecy = (ImageView) view.findViewById(R.id.img_item_mainrecy);
    }

    public TextView getTextItleItemMainrecy() {
        return textItleItemMainrecy;
    }

    public ImageView getImgItemMainrecy() {
        return imgItemMainrecy;
    }

    public TextView getTextPublictimeItemMainrecy() {
        return textPublictimeItemMainrecy;
    }
}
