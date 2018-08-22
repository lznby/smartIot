package com.lznby.smartiot.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author Lznby
 * @time 2018/6/1 12:48
 * Class Note: okhttp请求头含cookie，okhttp请求基类
 */
public class HttpUtilCookie {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback, RequestBody requestBody, String requestType,String cookie){
        OkHttpClient client = new OkHttpClient();
        Request request;
        if ("POST".equals(requestType)){
            request = new Request.Builder()
                    .url(address)
                    .addHeader("Cookie",cookie)
                    .post(requestBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(address)
                    .addHeader("Cookie",cookie)
                    .build();
        }
        client.newCall(request).enqueue(callback);
    }
}
