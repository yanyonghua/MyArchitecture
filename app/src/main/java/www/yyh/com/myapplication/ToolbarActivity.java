package www.yyh.com.myapplication;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import www.yyh.com.myapplication.base.Activity;

/**
 * Created by 56357 on 2018/9/6
 */
public abstract class ToolbarActivity extends Activity {
    protected Toolbar mToolbar;
    @Override
    protected void initWidget() {
        super.initWidget();
       Toolbar toolbar= findViewById(R.id.toolBar);
       initTool(toolbar);
    }

    private void initTool(Toolbar toolbar) {
        mToolbar=toolbar;
        if (toolbar!=null){
            setSupportActionBar(toolbar);
        }
        initTitleNeedBack();
    }

    protected void initTitleNeedBack() {
        //设置左上角的返回按钮为实际的返回效果
       ActionBar actionBar= getSupportActionBar();
       if (actionBar!=null){
           actionBar.setDisplayHomeAsUpEnabled(true);
           actionBar.setHomeButtonEnabled(true);
       }
    }
}
