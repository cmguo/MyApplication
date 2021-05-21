package com.xhb.blbase;

import com.xhb.prism.http.HttpServices;

import java.util.List;
import java.util.ArrayList;

import okhttp3.Interceptor;

public class Services {

    public static <I> I get(Class<I> clazz) {
        return get(clazz, null);
    }
    
    public static <I> I get(Class<I> clazz, List<Interceptor> interceptors) {
        if (interceptors == null)
            interceptors = new ArrayList<>();
        interceptors.add(new TicketInterceptor());
        interceptors.add(new HostInterceptor());
        return HttpServices.get(clazz, interceptors,
                new ResultInfo());
    }
}
