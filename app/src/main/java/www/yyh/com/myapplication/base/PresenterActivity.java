package www.yyh.com.myapplication.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import www.yyh.com.myapplication.R;
import www.yyh.com.myapplication.presenter.BaseContact;

/**
 * Created by 56357 on 2018/9/6
 */
public abstract class PresenterActivity<Presenter extends BaseContact.Presenter> extends Activity implements BaseContact.View<Presenter>{
    protected Presenter mPresenter;

    private ProgressDialog mLoadingDialog;
    /**
     * 初始化Presenter
     * @return Presenter
     */
    protected abstract Presenter initPresenter();
    @Override
    protected void initBefore() {
        super.initBefore();
        initPresenter();
    }

    @Override
    public void showError(int str) {
        //不管你怎么样，我先隐藏我
        hideDialogLoading();
        //显示toast提示
        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();
    }


    /**
     * 隐藏dialog
     */
    protected void hideDialogLoading(){
        ProgressDialog dialog= mLoadingDialog;
        if (dialog!=null){
            mLoadingDialog=null;
            dialog.dismiss();
        }
    }

    public void hideLoading() {
        //不管你怎么样，我先隐藏我
        hideDialogLoading();
//        if (mPlaceHolderView!=null){
//            mPlaceHolderView.triggerOk();
//        }
    }
    @Override
    public void showLoading() {
        ProgressDialog dialog= mLoadingDialog;
        if (dialog==null){

            dialog=new ProgressDialog(this, R.style.APPTheme_Dialog_Alert_Light);
            // 不可触摸取消
            dialog.setCanceledOnTouchOutside(false);
            //强制取消关闭界面
            dialog.setCancelable(true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();
                }
            });
            mLoadingDialog =  dialog;
        }
        dialog.setMessage("loading...");
        dialog.show();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        //View中赋值Presenter
        this.mPresenter = presenter;
    }
}
