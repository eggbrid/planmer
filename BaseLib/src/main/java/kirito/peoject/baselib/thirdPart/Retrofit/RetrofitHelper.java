package kirito.peoject.baselib.thirdPart.Retrofit;

import kirito.peoject.constantlibs.net.HttpUrls;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @Description: the retrofit helper
 * @Author:kirito
 * @CreatTime:2019/2/27 0027
 */
public final class RetrofitHelper {
    public OkHttpClient client;
    public Converter.Factory factory;
    public String baseUrl ;
    RetrofitHelper(OkHttpClient client,Converter.Factory factory,String baseUrl) {
        this.client = client;
        this.factory=factory;
        this.baseUrl=baseUrl;
    }



    /**
     * get a true Retrofit instance
     *
     * @return
     */
    public Retrofit toRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(HttpUrls.API_URL);
        if (client != null) {
            builder = builder.client(client);
        }
        if (factory!=null){
            builder=  builder.addConverterFactory(factory);
        }
        builder=  builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build();
    }

    public Retrofit toRetrofit(String url) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url);
        if (client != null) {
            builder = builder.client(client);
        }
        if (factory!=null){
            builder=  builder.addConverterFactory(factory);
        }
        builder=  builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build();
    }
    public static final  class Builder {
        public RetrofitInterceptor retrofitInterceptor;
        public Converter.Factory factory;
        public String baseUrl ="";

        public Builder() {
            retrofitInterceptor = new RetrofitInterceptor();
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;

        }

        public Builder converterFactory(Converter.Factory factory) {
            this.factory = factory;
            return this;

        }

        public Builder retrofitInterceptor(RetrofitInterceptor retrofitInterceptor) {
            this.retrofitInterceptor = retrofitInterceptor;
            return this;
        }

        public <T extends CommentParamsAdapter> Builder commentParamsAdapter(T commentParamsAdapter) {
            if (retrofitInterceptor==null){
                this.retrofitInterceptor = new RetrofitInterceptor();
            }
            retrofitInterceptor.setCommentParamsAdapter(commentParamsAdapter);
            return this;
        }

        public RetrofitHelper build() {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(retrofitInterceptor)
                    .build();
            return new RetrofitHelper(okHttpClient,factory,baseUrl);
        }
        public RetrofitHelper buildDownload(DownloadCallBack downloadCallBack) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new DownloadInterceptor(downloadCallBack))
                    .build();
            return new RetrofitHelper(okHttpClient,factory,baseUrl);
        }
    }

}
