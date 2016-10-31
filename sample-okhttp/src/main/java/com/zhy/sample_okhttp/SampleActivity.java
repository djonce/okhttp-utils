package com.zhy.sample_okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import tech.wangjie.httpmanager.HttpManager;
import tech.wangjie.httpmanager.JsonHttpListener;
import tech.wangjie.httpmanager.JsonRequest;
import tech.wangjie.httpmanager.OkRequest;
import tech.wangjie.httpmanager.StringHttpListener;
import tech.wangjie.httpmanager.test.ApiModel;
import tech.wangjie.httpmanager.test.User;
import tech.wangjie.httpmanager.test.UserModel;

/**
 *
 * Created by djonce on 2016/10/27.
 */
public class SampleActivity extends Activity {

    private static final String TAG = SampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

    }

    private void getSampleData() {
        try {
            String api = "http://test.19ba.cn/api/user.json";
            final OkRequest request = new JsonRequest(api);

//            HttpManager.getInstance().enqueue(request, new StringHttpListener() {
//                @Override
//                public void onSuccess(String response) {
//                    Log.e(TAG, response);
//                    ((TextView)findViewById(R.id.textView))
//                            .setText(response.toString());
//                }
//
//                @Override
//                public void onFail(String error) {
//                    super.onFail(error);
//                    Log.e(TAG, error);
//                }
//            });

            HttpManager.getInstance().enqueue(request, new JsonHttpListener<ApiModel<User>>() {
                @Override
                public void onSuccess(ApiModel<User> response) {
                    Log.e(TAG, response.toString());
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doClick(View view) {
        getSampleData();
    }
}
