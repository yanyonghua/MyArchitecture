package www.yyh.com.aowang.factory;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import www.yyh.com.aowang.presenter.MvpPresenter;

/**
 * Created by 56357 on 2018/9/7
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends MvpPresenter> value();
}
