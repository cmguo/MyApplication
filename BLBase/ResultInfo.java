package com.xhb.blbase;

import com.xhb.prism.http.ResultConverterFactory;

public class ResultInfo extends ResultConverterFactory.ResultInfo<ResultInfo.Result>
{
    static class Result {
        int code;
        String msg;
        Object data;
    }

    public ResultInfo() {
        mStatusField = "code";
        mMessageField = "msg";
        mDataField = "data";
    }

    @Override
    public Result newResult() {
        return new Result();
    }

    @Override
    public void setStatus(Result result, int status) {
        result.code = status;
    }

    @Override
    public void setMessage(Result result, String message) {
        result.msg = message;
    }

    @Override
    public void setData(Result result, Object data) {
        result.data = data;
    }

    @Override
    public void check(Result result) {
        if (result.code != 0)
            throw new RuntimeException(result.msg);
    }

    @Override
    public Object data(Result result) {
        return result.data;
    }
}
