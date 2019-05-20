package kirito.peoject.baselib.thirdPart.Retrofit;

import kirito.peoject.constantlibs.net.HttpUrls;
import retrofit2.Converter;

/**
 * @Description: the config for {@link XRetrofit}
 * @Author:kirito
 * @CreatTime:2019/2/27 0027
 */
public class XRetrofitConfig<T extends RetrofitInterceptor, D extends CommentParamsAdapter, I extends ResponseInterceptor> {
    /**
     * construction Method
     */
    public XRetrofitConfig() {
    }

    /**
     * construction Method
     *
     * @param httpUrl your http host
     */
    public XRetrofitConfig(String httpUrl) {
        this(httpUrl, null);
    }

    /**
     * construction Method
     *
     * @param httpUrl             your http host
     * @param retrofitInterceptor the request interceptor before true request
     *                            you need extends {@link RetrofitInterceptor}
     */
    public XRetrofitConfig(String httpUrl, Class<T> retrofitInterceptor) {
        this(httpUrl, retrofitInterceptor, null);
    }

    /**
     * construction Method
     *
     * @param httpUrl              your http host
     * @param retrofitInterceptor  the class for request interceptor before true request
     *                             you need extends {@link RetrofitInterceptor}
     * @param commentParamsAdapter add comment params adapter
     *                             you need implement {@link CommentParamsAdapter}
     */
    public XRetrofitConfig(String httpUrl, Class<T> retrofitInterceptor, Class<D> commentParamsAdapter) {
        this(httpUrl, retrofitInterceptor, commentParamsAdapter, null);

    }

    /**
     * construction Method
     *
     * @param httpUrl              your http host
     * @param retrofitInterceptor  the class for request interceptor before true request
     *                             you need extends {@link RetrofitInterceptor}
     * @param commentParamsAdapter add comment params adapter
     *                             you need implement {@link CommentParamsAdapter}
     * @param responseInterceptor  the interceptor of parse http response
     *                             you need implement {@link ResponseInterceptor}
     */
    public XRetrofitConfig(String httpUrl, Class<T> retrofitInterceptor, Class<D> commentParamsAdapter, Class<I> responseInterceptor) {
        if (httpUrl != null) {
            this.httpUrl = httpUrl;
        }
        this.retrofitInterceptor = retrofitInterceptor;
        this.commentParamsAdapter = commentParamsAdapter;
        this.responseInterceptor = responseInterceptor;

    }

    public String httpUrl = HttpUrls.API_URL;
    //TODO 此处需要配置到外部
    public Converter.Factory factory = GsonConverterFactory.create();
    private Class<T> retrofitInterceptor;//retrofitInterceptor class name
    private Class<D> commentParamsAdapter;//commentParamsAdapter class name
    private T tCache;// instance for retrofitInterceptor
    private D dCache;// instance for commentParamsAdapter
    private I iCache;// instance for commentParamsAdapter

    private Class<I> responseInterceptor;

    /**
     * retrofitInterceptor class to instance
     */
    T getInterceptorInstance() throws InstantiationException, IllegalAccessException {
        if (tCache == null&&retrofitInterceptor!=null) {
            tCache = retrofitInterceptor.newInstance();
        }
        return tCache;
    }

    /**
     * commentParamsAdapter class to instance
     */
    D getCommentParamsAdapterInstance() throws InstantiationException, IllegalAccessException {
        if (dCache == null&&commentParamsAdapter!=null) {
            dCache = commentParamsAdapter.newInstance();
        }
        return dCache;
    }

    I getResponseInterceptorInstance() throws InstantiationException, IllegalAccessException {
        if (iCache == null&&responseInterceptor!=null) {
            iCache = responseInterceptor.newInstance();
        }
        return iCache;
    }
}
