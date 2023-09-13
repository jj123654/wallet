package com.yns.wallet.bean;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * json形式传参的body
 *
 * @author liyang
 */
public class JsonRequestBody extends RequestBody {

    MediaType mMediaType;
    String mContent;
    RequestBody mRequestBody;

    public JsonRequestBody(MediaType mediaType, String content) {
        mMediaType = mediaType;
        mContent = content;
        mRequestBody = RequestBody.create(mediaType, content);
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        mRequestBody.writeTo(sink);
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public RequestBody getRequestBody() {
        return mRequestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        mRequestBody = requestBody;
    }
}
