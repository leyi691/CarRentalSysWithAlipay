package cn.edu.nbut.jerry;

import cn.edu.nbut.jerry.POJO.Order;
import cn.edu.nbut.jerry.Utils.PayUtils;
import cn.edu.nbut.jerry.config.NamesConfig;
import cn.edu.nbut.jerry.service.OrderService;
import cn.edu.nbut.jerry.service.impl.OrderServiceImpl;
import com.alipay.api.AlipayApiException;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static cn.edu.nbut.jerry.config.NamesConfig.ORDER_POLLING_COUNT_TEST;
import static cn.edu.nbut.jerry.config.NamesConfig.ORDER_TIME_LIMIT_TEST;

public class QuartzTest implements Job{

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        //创建一个scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //创建一个Trigger 轮询
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(ORDER_TIME_LIMIT_TEST) // 这里是 每隔 x 秒轮询
                        .withRepeatCount(ORDER_POLLING_COUNT_TEST)
                ).build();

        //创建一个job
        JobDetail job = JobBuilder.newJob(QuartzTest.class).build();

        //注册trigger并启动scheduler
        scheduler.scheduleJob(job,trigger);
        scheduler.start();
//        TimeUnit.SECONDS.sleep(5);
//        shutdown(scheduler);
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TIMEOUT: CancelTimoutOrder");

//        Date nowDate = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date beforeDate = new Date(nowDate.getTime() - 2*5);
//        String timeout = sdf.format(beforeDate); // 本次测试为5s
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeout = sdf.format(subtractTime(now, -50000));

        OrderService orderService = new OrderServiceImpl();
        System.out.println("TIMEOUT: "+timeout);
        List<Order> orders = orderService.getAllTimoutOrder(timeout);       // 获取所有超时的订单
        System.out.println("TIMEOUT: orders = " + orders);
        if (orders != null) {
            for(Order order : orders) {
                // 一条条修改超时订单状态
                System.out.println("TIMEOUT: orders != null");
                String aliStatue = null;
                try {
                    aliStatue = PayUtils.getAlipayTradeStatue(order.getOutTradeNo());
                    System.out.println("TIMEOUT: aliStatue = " + aliStatue);
                } catch (AlipayApiException e) {
                    System.out.println("支付宝订单查询失败。");
                    e.printStackTrace();
                }
                if (aliStatue != null)
                    if (aliStatue.equals(NamesConfig.WAIT_BUYER_PAY)) {
                        System.out.println("TIMEOUT: aliStatue != null");
                        order.setOrderStatus(NamesConfig.TRADE_CLOSED);
                        System.out.println("TIMEOUT: order = " + order);
                        int result = orderService.updateOrderStatusByOutTradeNo(order);
                        if (result > 0) {
                            System.out.println("订单已超时关闭");
                        }
                    }
            }
        }
    }

    /**
     *  加减对应时间后的日期
     * @param date  需要加减时间的日期
     * @param amount  加减的时间(毫秒)
     * @return  加减对应时间后的日期
     */
    private Date subtractTime(Date date, int amount) {
        try {
            return new Date(date.getTime() + amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testTime(){
        Date date=new Date();
//        Date date1=new Date(date.getTime()+10*60*1000);
        Date date1 = subtractTime(date, 6000);
        System.out.println(date);
        System.out.println(date1);
    }
}
