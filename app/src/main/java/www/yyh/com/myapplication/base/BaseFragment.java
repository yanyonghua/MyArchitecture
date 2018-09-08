package www.yyh.com.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * Created by 56357 on 2018/9/6
 */
public abstract class BaseFragment extends Fragment{
    protected View mRoot;
    private Unbinder mRootUnbinder;
    private boolean mIsFirstInitData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始参数
        initArgs(getArguments());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot==null){
          int  layoutId=getContentLayout();
        View root=  inflater.inflate(layoutId,container,false);
        initWidget(root);
            mRoot=root;
        }else {
            if (mRoot.getParent() != null) {
                //把当前root从其父控件中移除
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }
    /**
     * 初始化相关参数
     *
     * @param bundle
     * @return 如果参数正确返回True，错误返回False
     */
    protected void initArgs(Bundle bundle) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mIsFirstInitData){
            //第一次才会出发
            mIsFirstInitData =false;
            //触发
            onFirstInit();
        }
        //当View创建完成后初始化数据
        initData();
    }

    private void initData() {

    }

    private void onFirstInit() {

    }

    protected  void initWidget(View root){
        mRootUnbinder = ButterKnife.bind(this, root);
    };
    /**
     * 得到当前界面的资源文件id
     *
     * @return 资源文件ID
     */
    protected abstract int getContentLayout() ;

    /**
     * 返回按键触发调用
     *
     * @return 返回True 代表我已经处理返回逻辑，Activity不用Finish
     * 返回False代表我没有处理逻辑,Activity自己处理自己的逻辑
     */
    public boolean onBackPressed() {
        return false;
    }
}
