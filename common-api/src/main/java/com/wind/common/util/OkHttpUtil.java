package com.wind.common.util;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Title: OkHttpUtil
 * @Package com.wind.common.util
 * @Description: http请求工具类
 * @author wind
 * @date 2018/9/17 18:08
 * @version V1.0
 */
public class OkHttpUtil {

    private static Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final MediaType FILE = MediaType.parse("File/*");
    /**
     * 文件key
     */
    public static final String FILE_KEY = "file";

    private static OkHttpClient client = new OkHttpClient();

    /**
     * 异步请求失败回调处理
     */
    @FunctionalInterface
    interface FailCallback{
        /**
         * 异步请求失败回调处理
         * @param call
         * @param e
         */
        void call(Call call, IOException e);
    }

    /**
     * 异步请求成功回调处理
     */
    @FunctionalInterface
    interface OkCallback{
        /**
         * 异步请求成功回调处理
         * @param call
         * @param response
         */
        void call(Call call, Response response);
    }

    /**
     * json get请求
     * application/json; charset=utf-8
     * @param url
     * @return
     * @throws IOException
     */
    public static String getJson(String url){
        Request request = new Request.Builder().url(url).build();
        return execAsync(request);
    }


    /**
     * json post请求
     * application/json; charset=utf-8
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String postJson(String url, String json){
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        return execAsync(request);
    }

    /**
     * json put请求
     * application/json; charset=utf-8
     * @param url
     * @param json
     * @return
     */
    public static String putJson(String url, String json){
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).put(body).build();
        return execAsync(request);
    }


    /**
     * post表单请求
     * @param url
     * @param map
     * @return
     */
    public static String postForm(String url, Map<String, String> map){
        //创建表单请求体
        FormBody.Builder formBody = new FormBody.Builder();
        if(map != null && !map.isEmpty()){
            map.forEach(formBody::add);
        }
        Request request = new Request.Builder().url(url).post(formBody.build()).build();
        return execAsync(request);
    }

    /**
     * MultipartBody同时传递键值对参数和File对象
     * @param url
     * @param map
     * @param files
     * @return
     */
    public static String postMulti(String url, Map<String, String> map, List<File> files){
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(map != null && !map.isEmpty()){
            map.forEach(builder::addFormDataPart);
        }

        if(files != null && !files.isEmpty()){
            files.forEach(file -> {
                RequestBody body = RequestBody.create(FILE, file);
                builder.addFormDataPart(FILE_KEY, file.getName(), body);
            });
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        return execAsync(request);
    }

    /**
     * MultipartBody同时传递键值对参数和File对象
     * @param url
     * @param multipartBody
     * @return
     */
    public static String postMulti(String url, MultipartBody multipartBody){
        Request request = new Request.Builder().url(url).post(multipartBody).build();
        return execAsync(request);
    }

    /**
     * 下载文件
     * @param request
     * @param targetFile
     */
    public static void download(Request request, String targetFile){
        ResponseBody responseBody = getBody(request);
        InputStream in = null;
        FileOutputStream out = null;
        try {
            in = responseBody.byteStream();
            out = new FileOutputStream(targetFile, true);
            byte[] bytes = new byte[1024];
            int len;
            while((len = in.read(bytes, 0, bytes.length)) != -1){
                out.write(bytes, 0, len);
            }

            out.flush();
        } catch (IOException e) {
            logger.warn("file: " + targetFile + " download error", e);
        } finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    logger.warn("file: " + targetFile + " download error", e);
                }
            }

            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    logger.warn("file: " + targetFile + " download error", e);
                }
            }
        }
    }



    /**
     * 同步请求调用
     * @param request
     * @return
     */
    public static String execAsync(Request request){
        ResponseBody responseBody = getBody(request);
        String result = null;
        try {
            result =  responseBody != null ? responseBody.string() : null;
        } catch (IOException e) {
            logger.warn("execAsync error", e);
        }
        return result;
    }

    /**
     * 获取相应消息体
     * @param request
     * @return
     */
    public static ResponseBody getBody(Request request){
        ResponseBody responseBody = null;
        try {
            Response response = client.newCall(request).execute();
            responseBody = response.body();
        } catch (IOException e) {
            logger.warn("getBody error", e);
        }
        return responseBody;
    }

    /**
     * 异步调用
     * @param request
     */
    public static void execSync(Request request, OkCallback ok, FailCallback fail){
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                fail.call(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ok.call(call, response);
            }
        });
    }

}
