package com.exa.framelib_rrm.retrofit;

import com.google.gson.Gson;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.http.FieldMap;
import retrofit2.http.Url;

/**
 * Created by acer on 2017/7/24.
 * 使用Retrofit时，几个可以公用的方法
 *
 * 使用示例：
 * ApiTest api = RetrofitHelper.createApi(ApiTest.class);
 * Observable<Bean> os = ApiUtils.get(api, "123", Bean.class);
 */
public class ApiUtils {

    private static Gson gson = new Gson();

    /**把json字符串转换为实体类*/
    private static <T> Observable<T> map(Observable<String> os, final Class<T> clazz){
        return os.map(new Function<String, T>() {
            @Override
            public T apply(@NonNull String s) throws Exception {
                return gson.fromJson(s, clazz);
            }
        });
    }

    /**GET请求，无参数，返回类型为实体类*/
    public static <T> Observable<T> get(ApiService api, @Url String url, Class<T> clazz){
        Observable<String> os = api.get(url);
        return map(os, clazz);
    }

    /**POST请求，无参数，返回类型为实体类*/
    public static <T> Observable<T> post(ApiService api, @Url String url, Class<T> clazz){
        Observable<String> os = api.post(url);
        return map(os, clazz);
    }

    /**POST请求，有参数，返回类型为实体类*/
    public static <T> Observable<T> post(ApiService api, @Url String url, Class<T> clazz, @FieldMap Map<String, String> map){
        Observable<String> os = api.postWithParams(url, map);
        return map(os, clazz);
    }

}
