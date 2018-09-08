package www.yyh.com.myapplication.Net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 56357 on 2018/9/7
 */
public class RetrofitManager {
    private static RetrofitManager instance;
    private static String BASE_URL="http://www.example.com";
    private Retrofit retrofit;

    private RetrofitManager(){

    }

    public static RetrofitManager getInstance(){
        if (instance==null){
            instance=new RetrofitManager();
        }
        return instance;
    }

    public  Retrofit get(){
        if (instance.retrofit!=null){
            return instance.retrofit;
        }
        //构建okhettp
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                                    .addInterceptor(new HttpLoggingInterceptor())
                                    .readTimeout(10, TimeUnit.SECONDS)
                                    .connectTimeout(10, TimeUnit.SECONDS)
                                    .writeTimeout(10, TimeUnit.SECONDS).build();
    //
        instance.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();

        return instance.retrofit;
    }
}
