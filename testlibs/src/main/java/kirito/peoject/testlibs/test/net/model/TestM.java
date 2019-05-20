package kirito.peoject.testlibs.test.net.model;

import kirito.peoject.baselib.mvp.BaseM;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/28 0028
 */
public class TestM extends BaseM {
    private String json;
    @Override
    public boolean isReadResponseBySelf() {
        return true;
    }

    @Override
    public TestM toBean(String value) {
        TestM testM=new TestM();
        testM.setJson(value);
        return testM;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
