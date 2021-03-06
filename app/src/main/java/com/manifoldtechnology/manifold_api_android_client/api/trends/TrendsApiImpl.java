/*
 * manifold-api-android-client
 *
 * MIT License
 *
 * Copyright (C) 2016, Manifold Technology (contact@manifoldtechnology.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.manifoldtechnology.manifold_api_android_client.api.trends;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.manifoldtechnology.manifold_api_android_client.R;
import com.manifoldtechnology.manifold_api_android_client.api.AbstractApi;
import com.manifoldtechnology.manifold_api_android_client.domain.ManifoldApiConnector;
import com.manifoldtechnology.manifold_api_android_client.service.Utilities;
import com.manifoldtechnology.manifold_api_android_client.service.request.JsonObjectRequestBasicAuth;
import com.manifoldtechnology.manifold_api_android_client.service.request.RequestQueueSingleton;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Default implementation of the <code>TrendsApi</code> using Volley for HTTP requests.
 */
public class TrendsApiImpl extends AbstractApi implements TrendsApi {

    public TrendsApiImpl(Context context, ManifoldApiConnector connector){
        super(context, connector);
    }

    @Override
    public void getInterestRates(List<String> assetTypes, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener){

        String url = Utilities.getUrlRoot(getConnector())
                .appendPath("Broker")
                .appendPath("stats")
                // TODO: This really should be all of the asset types but they aren't available yet
                .appendQueryParameter("assetType", "Security")
                .build().toString();

        JsonObjectRequestBasicAuth request = new JsonObjectRequestBasicAuth(Request.Method.GET, url,
                getConnector().getUsername(), getConnector().getPassword(), null,
                JsonObjectRequestBasicAuth.Type.JSON_OBJECT, successListener, errorListener);

        RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(request);
    }

    @Override
    public void getMarketVolume(List<String> assetTypes, long duration, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener){

        DateFormat dateFormat = new SimpleDateFormat(getContext().getString(R.string.full_timestamp_format));
        dateFormat.setTimeZone(TimeZone.getTimeZone(getContext().getString(R.string.timezone)));

        String endTime = dateFormat.format(new Date());
        String startTime = dateFormat.format(new Date(new Date().getTime() - duration));

        String url = Utilities.getUrlRoot(getConnector())
                .appendPath("Broker")
                .appendPath("stats")
                // TODO: This really should be all of the asset types but they aren't available yet
                .appendQueryParameter("assetType", "Security")
                .appendQueryParameter("startTime", startTime)
                .appendQueryParameter("endTime", endTime)
                .build().toString();

        JsonObjectRequestBasicAuth request = new JsonObjectRequestBasicAuth(Request.Method.GET, url,
                getConnector().getUsername(), getConnector().getPassword(), null,
                JsonObjectRequestBasicAuth.Type.JSON_OBJECT, successListener, errorListener);

        RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(request);
    }

    @Override
    public void getMarketValue(List<String> assetTypes, long duration, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener){

        DateFormat dateFormat = new SimpleDateFormat(getContext().getString(R.string.full_timestamp_format));
        dateFormat.setTimeZone(TimeZone.getTimeZone(getContext().getString(R.string.timezone)));

        String endTime = dateFormat.format(new Date());
        String startTime = dateFormat.format(new Date(new Date().getTime() - duration));

        String url = Utilities.getUrlRoot(getConnector())
                .appendPath("Broker")
                .appendPath("stats")
                // TODO: This really should be all of the asset types but they aren't available yet
                .appendQueryParameter("assetType", "Security")
                .appendQueryParameter("startTime", startTime)
                .appendQueryParameter("endTime", endTime)
                .build().toString();

        JsonObjectRequestBasicAuth request = new JsonObjectRequestBasicAuth(Request.Method.GET, url,
                getConnector().getUsername(), getConnector().getPassword(), null,
                JsonObjectRequestBasicAuth.Type.JSON_OBJECT, successListener, errorListener);

        RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(request);
    }
}
