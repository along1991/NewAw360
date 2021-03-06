package com.android.allwinner.newaw360;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.allwinner.newaw360.utils.LogUtil;
import com.android.allwinner.newaw360.utils.SPUtils;
import com.xinzhihui.mydvr.FileUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCameraBtn;

    private Spinner mSpinnerFront;
    private Spinner mSPinnerBehind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AppConfig.IS_PREALLOCATE) {
            LogUtil.d("qiansheng", "Jni:" + new FileUtil()._test());
        }

        mCameraBtn = (Button) findViewById(R.id.btn_camera);
        mCameraBtn.setOnClickListener(this);

        mSpinnerFront = (Spinner) findViewById(R.id.spinner_front);
        mSPinnerBehind = (Spinner) findViewById(R.id.spinner_behind);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerFront.setAdapter(adapter);
        mSPinnerBehind.setAdapter(adapter);


        switch ((int) SPUtils.get(MyApplication.getContext(), "KEY_FRONT_CAMERA_INDEX", 0)) {
            case 0:
                mSpinnerFront.setSelection(0);
                break;
            case 1:
                mSpinnerFront.setSelection(1);
                break;
            case 2:
                mSpinnerFront.setSelection(2);
                break;
            case 3:
                mSpinnerFront.setSelection(3);
                break;
            case 4:
                mSpinnerFront.setSelection(4);
                break;
            case 5:
                mSpinnerFront.setSelection(5);
                break;
            case 6:
                mSpinnerFront.setSelection(6);
                break;
            case 7:
                mSpinnerFront.setSelection(7);
                break;
            case 8:
                mSpinnerFront.setSelection(8);
                break;
        }

        switch ((int) SPUtils.get(MyApplication.getContext(), "KEY_BEHIND_CAMERA_INDEX", 4)) {
            case 0:
                mSPinnerBehind.setSelection(0);
                break;
            case 1:
                mSPinnerBehind.setSelection(1);
                break;
            case 2:
                mSPinnerBehind.setSelection(2);
                break;
            case 3:
                mSPinnerBehind.setSelection(3);
                break;
            case 4:
                mSPinnerBehind.setSelection(4);
                break;
            case 5:
                mSPinnerBehind.setSelection(5);
                break;
            case 6:
                mSPinnerBehind.setSelection(6);
                break;
            case 7:
                mSPinnerBehind.setSelection(7);
                break;
            case 8:
                mSPinnerBehind.setSelection(8);
                break;
        }

        mSpinnerFront.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppConfig.FRONT_CAMERA_INDEX = position;
                SPUtils.put(MyApplication.getContext(), "KEY_FRONT_CAMERA_INDEX", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSPinnerBehind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppConfig.BEHIND_CAMERA_INDEX = position;
                SPUtils.put(MyApplication.getContext(), "KEY_BEHIND_CAMERA_INDEX", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void makeDir(String path) {
        File dir = new File(path);
        if (dir.isFile()) {
            dir.delete();
        }
        if (!dir.exists()) {
            dir.mkdir();
        }
    }
}
