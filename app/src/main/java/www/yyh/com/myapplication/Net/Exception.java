package www.yyh.com.myapplication.Net;

/**
 * Created by 56357 on 2018/9/7
 */
public interface Exception {
    class TokenExpired extends RuntimeException{
        public TokenExpired(){
            super("Token 过期了");
        }
    }

    class OffLine extends RuntimeException{
        public OffLine(){
            super("没有网络");
        }
    }

    class TimeOut extends RuntimeException{
        public TimeOut(){
            super("连接超时");
        }
    }

}
