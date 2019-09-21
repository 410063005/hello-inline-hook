/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hellojni;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HelloInlineHookActivity extends AppCompatActivity {

    private static final String TAG = "HelloInlineHookActivity";

    private EditText mEtOne;
    private EditText mEtTwo;
    private TextView mTvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_inline_hook);

        mEtOne = findViewById(R.id.et_one);
        mEtTwo = findViewById(R.id.et_two);
        mTvValue = findViewById(R.id.tv_value);

        final HelloInlineHook cp = new HelloInlineHook();

        findViewById(R.id.btn_calc).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int one = getIntFromUiSafely(mEtOne);
                int two = getIntFromUiSafely(mEtTwo);
                int result = cp.calc(one, two);
                mTvValue.setText(Integer.toString(result));
            }
        });

        findViewById(R.id.btn_hook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp.hook();
            }
        });

        findViewById(R.id.btn_unhook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp.unhook();
            }
        });

    }

    private static int getIntFromUiSafely(EditText et) {
        try {
            return Integer.parseInt(et.getText().toString());
        } catch (NumberFormatException e) {
            Log.e(TAG, "bad content " + et.getText() + " ?", e);
            return 0;
        }
    }


}
