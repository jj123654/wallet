package com.yns.wallet.service;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.yns.wallet.bean.JsonRequestBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import kotlin.Pair;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 自动打印网络请求的request和response内容
 *
 * @author liyang
 */
public class LoggingInterceptor implements Interceptor {
    private static final String LOG_TAG = "httpclient";

    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        String url = request.url().toString();
        Uri uri = Uri.parse(url);
        boolean noLog = "GET".equals(request.method()) && !TextUtils.isEmpty(uri.getPath()) && uri.getPath().lastIndexOf(".") != -1;
        long t1 = System.nanoTime();//请求发起的时间
        if (!noLog) {
            String requestLog = getRequestLog(request);
            Log.i(LOG_TAG, requestLog);
        }
        try {
            Response response = chain.proceed(request);
            if (!noLog) {
                Log.i(LOG_TAG, getResponseLog(request, response, t1));
            }
            return response;
        } catch (Exception e) {
            Log.i(LOG_TAG, getExceptionLog(request, t1, e));
            Log.e(LOG_TAG, "error from url " + decodeUrl(request.url().toString()), e);
            throw e;
        }
    }


    private String getRequestLog(okhttp3.Request request) {
        String method = request.method();
        RequestBody requestBody = request.body();
        String requestStr = "";
        switch (method) {
            case "GET":
                JSONObject requestMsg = new JSONObject();
                String url = request.url().toString();
                int index = url.indexOf("?");
                if (index < 0) {
                    break;
                }
                url = URLDecoder.decode(url.substring(index + 1));
                String[] query = url.split("&");
                for (int i = 0; i < query.length; i++) {
                    String[] str = query[i].split("=");
                    try {
                        requestMsg.put(str[0], str.length > 1 ? str[1] : "");
                    } catch (JSONException ignored) {
                    }
                }
                break;
            default:
                requestStr = bodyToString(requestBody);
                break;
        }
        Headers headers = request.headers();
        int size = headers.size();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < size; i++) {
            try {
                jsonObject.put(headers.name(i), headers.value(i));
            } catch (JSONException e) {
            }
        }
        return String.format("sending request to\nurl:[%s]\nmethod:[%s] contentType:[%s]\nheader:[%s]\n%s:[%s]",
                decodeUrl(request.url().toString()), method, requestBody == null ? "null" : requestBody.contentType(), jsonObject.toString(), "GET".equalsIgnoreCase(method) ? "query" : "body", requestStr);
    }

    private String bodyToString(RequestBody requestBody) {
        if (requestBody instanceof FormBody) {
            JSONObject jsonObject = new JSONObject();
            FormBody formBody = (FormBody) requestBody;
            for (int i = 0; i < formBody.size(); i++) {
                try {
                    jsonObject.put(formBody.name(i), formBody.value(i));
                } catch (JSONException ignored) {
                }
            }
            return jsonObject.toString();
        } else if (requestBody instanceof MultipartBody) {
            MultipartBody body = (MultipartBody) requestBody;
            List<MultipartBody.Part> parts = body.parts();
            StringBuilder s = new StringBuilder("multi_parts_start ");
            for (int i = 0; i < parts.size(); i++) {
                MultipartBody.Part part = parts.get(i);
                s.append("parts_index:").append(i).append(bodyToString(part.body()));
            }
            //TODO 尚未完成打印
            return s.toString();
        } else if (requestBody instanceof JsonRequestBody) {
            JsonRequestBody body = (JsonRequestBody) requestBody;
            return body.getContent();
        } else {
            return "_文件";
        }
    }

    private String getResponseLog(okhttp3.Request request, Response response, long requestTime) {
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        long t2 = System.nanoTime();//收到响应的时间
        try {
//            long contentLength = response.body().contentLength();
//            if (contentLength < 0) {
//                return String.format("received response from\nurl:[%s]\ntime:[%.1fms]\nresponse:[%s]",
//                        decodeUrl(request.url().toString()), (t2 - requestTime) / 1e6d,
//                        "contentLength = -1");
//            }
            String log = "";
            Headers h = response.headers();
            JSONObject jsonObject = new JSONObject();
            Iterator<Pair<String, String>> i = h.iterator();
            while (i.hasNext()) {
                Pair<String, String> next = i.next();
                jsonObject.put(next.getFirst(), next.getSecond());
            }
            MediaType mediaType = response.body().contentType();
            if (mediaType != null) {
                if (mediaType.subtype().contains("json") || mediaType.subtype().contains("plain")) {
                    ResponseBody responseBody = response.peekBody(1024 * 1024);
                    log = responseBody.string();
                }
            }
            if (TextUtils.isEmpty(log)) {
                log = "no string response for this request";
            }
            return String.format(Locale.US, "response from\nurl:[%s]\ntime:[%.1fms]\nresponseCode:[%s] contentType:[%s]\nheader:[%s]\nresponse:[%s]",
                    decodeUrl(request.url().toString()), (t2 - requestTime) / 1e6d, String.valueOf(response.code()), mediaType == null ? "mediaType is Null" : mediaType.toString(),
                    jsonObject, log);
        } catch (Exception e) {
            String log = e.toString();
            return String.format("an exception occur \nurl:[%s]\ntime:[%.1fms]\nerror:[%s]",
                    decodeUrl(request.url().toString()), (t2 - requestTime) / 1e6d,
                    log);
        }
    }

    private String getExceptionLog(okhttp3.Request request, long requestTime, Exception e) {
        long t2 = System.nanoTime();//收到响应的时间
        return String.format("exception  from\nurl:[%s]\ntime:[%.1fms]\nexception:[%s]\nresponse:[%s]",
                decodeUrl(request.url().toString()), (t2 - requestTime) / 1e6d, e.getClass().getName(),
                e.getMessage());
    }

    private String decodeUrl(String url) {
        return URLDecoder.decode(url);
    }


}
