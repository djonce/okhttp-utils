package tech.wangjie.httpmanager;

import okhttp3.RequestBody;

/**
 * Created by wangjie on 2016/10/28
 */

public class JsonRequest extends OkRequest {
    public JsonRequest(String url) {
        super(url);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }
}
