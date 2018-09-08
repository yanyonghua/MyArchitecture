package www.yyh.com.myapplication;

import android.support.annotation.StringRes;

/**
 * Created by 56357 on 2018/9/6
 */
public interface DataSource {

    /**
     * 只关注成功的接口
     * @param <T>
     */
    interface SucceedCallback<T>{
        void onDataLoaded(T t);
    }
    /**
     * 只关注失败的接口
     */
    interface FailedCallback<T>{
        void onDataNotAvailable(@StringRes int strRes);
    }
    /**
     * 同时包括了成功与失败的回调接口
     * @param <T> 任意类型
     */
    interface Callback<T> extends SucceedCallback<T>,FailedCallback<T>{

    }

}
