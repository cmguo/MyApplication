package com.xhb.business;

import java.util.List;
import java.util.ArrayList;

import okhttp3.Interceptor;

public class Services {

    public static BookService bookService() {
        return com.xhb.blbase.Services.get(BookService.class);
    }
}
