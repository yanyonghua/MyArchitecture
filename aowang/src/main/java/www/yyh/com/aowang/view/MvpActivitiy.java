package www.yyh.com.aowang.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import www.yyh.com.aowang.factory.PresenterFactoryImpl;
import www.yyh.com.aowang.factory.PresenterMvpFactory;
import www.yyh.com.aowang.presenter.MvpPresenter;
import www.yyh.com.aowang.proxy.BaseMvpProxy;
import www.yyh.com.aowang.proxy.PresenterProxyInterface;

/**
 * Created by 56357 on 2018/9/7
 */
public class MvpActivitiy<V extends BaseView,P extends MvpPresenter<V>> extends AppCompatActivity implements PresenterProxyInterface<V,P>{


    private BaseMvpProxy<V,P> mProxy=new BaseMvpProxy<>(PresenterFactoryImpl.<V,P>createFactory(getClass()));
    private static final String PRESENTER_SAVE_KEY="presenter_save_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        Log.e("perfect-mvp","V onCreate");
        mProxy.onCreate((V) this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("perfect-mvp","V onResume");
        mProxy.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp","V onDestroy" );
        mProxy.onDestroy();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp","V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }
    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return  mProxy.getPresenterFactory();
    }

    @Override
    public P getPresenter() {
        return mProxy.getPresenter();
    }
}
