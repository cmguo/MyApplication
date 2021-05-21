package com.xhb.blbase;

import com.xhb.prism.http.URLInteceptor;

import java.net.MalformedURLException;
import java.net.URL;

class HostInterceptor extends URLInteceptor {
    @Override
    protected URL updateURL(URL url) {
        String s = url.toString();
        int n = s.indexOf('.');
        s = s.substring(0, n) + ".itest" + s.substring(n);
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            return url;
        }
    }
}
