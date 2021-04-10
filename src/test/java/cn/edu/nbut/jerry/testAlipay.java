package cn.edu.nbut.jerry;

import cn.edu.nbut.jerry.Utils.PayUtils;
import com.alipay.api.AlipayApiException;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class testAlipay {
    public static void main(String[] args) {
        String tradeCode = "22620210311192401";
        String tradeCode2 = "75320210316220352";
        try {
            String statue = PayUtils.getAlipayTradeStatue("75320210316220352");
            System.out.println(statue);
        } catch (AlipayApiException e) {
            e.getErrMsg();
            e.getErrCode();
            e.printStackTrace();
        }
    }
    @Test
    public void aliPay(HttpServletResponse response) throws IOException {
        PayUtils.alipay(response, "22620210311192401", "123", "123", "123","","");
    }
    @Test
    public void test(){
        String json =
                "{\"alipay_trade_refund_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"buyer_logon_id\":\"enu***@sandbox.com\",\"buyer_user_id\":\"2088622955527645\",\"fund_change\":\"N\",\"gmt_refund_pay\":\"2021-03-09 22:29:26\",\"out_trade_no\":\"73220210309222636\",\"refund_fee\":\"5997.00\",\"send_back_fee\":\"0.00\",\"trade_no\":\"2021030922001427640501399580\"},\"sign\":\"mxqBtPG70sTpmWf0gTVoHkDpjQlLVi3L3e10BOnnza7Pz9ISBps8AxC2rRERhsexC7mgTwf6afZofb2joOUO1Ye4E6hxNj0tcOjnWE4BX7HCrUwezh8yOvJXhihqSD753mEEC+nHQgCONbizOojK42DsxJvXAfyv68gSqhQaqG15BROckokGbVQSVfPhoB3nzj8hVqnaUPtDJyQPb7cSXu7TvrCxyyFRvahBm6PXiWyuB0S8B2ruzzc8DRmOUYZ5X4dw4MywFoBRWhSAyvoLu3O01eNGyV2H+X9e+8QTQCDp84hjOv05ZBY7jfr5SkHX06+C1VUe7ngHJ1mABRH3uw==\"}";
    }

    @Test
    public void testT(){
        int i = 9;
        int b = 10;
        int c = i / b;
        System.out.println(c);
    }
}
