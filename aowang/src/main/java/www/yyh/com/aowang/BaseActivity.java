package www.yyh.com.aowang;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import www.yyh.com.aowang.presenter.MvpPresenter;
import www.yyh.com.aowang.view.BaseView;
import www.yyh.com.aowang.view.MvpActivitiy;

/**
 * 调用的此基类
 * Created by 56357 on 2018/9/7
 */
public abstract class BaseActivity<V extends BaseView,P extends MvpPresenter<V>> extends MvpActivitiy<V,P>implements BaseView{

    protected LinearLayout mContentView;
    protected Toolbar mToolBar;

    protected TextView tvToolbarTitle;//标题
    protected TextView tvToolbarRight;//右边文本
    protected ImageView imgSearch;//搜索
    protected ImageView imgAdd;//新增

    //http请求码
    public static final int SEARCH =1;
    public static final int ADD =2;
    public static final int UPDATE =3;
    public static final int DELETE =4;
    public static final int REFER =9;
    public static final int UNREFER =10;
    public static final int SEARCH_DETAIL= 6;
    public static final String ENTITY="ENTITY";
    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        setContentViewLayout(getContentLayoutId());
        initView();
        initData();
        addEvent();
    }

    protected abstract void addEvent();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getContentLayoutId();

    private void setContentViewLayout(@LayoutRes int contentLayoutId) {
        LayoutInflater.from(this).inflate(contentLayoutId,mContentView,true);
    }

    private void initContentView() {
        //找到根View
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        mContentView =new LinearLayout(this);
        mContentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        mContentView.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(mContentView);
        if (showToolBar()){
            addToolBar();
        }
    }

    private void addToolBar() {
        View toolBar=getLayoutInflater().inflate(R.layout.toolbar_layout,mContentView,true);
       mToolBar = toolBar.findViewById(R.id.toolbar);
       if (mToolBar==null){
           throw new NullPointerException("mToolbar can not be null");
       }
        setSupportActionBar(mToolBar);
        //不显示toolbar标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp());
        tvToolbarTitle = mToolBar.findViewById(R.id.tv_title);
        tvToolbarRight = mToolBar.findViewById(R.id.tv_right);
        imgSearch = mToolBar.findViewById(R.id.iv_search);
        imgAdd = mToolBar.findViewById(R.id.iv_add);
    }
    /**
     *
     * @return 是否显示返回键
     */
    protected boolean showHomeAsUp() {
        return true;
    }

    protected boolean showToolBar() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * 设置居中标题
     */
    public void setToolbarTitle(String title) {
        tvToolbarTitle.setText(title);
    }
    /**
     *
     * 设置右边文字
     */
    public void setToolbarRightText(String title) {
        tvToolbarRight.setText(title);
    }


    //搜索按钮监听
    protected void setSearchBtnOnclickListener(View.OnClickListener l){
        if (l !=null){
            imgSearch.setVisibility(View.VISIBLE);
            imgSearch.setOnClickListener(l);
        }
    }

    //新增按钮监听
    protected void setAddBtnOnclickListener(View.OnClickListener l){
        if (l !=null){
            imgAdd.setVisibility(View.VISIBLE);
            imgAdd.setOnClickListener(l);
        }
    }
    //右边文字监听
    protected void setRightTextOnclickListener(View.OnClickListener l){
        if (l !=null){
            tvToolbarRight.setOnClickListener(l);
        }
    }
    @Override
    public void showLoading() {

    }

    public void showSnackBar(String message){
        Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
        {
            mImmersionBar.destroy();
        }
    }

    /**
     * 重写此方法改变状态栏颜色
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }
}
