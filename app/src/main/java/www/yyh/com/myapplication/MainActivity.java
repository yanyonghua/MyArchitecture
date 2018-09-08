package www.yyh.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import www.yyh.com.myapplication.contact.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private String TAG="MainActivity";
    private Subscription mSubscription;
    String porms="再别康桥_百度汉语\n" +
            "\n" +
            "作者：徐志摩\n" +
            "轻轻的我走了，\n" +
            "正如我轻轻的来；\n" +
            "我轻轻的招手，\n" +
            "作别西天的云彩。\n" +
            "\n" +
            "那河畔的金柳，\n" +
            "是夕阳中的新娘；\n" +
            "波光里的艳影，\n" +
            "在我的心头荡漾。\n" +
            "\n" +
            "软泥上的青荇，\n" +
            "油油的在水底招摇；\n" +
            "在康河的柔波里，\n" +
            "我甘心做一条水草！\n" +
            "\n" +
            "那榆荫下的一潭，\n" +
            "不是清泉，是天上虹；\n" +
            "揉碎在浮藻间，\n" +
            "沉淀着彩虹似的梦。\n" +
            "\n" +
            "寻梦？撑一支长篙，\n" +
            "向青草更青处漫溯；\n" +
            "满载一船星辉，\n" +
            "在星辉斑斓里放歌。\n" +
            "\n" +
            "但我不能放歌，\n" +
            "悄悄是别离的笙箫；\n" +
            "夏虫也为我沉默，\n" +
            "沉默是今晚的康桥！\n" +
            "\n" +
            "悄悄的我走了，\n" +
            "正如我悄悄的来；\n" +
            "我挥一挥衣袖，\n" +
            "不带走一片云彩。";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, LoginActivity.class));
    }




    public void observe(View view) {

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {

                try {


                    String path=getFilesDir().getAbsolutePath()+"/"+"test.txt";
                    Log.e(TAG, "subscribe: "+path );

                    FileReader reader = new FileReader(path);
                    BufferedReader br = new BufferedReader(reader);
                    String str;
                    while ((str = br.readLine()) != null && !emitter.isCancelled()) {
                        while (emitter.requested() == 0) {
                            if (emitter.isCancelled()) {
                                break;
                            }
                        }
                        emitter.onNext(str);
                    }
                    br.close();
                    reader.close();

                    emitter.onComplete();

                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        mSubscription =s;
                        s.request(1);
                    }

                    @Override
                    public void onNext(String string) {
                        Log.e(TAG, "onNext: zbkq "+string );


                        try {
                            Thread.sleep(2000);
                            mSubscription.request(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Log.e(TAG, "onNext: "+e );
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    public void observe1(View view) {
        OutputStream os= null;
        try {
            os = openFileOutput("test.txt", Context.MODE_PRIVATE);
            os.write(porms.getBytes());
            os.close();
            Log.e(TAG, "observe1: "+"写入完成" );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
