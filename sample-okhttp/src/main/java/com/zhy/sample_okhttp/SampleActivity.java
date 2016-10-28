package com.zhy.sample_okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import tech.wangjie.httpmanager.HttpManager;
import tech.wangjie.httpmanager.JsonRequest;
import tech.wangjie.httpmanager.OkRequest;
import tech.wangjie.httpmanager.StringHttpListener;

/**
 * Created by djonce on 2016/10/27.
 */
public class SampleActivity extends Activity {

    private static final String TAG = SampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);


        try {
            String api = "http://test.19ba.cn/api/user.json";
            OkRequest request = new JsonRequest(api);

            HttpManager.getInstance().enqueue(request, new StringHttpListener() {
                @Override
                public void onSuccess(String response) {
                    Log.e(TAG, response);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
