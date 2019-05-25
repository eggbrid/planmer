package kirito.peoject.baselib.thirdPart.Retrofit;

import kirito.peoject.baselib.mvp.BaseM;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/28 0028
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/28 0028
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public abstract class ResponseInterceptor {


    public abstract ResponBean doBeforeParse(String json);

    public abstract <T extends BaseM> T doAfterParse(T t, ResponBean responBean);


    public class ResponBean {
        public ResponBean(String beanJson, Map map) {
            this.beanJson = beanJson;
            this.otherMap = map;
        }

        private String beanJson;
        private Map<String, Object> otherMap;

        public String getBeanJson() {
            return beanJson;
        }

        public void setBeanJson(String beanJson) {
            this.beanJson = beanJson;
        }

        public Map<String, Object> getOtherMap() {
            return otherMap;
        }

        public void setOtherMap(Map<String, Object> otherMap) {
            this.otherMap = otherMap;
        }
    }
}
