package com.lznby.smartiot.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author Lznby
 * @time 2018/6/1 12:48
 * Class Note: okhttp请求头
 */
public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback, RequestBody requestBody, String requestType){
        OkHttpClient client = new OkHttpClient();
        Request request;
        if ("POST".equals(requestType)){
            request = new Request.Builder()
                    .url(address)
//                    .header("Cookie","")
                    .post(requestBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(address)
//                    .header("Cookie","")
                    .build();
        }
        client.newCall(request).enqueue(callback);
    }
}
