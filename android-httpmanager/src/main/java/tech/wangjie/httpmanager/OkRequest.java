package tech.wangjie.httpmanager;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.RequestBody;

/**
 * Created by wangjie on 2016/10/27.
 */
public abstract class OkRequest {

    // 构建一个RealRequest (okhttp3.OkRequest)

    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;

    protected Object apiParam;

    protected okhttp3.Request realRequest;
    protected okhttp3.Request.Builder builder = new okhttp3.Request.Builder();

    /**
     * 初始化一些基本参数 url , tag , headers
     */
    private void initBaseParam() {
        builder.url(url).tag(tag);
        appendHeaders();

        // 解析参数并组装成ok request
        realRequest = builder.build();
    }

    public OkRequest(String url) {
        this.url = url;
        initBaseParam();
    }

    // 对象参数
    public OkRequest(Object apiParam) {
        this.apiParam = apiParam;
        initBaseParam();
    }

    public OkRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers) {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;
        initBaseParam();
    }


    public Call getRealCall() {
        return HttpManager.getInstance().newRealCall(this);
    }


    public okhttp3.Request getRealRequest() {
        return realRequest;
    }

    protected abstract RequestBody buildRequestBody();

    protected void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    public OkRequest createDefaultRequest() {
        RequestBody requestBody = buildRequestBody();

        return this;
    }
}
