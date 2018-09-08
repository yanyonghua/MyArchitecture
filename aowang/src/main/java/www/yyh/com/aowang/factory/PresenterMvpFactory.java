package www.yyh.com.aowang.factory;

import www.yyh.com.aowang.presenter.MvpPresenter;
import www.yyh.com.aowang.view.BaseView;

/**
 * Created by 56357 on 2018/9/7
 */
public interface PresenterMvpFactory<V extends BaseView,P extends MvpPresenter<V>> {

    /**
     * 创建Presenter的接口方法
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
