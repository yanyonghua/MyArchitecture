package www.yyh.com.aowang;

import www.yyh.com.aowang.contact.LoginPresenter;
import www.yyh.com.aowang.contact.V;
import www.yyh.com.aowang.factory.CreatePresenter;

/**
 * Created by 56357 on 2018/9/7
 */
@CreatePresenter(LoginPresenter.class)
public class LoginActivity extends BaseActivity<V.LoginView,LoginPresenter> implements V.LoginView {



    @Override
    protected void addEvent() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void getDataFromService(int requestCode) {

    }
}
