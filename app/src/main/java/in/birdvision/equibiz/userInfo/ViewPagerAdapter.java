/*
 * *
 *  * Created by Vaibhav Andhare on 9/7/20 5:15 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.userInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import in.birdvision.equibiz.R;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    Context context;
    private List<String> mData;
    private LayoutInflater mInflater;

    public ViewPagerAdapter(Context context, List<String> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_viewpager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String IMG_URL = "https://equibase.s3.ap-south-1.amazonaws.com/" + mData.get(position);
        Glide.with(context).load(IMG_URL).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_img);
        }
    }

}