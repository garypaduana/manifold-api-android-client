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

package com.manifoldtechnology.manifold_api_android_client;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.android.volley.Response;
import com.manifoldtechnology.manifold_api_android_client.api.trends.TrendsApi;
import com.manifoldtechnology.manifold_api_android_client.api.trends.TrendsApiImpl;
import com.manifoldtechnology.manifold_api_android_client.rules.SignUpTestRule;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static com.manifoldtechnology.manifold_api_android_client.rules.SignUpTestRule.defaultErrorListener;
import static com.manifoldtechnology.manifold_api_android_client.rules.SignUpTestRule.getAsyncResponseReceived;
import static com.manifoldtechnology.manifold_api_android_client.rules.SignUpTestRule.getAsyncTestSuccessful;
import static com.manifoldtechnology.manifold_api_android_client.rules.SignUpTestRule.getContext;
import static com.manifoldtechnology.manifold_api_android_client.rules.SignUpTestRule.getManifoldApiConnector;
import static com.manifoldtechnology.manifold_api_android_client.rules.SignUpTestRule.waitForAsyncResponse;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TrendsApiTest {

    @ClassRule
    public static SignUpTestRule apiTestRule = new SignUpTestRule();

    private TrendsApi trendsApi;
    private DateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss.SSS");

    @Before
    public void beforeTest(){
        trendsApi = new TrendsApiImpl(getContext(), getManifoldApiConnector());
    }

    @Test
    public void testGetInterestRates() throws InterruptedException {
        trendsApi.getInterestRates(Arrays.asList("Security"), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", "getInterestRates: " + response.toString());
                validateResponse(response);
                getAsyncTestSuccessful().set(true);
                getAsyncResponseReceived().set(true);
            }
        }, defaultErrorListener());
        waitForAsyncResponse();
    }

    @Test
    public void testGetMarketValue() throws InterruptedException {
        trendsApi.getMarketValue(Arrays.asList("Security"), 60 * 60 * 48 * 1000L, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", "getMarketValue: " + response.toString());
                validateResponse(response);
                getAsyncTestSuccessful().set(true);
                getAsyncResponseReceived().set(true);
            }
        }, defaultErrorListener());
        waitForAsyncResponse();
    }

    @Test
    public void testGetMarketVolume() throws InterruptedException {
        trendsApi.getMarketVolume(Arrays.asList("Security"), 60 * 60 * 48 * 1000L, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", "getMarketVolume: " + response.toString());
                validateResponse(response);
                getAsyncTestSuccessful().set(true);
                getAsyncResponseReceived().set(true);
            }
        }, defaultErrorListener());
        waitForAsyncResponse();
    }

    public void validateResponse(JSONObject response){
        for(String attribute : Arrays.asList("averageValueToMaturity", "transactions", "bids", "asks")){
            assertTrue(response.has(attribute));
        }
    }
}
