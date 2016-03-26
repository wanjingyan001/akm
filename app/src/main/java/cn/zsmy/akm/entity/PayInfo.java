package cn.zsmy.akm.entity;

/**
 * Created by Administrator on 2016/1/28.
 */
public class PayInfo {


    /**
     * code : SUCC
     * message : 获取订单付款信息成功
     * data : {"payInfo":"partner=\"2088121368241138\"&seller_id=\"zsmy@zsmy.cn\"&out_trade_no=\"012816152673085\"&subject=\"测试的商品\"&body=\"该测试商品的详细描述\"&total_fee=\"0.01\"&notify_url=\"http://notify.msp.hk/notify.htm\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&return_url=\"m.alipay.com\"&sign=\"IGU74aUwutz7OXcC29abiT6nz8sluIMVZSoX%2BtwSKbX%2FLS1MVHq2Qnjf2KZaMq2OUGveThRnUwhMverUGVieMIHmWSYY%2Bo9uvaICcd7HZ1PwAkK9XWDE9%2FZDR5ECgAcM3pr5WfiHPjFxE3DsOBNl0A4wthsJTF3IZSGRWjruH6Q%3D\"&sign_type=\"RSA\""}
     */

    private String code;
    private String message;
    /**
     * payInfo : partner="2088121368241138"&seller_id="zsmy@zsmy.cn"&out_trade_no="012816152673085"&subject="测试的商品"&body="该测试商品的详细描述"&total_fee="0.01"&notify_url="http://notify.msp.hk/notify.htm"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&return_url="m.alipay.com"&sign="IGU74aUwutz7OXcC29abiT6nz8sluIMVZSoX%2BtwSKbX%2FLS1MVHq2Qnjf2KZaMq2OUGveThRnUwhMverUGVieMIHmWSYY%2Bo9uvaICcd7HZ1PwAkK9XWDE9%2FZDR5ECgAcM3pr5WfiHPjFxE3DsOBNl0A4wthsJTF3IZSGRWjruH6Q%3D"&sign_type="RSA"
     */

    private DataEntity data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String payInfo;

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }

        public String getPayInfo() {
            return payInfo;
        }
    }
}
