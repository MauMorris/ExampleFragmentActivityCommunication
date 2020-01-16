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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;


// This fragment displays all of the AndroidMe images in one large list
// The list appears as a grid of images
public class MasterListFragment extends Fragment implements MasterListAdapter.ItemListClickListener,
        View.OnClickListener {

    OnImageFromAndroidListClickListener mCallback;

    private boolean flagHead = false;
    private boolean flagBody = false;
    private boolean flagLegs = false;

    private View viewBody;
    private View viewHead;
    private View viewLegs;

    private Button button;

    private int headIndex = 0;
    private int bodyIndex = 0;
    private int legIndex = 0;

    // Mandatory empty constructor
    public MasterListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mCallback = (OnImageFromAndroidListClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
                    "must implement OnImageFromAndroidListClickListener");
        }
    }

    // Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // Get a reference to the GridView in the fragment_master_list xml layout file
        RecyclerView recyclerView = rootView.findViewById(R.id.images_grid_view);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager mLayoutManager = new
                GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the image resources to display
        MasterListAdapter mAdapter = new MasterListAdapter(AndroidImageAssets.getAll(),this);

        // Set the adapter on the GridView
        recyclerView.setAdapter(mAdapter);

        button = rootView.findViewById(R.id.next_activity_button);
        button.setBackground(getResources().getDrawable(R.drawable.disabled_shape));
        button.setEnabled(false);
        button.setOnClickListener(this);
        // Return the root view
        return rootView;
    }

    @Override
    public void itemClickedFromList(int position, View view) {
        setMatrixOfSelectedData(position, view);
    }

    private void setMatrixOfSelectedData(int position, View view) {
        int bodyPartNumber = position / 12;
        int listIndex = position - (12 * bodyPartNumber);

        switch (bodyPartNumber) {
            case 0:
                if(!flagHead){
                    flagHead = true;
                } else{
                    viewHead.setBackground(null);
                }
                headIndex = listIndex;
                viewHead = view;
                viewHead.setBackground(getResources().getDrawable(R.drawable.corners));
                break;
            case 1:
                if(!flagBody){
                    flagBody = true;
                } else{
                    viewBody.setBackground(null);
                }
                bodyIndex = listIndex;
                viewBody = view;
                viewBody.setBackground(getResources().getDrawable(R.drawable.corners));
                break;
            case 2:
                if(!flagLegs){
                    flagLegs = true;
                } else{
                    viewLegs.setBackground(null);
                }
                legIndex = listIndex;
                viewLegs = view;
                viewLegs.setBackground(getResources().getDrawable(R.drawable.corners));
                break;
            default:
                break;
        }

        boolean flagForEnablingButton = flagBody & flagHead & flagLegs;

        if(flagForEnablingButton){
            button.setBackground(getResources().getDrawable(R.drawable.shape));
        } else{
            button.setBackground(getResources().getDrawable(R.drawable.disabled_shape));
        }

        button.setEnabled(flagForEnablingButton);
    }

    @Override
    public void onClick(View view) {
        mCallback.onImagesSelected(headIndex, bodyIndex, legIndex);
    }
}