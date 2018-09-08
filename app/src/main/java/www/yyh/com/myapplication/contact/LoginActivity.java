package www.yyh.com.myapplication.contact;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import www.yyh.com.myapplication.R;
import www.yyh.com.myapplication.base.PresenterActivity;

/**
 * Created by 56357 on 2018/9/6
 */
public class LoginActivity extends PresenterActivity<LoginContact.Presenter> implements LoginContact.View{


    EditText name;

    EditText password;

    @Override
    protected void initWidget() {
        super.initWidget();
        name=  findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, R.string.login_success,Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    protected LoginContact.Presenter initPresenter() {
        return new LoginPresenter(this);
    }


    public void login(View view){
        mPresenter.login(name.getText().toString().trim(),password.getText().toString().trim());
    }
}
