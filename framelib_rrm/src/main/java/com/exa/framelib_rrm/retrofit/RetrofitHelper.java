package com.exa.framelib_rrm.retrofit;

import android.text.TextUtils;

import com.exa.framelib_rrm.BuildConfig;
import com.exa.framelib_rrm.app.BaseApp;
import com.exa.framelib_rrm.base.model.system.Constants;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by acer on 2017/6/14.
 * 如果需要有多个baseUrl，HashMap<String, Retrofit>
 */
public class RetrofitHelper {
    //public static final String DEFAULT_BASE_URL = Constants.LINK_MAIN;
    public static String DEFAULT_BASE_URL = null;
    private static volatile RetrofitHelper mInstance;
    private Gson gson;
    private HashMap<String, Retrofit> retrofits;

    /**初始化默认的baseUrl，只能初始化一次*/
    public static void init(String baseUrl) {
        if (!TextUtils.isEmpty(baseUrl)) {
            if(TextUtils.isEmpty(DEFAULT_BASE_URL)){
                DEFAULT_BASE_URL = baseUrl;
            }else {
                throw new RuntimeException("Retrofit的DEFAULT_BASE_URL已经初始化过了");
            }
        } else {
            throw new RuntimeException("参数不能为空");
        }
    }

    /**得到本类的单例对象*/
    private static RetrofitHelper getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitHelper();
                    mInstance.retrofits = new HashMap<String, Retrofit>();
                }
            }
        }
        return mInstance;
    }

    public static <T> T createApi(Class<T> clazz) {
        if (DEFAULT_BASE_URL != null) {
            return getInstance().createApiInternal(clazz, DEFAULT_BASE_URL);
        } else {
            throw new RuntimeException("RetrofitHelper未进行初始化");
        }
    }

    public static <T> T createApi(Class<T> clazz, String baseUrl) {
        return getInstance().createApiInternal(clazz, baseUrl);
    }

    private <T> T createApiInternal(Class<T> clazz, String baseUrl) {
        Retrofit r = retrofits.get(baseUrl);
        if (r == null) {
            r = createNewRetrofit(baseUrl);
            retrofits.put(baseUrl, r);
        }
        return r.create(clazz);
    }

    private Retrofit createNewRetrofit(String baseUrl) {
        if (gson == null) {
            gson = new Gson();
        }

        return new Retrofit.Builder()//java.lang.IllegalArgumentException: baseUrl must end in /: http://192.168.56.1/mobile/index.php?act=
                .client(getOkHttpClient())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())//要写在最上面？
                //.addConverterFactory(new LogoutConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public Gson getGson() {
        return gson != null ? gson : new Gson();
    }

    public OkHttpClient getOkHttpClient() {
        //设置缓存路径
        File cacheFile = new File(BaseApp.getInstance0().getCacheDir(), "cacheData");
        //设置缓存大小
        Cache cache = new Cache(cacheFile, 10 * 1024 * 1024);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpRequestInterceptor())
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new CacheInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
                .cache(cache)
                .connectTimeout(Constants.DEFAILT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
//                builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                    @Override
//                    public void log(String message) {
//                        if(!TextFormatUtils.isEmpty(message)){
//                            if(message.startsWith("{") || message.startsWith("[")){
//                                FrameLifeCircleLogUtils.i("收到返回的数据："+message);
//                            }
//                        }
//                    }
//                }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

    //为了应对.addConverterFactory(new LogoutConverterFactory())这样需要自定义ConverterFactory的情况
    public static void replaceRetrofit(String baseUrl, Retrofit retrofit) {
        if (baseUrl != null && retrofit != null) {
            getInstance().retrofits.put(baseUrl, retrofit);
        } else {
            throw new RuntimeException("参数不能为null");
        }
    }

}
