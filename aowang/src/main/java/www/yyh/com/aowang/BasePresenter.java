package www.yyh.com.aowang;

import java.util.Map;

import www.yyh.com.aowang.presenter.MvpPresenter;
import www.yyh.com.aowang.view.BaseView;

/**
 * Created by 56357 on 2018/9/7
 */
public abstract class BasePresenter<T extends BaseView> extends MvpPresenter<T> {

    public  abstract void  onStart(Map<String,String> parms,int requestCode);
}
