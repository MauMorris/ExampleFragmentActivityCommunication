/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.android_me.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.List;


// Custom adapter class that displays a list of Android-Me images in a GridView
public class MasterListAdapter extends RecyclerView.Adapter<MasterListAdapter.MyViewHolder> {

    private List<Integer> mImageIds;
    public static ItemListClickListener mInterface;

    public interface ItemListClickListener {
        void itemClickedFromList(int position, View view);
    }

    public MasterListAdapter(List<Integer> imageIds, ItemListClickListener itemListClickListener) {
        mImageIds = imageIds;
        mInterface = itemListClickListener;
    }

    public void setData(List<Integer> imageIds){
        mImageIds = imageIds;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mImageIds == null ? 0 : mImageIds.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.fragment_body_part;
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        Integer id = mImageIds.get(position);

        viewHolder.mImage.setImageResource(id);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.body_part_image_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mInterface.itemClickedFromList(getAdapterPosition(), view);
        }
    }
}