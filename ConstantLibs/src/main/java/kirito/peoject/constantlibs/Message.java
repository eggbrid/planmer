package kirito.peoject.constantlibs;

/**
 * @auther kirito
 * @Date 2019-06-12
 * @NOTE 类说明
 */
public class Message  {

    public final static int REGISTER_FINISH=10001;
    public final static int USER_INFO_UPDATED=10002;
    public Message(int code){
        this.code=code;
    }
    public int code;

}
