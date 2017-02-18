package com.cmdev.reelmarkets.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Vagdevi on 2/17/2017.
 */
public class ReelMarketsRestClient {
    private static final String BASE_URL = "http://lowcost-env.xf3ex7sdnh.us-west-2.elasticbeanstalk.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler resHandler) {
        client.get(mergeURL(url), params, resHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler resHandler) {
        client.post(mergeURL(url), params, resHandler);
    }

    private static String mergeURL(String path) {
        return BASE_URL + path;
    }

}
