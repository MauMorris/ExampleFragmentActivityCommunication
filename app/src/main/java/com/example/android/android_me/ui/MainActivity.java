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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.android_me.R;

import java.util.ArrayList;

// This activity is responsible for displaying the master list of all images
public class MainActivity extends AppCompatActivity implements OnImageFromAndroidListClickListener {
    public static final String HEAD_INDEX = "head_index";
    public static final String BODY_INDEX = "body_index";
    public static final String LEG_INDEX = "leg_index";

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.android_me_linear_layout) != null){
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImagesSelected(int headIndex, int bodyIndex, int legIndex) {
        if(!mTwoPane){
            Intent intent = setIntentForAndroidMe(headIndex, bodyIndex, legIndex);
            startActivity(intent);
        } else {

        }
    }

    private Intent setIntentForAndroidMe(int headIndex, int bodyIndex, int legIndex) {
        Bundle bundle = new Bundle();
        bundle.putInt(HEAD_INDEX, headIndex);
        bundle.putInt(BODY_INDEX, bodyIndex);
        bundle.putInt(LEG_INDEX, legIndex);

        Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(bundle);

        return intent;
    }
}