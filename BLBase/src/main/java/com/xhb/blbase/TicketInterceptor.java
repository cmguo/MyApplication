package com.xhb.blbase;

import com.xhb.prism.http.HeadersInteceptor;

import okhttp3.Headers;

class TicketInterceptor extends HeadersInteceptor {

    @Override
    protected boolean updateHeaders(Headers.Builder builder) {
        builder.add("userid", "17261802");
        return true;
    }
}
