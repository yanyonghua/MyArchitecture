package www.yyh.com.myapplication.presenter;

/**
 * Created by 56357 on 2018/9/6
 */
public class BasePresenter<T extends BaseContact.View>
        implements BaseContact.Presenter {

    private T mView;//开始的时候要调用结束要销毁


    /**
     * 给子类使用的获取View的操作
     * @return
     */
    public final T getView() {
        return mView;
    }


    public BasePresenter(T view) {
        setView(view);
    }


    /**
     * 给子类使用获取View的操作 setview顺便把Presenter 的实体set到View里面去了
     *
     * @param mView
     */
    public void setView(T mView) {//传递一个泛型view过来继承自BaseContract.View
        this.mView = mView;
        this.mView.setPresenter(this);
    }


    @Override
    public void start() {
        T view =mView;
        if (view!=null){
            view.showLoading();
        }
    }

    @Override
    public void destory() {
        T view =mView;
        mView=null;
        if (view!=null){
            view.setPresenter(null);
        }
    }
}
