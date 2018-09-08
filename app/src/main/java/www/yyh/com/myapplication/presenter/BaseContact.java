package www.yyh.com.myapplication.presenter;

import android.support.annotation.StringRes;

/**
 * MVP模式中公共的基本契约
 * Created by 56357 on 2018/6/1
 */
public interface BaseContact {
     interface View<T extends Presenter>{

         void showError(@StringRes int str);
         void showLoading();
        void setPresenter(T presenter);
    }

    interface Presenter{
        void start();
        void destory();
    }
}
