# XLibs<br>
the library for android developers<br>
### it makes code so easy！！<br>

this Library include  the following function ：<br>
## BaseLib（baseActivity，activityManager）<br>
``` java
@Route(path = TestLibs.TestLibs_net_NetTestActivity)
public class NetTestActivity extends BaseActivity<NetTestView> {

    @Override
    public void afterInitView(NetTestView v) {
        v.post.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == view.post) {
            getP(NetTestP.class).getTestJson(new NetCallBack<TestM>() {
                @Override
                public void onGetData(TestM testM) {
                    view.response.setText(testM.getJson());
                }
                @Override
                public void onFinish() {
                    Log.e(getTag(), "onFinish");
                }

                @Override
                public void onFailure() {
                    Log.e(getTag(), "onFailure");
                }
            });
        }
    }

}
```
## MVP frame<br>
model<br>
``` java
public class TestM extends BaseM {
    private String json;
  
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
```
view<br>
``` java
public class NetTestView extends BaseV {
    public Button post;
    public TextView response;

    public NetTestView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.net_test_activity;
    }

    @Override
    public void initView() {
        post=findViewById(R.id.post);
        response=findViewById(R.id.response);
    }

}
```
presenter<br>
``` java
public class NetTestP extends BaseP<NetTestS> {

    public Observable<TestM> getTestJson(NetCallBack<TestM> callBack) {
        return request(getService().getTestJson(), callBack);
    }
}
```
## Modularization(base on ARouter)<br>
``` java
LibJumpHelper.startActivity(TestLibs.TestLibs_MainActivity);

```
## Dialog<br>
## http frame（base on Retrofit2+RxJava2）<br>
``` java

XRetrofit.getServerCall(getSClass())
XRetrofit.toRequest(observable, netCallBack, disposables)
```
## XWebView(base on x5)<br>

## code(base on blankJ AndroidUtilCode)<br>