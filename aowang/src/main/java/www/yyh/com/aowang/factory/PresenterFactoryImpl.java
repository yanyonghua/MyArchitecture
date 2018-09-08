package www.yyh.com.aowang.factory;

import www.yyh.com.aowang.presenter.MvpPresenter;
import www.yyh.com.aowang.view.BaseView;

/**
 * Created by 56357 on 2018/9/7
 */
public class PresenterFactoryImpl<V extends BaseView, P extends MvpPresenter<V>> implements PresenterMvpFactory<V, P> {
    private Class<P> pClass;

    private PresenterFactoryImpl(Class<P> pClass) {

        this.pClass = pClass;
    }

    @Override
    public P createMvpPresenter() {
        try {
            return pClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
    /**
     * 根据注解创建Presenter的工厂实现类
     * @param aClass 需要创建Presenter的V层实现类
     * @param <V> 当前View实现的接口类型
     * @param <P> 当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends BaseView, P extends MvpPresenter<V>> PresenterMvpFactory<V, P> createFactory(Class<?> aClass) {
        CreatePresenter createPresenter = aClass.getAnnotation(CreatePresenter.class);
        Class<P> pClass = null;
        if (createPresenter != null) {
            pClass = (Class<P>) createPresenter.value();
        }

        return pClass == null ? null : new PresenterFactoryImpl<V, P>(pClass);
    }
}
