package cn.edu.nbut.jerry.Utils;

import cn.edu.nbut.jerry.POJO.Order;
import cn.edu.nbut.jerry.config.NamesConfig;
import cn.edu.nbut.jerry.service.OrderService;
import cn.edu.nbut.jerry.service.impl.OrderServiceImpl;
import com.alipay.api.AlipayApiException;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CancelTimoutOrder implements Job {
    /**
     * 订单 30分钟不支付超时自动取消
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

//        System.out.println("TIMEOUT: CancelTimoutOrder");

//        Date nowDate = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date beforeDate = new Date(nowDate.getTime() - NamesConfig.ORDER_TOTAL_TIME_TEST);
//        String timeout = sdf.format(beforeDate); // 本次测试为5s

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeout = sdf.format(CommonUtils.subtractTime(now, NamesConfig.ORDER_TOTAL_TIME_TEST));

        OrderService orderService = new OrderServiceImpl();
//        System.out.println("TIMEOUT: "+timeout);
        List<Order> orders = orderService.getAllTimoutOrder(timeout);       // 获取所有超时的订单
//        System.out.println("TIMEOUT: orders = " + orders);
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
     * 轮询
     * @param seconds 每x秒 执行一次
     * @param repeatCount   轮询次数上限
     * @throws SchedulerException  ex
     */
    public static void roundQuery(int seconds, int repeatCount) throws SchedulerException {
        //创建一个scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //创建一个Trigger 轮询
        Trigger trigger = TriggerBuilder.newTrigger()
                                .withSchedule(SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(seconds) // 这里是 每隔 x 秒轮询
                                .withRepeatCount(repeatCount).repeatForever()
                ).build();

        //创建一个job
        JobDetail job = JobBuilder.newJob(CancelTimoutOrder.class).build();

        //注册trigger并启动scheduler
        scheduler.scheduleJob(job,trigger);
        scheduler.start();
    }

    /**
     * 停止轮询
     * @param scheduler
     * @throws SchedulerException
     */
    public static void roundQueryClose(Scheduler scheduler) throws SchedulerException {
        scheduler.shutdown();
    }

}
