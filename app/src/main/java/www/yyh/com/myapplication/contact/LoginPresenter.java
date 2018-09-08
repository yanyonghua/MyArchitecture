package www.yyh.com.myapplication.contact;

import android.text.TextUtils;

import www.yyh.com.myapplication.DataSource;
import www.yyh.com.myapplication.Net.AccountHelper;
import www.yyh.com.myapplication.R;
import www.yyh.com.myapplication.presenter.BasePresenter;

/**
 * Created by 56357 on 2018/9/7
 */
public class LoginPresenter extends BasePresenter<LoginContact.View> implements LoginContact.Presenter,DataSource.Callback {

    public LoginPresenter(LoginContact.View view) {

        super(view);
    }

    @Override
    public void login(String name, String password) {
        start();
        final  LoginContact.View view=getView();
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(password)){
            view.showError(R.string.data_account_login_invalid_parameter);
        }else {
                AccountHelper.login(name,password,this);
        }
    }


    @Override
    public void onDataLoaded(Object o) {
        final  LoginContact.View view=getView();
        if (view!=null)
        view.loginSuccess();
    }

    @Override
    public void onDataNotAvailable(int strRes) {
        final  LoginContact.View view=getView();
        if (view!=null)
            view.showError(strRes);
    }
}
