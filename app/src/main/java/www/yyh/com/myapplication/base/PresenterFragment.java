package www.yyh.com.myapplication.base;

import android.content.Context;

import www.yyh.com.myapplication.presenter.BaseContact;

/**
 * 基础的view
 * Created by 56357 on 2018/9/6
 */
public abstract class PresenterFragment<Presenter extends BaseContact.Presenter> extends BaseFragment implements BaseContact.View<Presenter> {
    protected Presenter presenter;
    /**
     * 初始化Presenter
     * @return Presenter
     */
    protected abstract Presenter initPresenter();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //在界面onAttach之后就出发初始化Presenter
        initPresenter();
    }

    @Override
    public void showError(int str) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null)
        {
            presenter.destory();
        }
    }
}
