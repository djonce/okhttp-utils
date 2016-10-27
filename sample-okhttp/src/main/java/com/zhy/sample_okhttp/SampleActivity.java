package com.zhy.sample_okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import tech.wangjie.httpmanager.HttpManager;
import tech.wangjie.httpmanager.Request;

/**
 * Created by djonce on 2016/10/27.
 */
public class SampleActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        HttpManager httpManager = new HttpManager();

        Request request = Request.builder();
        httpManager.enqueue(request, new Call() {
            @Override
            public okhttp3.Request request() {
                return null;
            }

            @Override
            public Response execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(okhttp3.Callback responseCallback) {

            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }
        });
    }
}
