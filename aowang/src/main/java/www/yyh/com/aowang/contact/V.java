package www.yyh.com.aowang.contact;

import www.yyh.com.aowang.view.BaseView;

/**
 * Created by 56357 on 2018/9/7
 */
public interface V {
    interface LoginView extends BaseView{
      void   getDataFromService(int requestCode);
    }
}
