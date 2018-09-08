package www.yyh.com.aowang.proxy;

import android.os.Bundle;
import android.util.Log;

import www.yyh.com.aowang.factory.PresenterMvpFactory;
import www.yyh.com.aowang.presenter.MvpPresenter;
import www.yyh.com.aowang.view.BaseView;

/**
 * Created by 56357 on 2018/9/7
 */
public class BaseMvpProxy<V extends BaseView,P extends MvpPresenter<V>> implements PresenterProxyInterface<V,P> {

    public BaseMvpProxy(PresenterMvpFactory<V, P> presenterFactory) {
        this.presenterFactory = presenterFactory;
    }

    private PresenterMvpFactory<V, P> presenterFactory;
    private P mPresenter;
    private Bundle mBundle;
    private String PRESENTER_KEY="presenter_key";
    private boolean mIsAttachView;

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.presenterFactory = presenterFactory;
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return presenterFactory;
    }

    @Override
    public P getPresenter() {
        if (presenterFactory!=null){
            if (mPresenter==null){
                mPresenter=  presenterFactory.createMvpPresenter();
                mPresenter.onCreatePresenter(mBundle == null ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }
        return mPresenter;
    }

    /**
     * 绑定View和Presenter
     * @param view
     */
    public void onCreate(V view){
        getPresenter();
        Log.e("perfect-mvp","Proxy onCreate");
        if (mPresenter!=null&&!mIsAttachView){
            mPresenter.onAttachMvpView(view);
            mIsAttachView=true;
        }

    }

    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        Log.e("perfect-mvp","Proxy onDestroy");
        if (mPresenter != null ) {
            onDetachMvpView();
            mPresenter.onDestroyPersenter();
            mPresenter = null;
        }
    }
    /**
     * 销毁Presenter
     */
    private void onDetachMvpView() {
        Log.e("perfect-mvp","Proxy onDetachMvpView");
        if (mPresenter != null && mIsAttachView) {
            mPresenter.onDetachMvpView();
            mIsAttachView = false;
        }
    }



    public void onResume() {

    }
    /**
     * 意外销毁的时候调用
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState() {
        Log.e("perfect-mvp","Proxy onSaveInstanceState");
        Bundle bundle = new Bundle();
        getPresenter();
        if(mPresenter != null){
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY,presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭恢复Presenter
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("perfect-mvp","Proxy onRestoreInstanceState");
        Log.e("perfect-mvp","Proxy onRestoreInstanceState Presenter = " + mPresenter);
        mBundle = savedInstanceState;
    }
}
