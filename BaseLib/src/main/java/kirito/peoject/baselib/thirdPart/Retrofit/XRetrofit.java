package kirito.peoject.baselib.thirdPart.Retrofit;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import kirito.peoject.baselib.BaseLib;
import kirito.peoject.baselib.mvp.BaseM;
import okhttp3.ResponseBody;

import java.io.*;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/27 0027
 */
public class XRetrofit {
    /**
     * before use this method you need init{@link BaseLib} XRetrofitConfig in application
     * <p>
     * <p>
     * in Retrofit you need use code like this
     * <p>
     * <pre>{@code
     * Retrofit retrofit = new Retrofit.Builder()
     *     .baseUrl("http://api.example.com")
     *     .addConverterFactory(GsonConverterFactory.create())
     *     .build();
     *
     * MyApi api = retrofit.create(MyApi.class);
     * Response<User> user = api.getUser().execute();
     * }</pre>
     * <p>
     * but,in XRetrofit you only use this method to create server instance
     *
     * @param server
     */
    public static <T> T getServerCall(Class<T> server) {
        RetrofitHelper.Builder retrofitHelperBuilder = new RetrofitHelper.Builder();
        retrofitHelperBuilder = retrofitHelperBuilder.baseUrl(BaseLib.xRetrofitConfig.httpUrl);
        try {
            retrofitHelperBuilder = retrofitHelperBuilder.retrofitInterceptor(BaseLib.xRetrofitConfig.getInterceptorInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            retrofitHelperBuilder = retrofitHelperBuilder.commentParamsAdapter(BaseLib.xRetrofitConfig.getCommentParamsAdapterInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        retrofitHelperBuilder = retrofitHelperBuilder.converterFactory(BaseLib.xRetrofitConfig.factory);
        T tImpl = retrofitHelperBuilder.build().toRetrofit().create(server);
        return tImpl;
    }

    public static <T extends BaseM,C extends CodeInterceptor> Observable<T> toRequest(Observable<T> observable, NetCallBack netCallBack, List<Disposable> disposables) {
        C c=null;
        try {
          c= (C) BaseLib.xRetrofitConfig.getCodeInterceptorInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ResponseCallBack<T,C>(netCallBack, disposables,c) {

        });
        return observable;
    }


    public static <T> T getDownloadServiceCall(Class<T> server, final DownloadCallBack downloadCallBack) {
        RetrofitHelper.Builder retrofitHelperBuilder = new RetrofitHelper.Builder();
        retrofitHelperBuilder = retrofitHelperBuilder.baseUrl(BaseLib.xRetrofitConfig.httpUrl);
        T tImpl = retrofitHelperBuilder.buildDownload(downloadCallBack).toRetrofit().create(server);
        return tImpl;
    }

    public static Observable toRequest(Observable observable, final DownloadCallBack downloadCallBack, List<Disposable> disposables, final File file) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io()).map(new Function<ResponseBody, InputStream>() {
            @Override
            public InputStream apply(ResponseBody responseBody) {
                return responseBody.byteStream();
            }
        }).observeOn(Schedulers.computation()).doOnNext(new Consumer<InputStream>() {
            @Override
            public void accept(InputStream o) throws Exception {
                writeFile(o, file, downloadCallBack);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new DownloadResponseCallBack(downloadCallBack, disposables) {
        });
        return observable;
    }

    /**
     * 将输入流写入文件
     *
     * @param inputString
     * @param file
     */
    private static void writeFile(InputStream inputString, File file, DownloadCallBack downloadCallBack) {
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputString.close();
            fos.close();
        } catch (FileNotFoundException e) {
            downloadCallBack.onFailure();
        } catch (IOException e) {
            downloadCallBack.onFailure();
        }
    }

}
