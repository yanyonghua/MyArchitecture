package www.yyh.com.myapplication.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.plugins.RxJavaPlugins;
import www.yyh.com.myapplication.Net.Exception;
import www.yyh.com.myapplication.contact.LoginActivity;
import www.yyh.com.myapplication.model.BaseResponse;

/**
 * Created by 56357 on 2018/9/7
 */
public class MyApp extends Application{
    private Handler mainHandler ;
    @Override
    public void onCreate() {
        super.onCreate();
        mainHandler=new Handler(getApplicationContext().getMainLooper());
        //拦截器的作用
        RxJavaPlugins.setOnObservableSubscribe(new BiFunction<Observable, Observer, Observer>() {
            @Override
            public Observer apply(Observable observable, Observer observer) throws java.lang.Exception {
                return new ObservableSubscribeHooker(observer);
            }
        });
    }

    class ObservableSubscribeHooker<T> implements Observer<T> {

        private Observer<T> actual;
        private Context context;

        private String TAG;

        public ObservableSubscribeHooker(Observer<T> observer) {
            this.actual = observer;

        }

        @Override
        public void onSubscribe(Disposable d) {
            actual.onSubscribe(d);
        }

        @Override
        public void onNext(T t) {
            hookNext(t);
            actual.onNext(t);
        }

        private void hookNext(T t) {

            if (t instanceof BaseResponse) {//此处判断Token是否过期
                BaseResponse baseResponse = (BaseResponse) t;
                if (baseResponse.getCode() == 100) {

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "登录过期", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    throw new Exception.TokenExpired();
                }
            }
        }

        @Override
        public void onError(Throwable e) {

            if (e instanceof ConnectException){
                Log.e(TAG, "Connect failed: ", e);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "无网络连接", Toast.LENGTH_SHORT).show();
                    }
                });
                actual.onError(new Exception.OffLine());
                return;
            }

            if (e instanceof SocketTimeoutException) {
                Log.e(TAG, "Time out ", e);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "连接超时", Toast.LENGTH_SHORT).show();
                    }
                });
                actual.onError(new Exception.TimeOut());
                return;
            }
            actual.onError(e);
        }

        @Override
        public void onComplete() {
            actual.onComplete();
        }
    }
}
