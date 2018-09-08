package www.yyh.com.myapplication.Net;

import io.reactivex.Maybe;
import retrofit2.http.GET;
import retrofit2.http.Url;
import www.yyh.com.myapplication.model.BaseResponse;

/**
 * 访问的接口
 * Created by 56357 on 2018/9/7
 */
public interface Service {
    @GET
    Maybe<BaseResponse> getSomeThing(@Url String url);
}
