package www.yyh.com.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by 56357 on 2018/9/6
 */
public abstract class Activity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (initArgs(getIntent().getExtras())){
            int layoutId=getContentLayout();
            setContentView(layoutId);
            initBefore();
            initWidget();
            initData();
        }

    }
    /*
     * 初始化数据
     * */
    protected void initData() {

    }
    /*
     * 初始化控件
     * */
    protected void initWidget() {
        ButterKnife.bind(this);
    }

    protected void initBefore() {

    }

    protected boolean initArgs(Bundle extras) {
        return true;
    }


    protected abstract int getContentLayout();

    @Override
    public void onBackPressed() {
        List<Fragment> fragments =getSupportFragmentManager().getFragments();
        if (fragments.size()>0&&fragments!=null){
            for (Fragment fragment : fragments) {
                if (fragment instanceof BaseFragment){
                    if (((BaseFragment) fragment).onBackPressed()){
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
