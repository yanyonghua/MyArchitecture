package www.yyh.com.myapplication.Net;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import www.yyh.com.myapplication.DataSource;
import www.yyh.com.myapplication.R;
import www.yyh.com.myapplication.model.BaseResponse;

/**
 * Created by 56357 on 2018/9/7
 */
public class AccountHelper {
private static String url="http://www.wanandroid.com/tools/mockapi/4037/bar";
    private static String TAG="AccountHelper";

    public static void login(String name, String password, final DataSource.Callback callback) {
        if (name.equalsIgnoreCase(password)){//正确的
            url="http://www.wanandroid.com/tools/mockapi/4037/foo";
        }

        RetrofitManager.getInstance().get().create(Service.class)
                .getSomeThing(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) {
                        callback.onDataLoaded(baseResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        callback.onDataNotAvailable(R.string.login_error);
                        Log.e(TAG, "accept: "+throwable );
                    }
                });
    }
}
