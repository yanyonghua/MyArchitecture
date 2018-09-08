package www.yyh.com.aowang.proxy;

import www.yyh.com.aowang.factory.PresenterMvpFactory;
import www.yyh.com.aowang.presenter.MvpPresenter;
import www.yyh.com.aowang.view.BaseView;

/**
 * Created by 56357 on 2018/9/7
 */
public interface PresenterProxyInterface<V extends BaseView,P extends MvpPresenter<V>> {

    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory
     */
    void  setPresenterFactory(PresenterMvpFactory<V,P> presenterFactory);

    /**
     * 获得Presenter工厂类
     * @return
     */
    PresenterMvpFactory<V,P> getPresenterFactory();
    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     */
    P getPresenter();
}
