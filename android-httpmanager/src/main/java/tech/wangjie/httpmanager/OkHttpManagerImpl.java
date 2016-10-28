package tech.wangjie.httpmanager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

/**
 * Created by wangjie on 2016/10/28
 */

public class OkHttpManagerImpl extends HttpManager {

    private OkHttpClient httpClient;

    public OkHttpManagerImpl() {
        if (httpClient == null) {
            this.httpClient = new OkHttpClient();
        }
    }

    @Override
    public void enqueue(OkRequest request, HttpListener callback) {
        if (callback == null) {
            callback = HttpListener.defaultListener;
        }
        final HttpListener realCallback = callback;

        request.getRealCall().enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                deliveryFailResult(e, realCallback);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                try {
                    if (call.isCanceled()) {
                        deliveryFailResult(new IOException("This request is canceled~"), realCallback);
                        return;
                    }

                    if (response != null || !response.isSuccessful()) {
                        deliveryFailResult(new IOException("request failed ~, response code " + response.code()), realCallback);
                        return;
                    }

                    Object obj = realCallback.parseNetworkResponse(response);
                    deliverySuccessResult(obj, realCallback);
                } catch (Exception e) {
                    e.printStackTrace();
                    deliveryFailResult(new IOException(e.getMessage()), realCallback);
                } finally {
                    if (response != null && response.body() != null) {
                        response.body().close();
                    }
                }
            }
        });
    }

    @Override
    public Call newRealCall(OkRequest request) {
        return httpClient.newCall(request.getRealRequest());
    }

    private void deliveryFailResult(final Exception e, final HttpListener callback) {
        if (callback == null)
            return;
        CallbackDelivery.get().execute(new Runnable() {
            @Override
            public void run() {
                callback.onFail(e.getMessage());
                callback.onFinish();
            }
        });
    }

    private void deliverySuccessResult(final Object obj, final HttpListener callback) {
        if (callback == null)
            return;

        CallbackDelivery.get().execute(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(obj);
                callback.onFinish();
            }
        });
    }
}
