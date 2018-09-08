package www.yyh.com.aowang.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import www.yyh.com.aowang.view.BaseView;

/**
 * Created by 56357 on 2018/9/7
 */
public class MvpPresenter<T extends BaseView> {
    private T mView;

    /**
     * Presenter被创建后调用
     *
     * @param saveState 被意外销毁后重建后的Bundle
     */
    public void onCreatePresenter(@NonNull Bundle saveState){

    }

    /**
     * 绑定View
     */
    public void onAttachMvpView(T view){
        mView=view;
    }
    /**
     * 解除绑定View
     */
    public void onDetachMvpView() {
        mView = null;
        Log.e("perfect-mvp", "P onDetachMvpView");
    }

    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
        Log.e("perfect-mvp", "P onDestroy");
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
        Log.e("perfect-mvp", "P onSaveInstanceState");
    }

    /**
     * 获取V层接口View
     *
     * @return 返回当前MvpView
     */
    public T getMvpView() {
        return mView;
    }

}
