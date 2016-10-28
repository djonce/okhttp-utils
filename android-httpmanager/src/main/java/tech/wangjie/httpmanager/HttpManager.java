package tech.wangjie.httpmanager;


import okhttp3.Call;

/**
 *
 *
 * Created by wangjie on 2016/10/27
 */
public abstract class HttpManager {

    private static HttpManager httpManager;

    public static HttpManager getInstance() {
        if (httpManager == null) {
            synchronized (HttpManager.class) {
                if (httpManager == null) {
                    httpManager = new OkHttpManagerImpl();
                }
            }
        }
        return httpManager;
    }

    public abstract void enqueue(OkRequest request, HttpListener callback);

    public abstract Call newRealCall(OkRequest request);
}
