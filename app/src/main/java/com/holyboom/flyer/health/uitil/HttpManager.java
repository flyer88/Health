package com.holyboom.flyer.health.uitil;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by shyboooy on 15/3/18.
 */
public class HttpManager {
    private static HttpManager manager;
    private OkHttpClient client;
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private OnHttpResponseListener listener;
    private HttpManager() {
        client = new OkHttpClient();
    }


    /**
     * singleton
     * @return
     */
    public static  HttpManager sharedManager() {
        if (manager == null) {
            manager = new HttpManager();
        }
        return manager;
    }

    public void setOnHttpResponseListener(OnHttpResponseListener listner) {
        this.listener = listner;
    }

    /**
     * HTTP POST
     * @param url
     * @param postJson
     */
    public void POST(String url,String postJson) {

        Request request = new Request.Builder()
                .post(RequestBody.create(MEDIA_TYPE, postJson))
                .url(url)
                .build();

        client.newCall(request).enqueue(new HttpCallBack());


    }

    /**
     * HTTP GET
     * @param url
     */

    public void GET(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new HttpCallBack());
    }

    private class HttpCallBack implements Callback {
        @Override
        public void onFailure(Request request, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Response response) throws IOException {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            if (listener != null) {
                listener.httpResponse(response.body().string());
            }
        }
    }
}