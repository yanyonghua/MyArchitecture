package www.yyh.com.myapplication.contact;

import www.yyh.com.myapplication.presenter.BaseContact;

/**
 * Created by 56357 on 2018/9/6
 */
public interface LoginContact {
    interface View extends BaseContact.View<LoginContact.Presenter>{
        //登录成功
        void loginSuccess();
    }

    interface Presenter extends BaseContact.Presenter{
        //发起一个登录
        void login(String name,String password);
    }

}
