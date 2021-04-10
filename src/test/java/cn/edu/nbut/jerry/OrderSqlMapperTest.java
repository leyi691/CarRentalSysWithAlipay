package cn.edu.nbut.jerry;

import cn.edu.nbut.jerry.POJO.Car;
import cn.edu.nbut.jerry.POJO.Order;
import cn.edu.nbut.jerry.Utils.MyBatisUtils;
import cn.edu.nbut.jerry.mapper.OrderSqlMapper;
import cn.edu.nbut.jerry.service.CarService;
import cn.edu.nbut.jerry.service.OrderService;
import cn.edu.nbut.jerry.service.impl.CarServiceImpl;
import cn.edu.nbut.jerry.service.impl.OrderServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class OrderSqlMapperTest {
//    @Test
//    public void testInsertOrderById(){
//        SqlSession sqlSession = MyBatisUtils.createSqlSession();
//        OrderSqlMapper mapper = sqlSession.getMapper(OrderSqlMapper.class);
//        Order order = new Order();
//        Date date = new Date();
//        order.setTotalPrice(912312.33);
//        order.setOrderDate(date);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2022, Calendar.JANUARY, 3);
//        date = calendar.getTime();
//        order.setReturnDate(date);
//        order.setSendCarAddress("浙江省宁波市江北区");
//        order.setGetCarAddress("阿斯顿马丁门店");
//        order.setOrderInfo("Info here.");
//        try {
//            int result = mapper.insertOrderById(order);
//            sqlSession.commit();
//            if (result > 0){
//                System.out.println("添加成功！");
//            }
//        } catch (Exception e){
//            System.out.println(e);
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
////        System.out.println(date);
//    }
    @Test
    public void testSearch(){
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.searchOrderLikeBrandForStore("", 1);
        System.out.println(orders.size());
    }
    @Test
    public void testSearchCAR_STORE(){
        CarService carService = new CarServiceImpl();
        List<Car> cars = carService.searchCarLikeBrandByStoreId("", 1);
        System.out.println(cars.size());
    }
    @Test
    public void testSearchUser(){
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.searchOrderLikeStoreNameForUser("u", 7);
        System.out.println(orders);
    }
    @Test
    public void getAllOrderForCarStoreSpecificPage() {
        int storeId = 4;
        int nowPage = 1;
        List<Order> orders = null;
        List<Order> ordersReturn = null;
        SqlSession sqlSession = null;
//        nowPage = nowPage - 1;  // 符合用户使用逻辑
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            OrderSqlMapper mapper = sqlSession.getMapper(OrderSqlMapper.class);
            orders = mapper.selectAllOrderForCarStore(storeId);
            System.out.println(orders.size());

//            ordersReturn = (List<Order>) CommonUtils.getSpecificPage(nowPage, orders, 10);

            int pageNumber = nowPage;

            float circleCountFloat  = (float) (orders.size() / (10*1.0));
            boolean flag            = false;
            int pagesCount          = orders.size() / 10;
            int pc = orders.size()/10;


            // 如果 circleCount == isInteger，说明记录数量刚好是10的倍数。
            if (circleCountFloat > pagesCount && pagesCount != 0) {
                // 如果记录数量不是 10 的倍数，说明总页面数量需要 + 1;
                pagesCount++;
                flag = true;
            }
            if (pagesCount == 0) {
                // 如果记录数量只有一页
                ordersReturn = orders;
                System.out.println("circleCountInt == 0 时候 ");
            } else {
                for (int i = 0; i < pc; i++) {
                    // 循环将相应的页面数量分给orders
                    if (i == pageNumber) {
                        // 关键代码：输出页面需要的相应的记录
                        ordersReturn = orders.subList(i * 10, i * 10 + 10);
                        System.out.println("i == pageNumber 时候 " + i);
                        break;
                    }
                }
                if (flag && ((pagesCount - 1) == pageNumber)) {
                    // 如果记录数不是 10 的倍数，就需要将最后几个遗漏的记录放在里面。
                    System.out.println("记录数不是 10 的倍数 时候 ");
                    ordersReturn = orders.subList((pagesCount - 1) * 10, orders.size());
                }
            }


        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        System.out.println(ordersReturn.size());
    }

    public static List<?> getSpecificPageTest(int pageNumber, List<?> list) {
        List<?> returnList = null;
        float circleCountFloat = (float) (list.size() / (10*1.0));
        int circleCountInt = list.size() / 10;
        boolean flag = false;
        // 如果 circleCount == isInteger，说明记录数量刚好是10的倍数。
        if (circleCountFloat > circleCountInt) {
            // 如果记录数量不是 10 的倍数，说明总页面数量需要 + 1;
            circleCountInt++;
            flag = true;
        }
        if (circleCountInt == 0) {
            // 如果记录数量只有一页
            returnList = list;
            System.out.println("circleCountInt == 0 时候 ");
        } else {
            for (int i = 0; i < (list.size() / 10); i++) {
                // 循环将相应的页面数量分给orders
                if (i == pageNumber) {
                    // 关键代码：输出页面需要的相应的记录
                    returnList = list.subList(i * 10, i * 10 + 10);
                    System.out.println("i == pageNumber 时候 " + i);
                    break;
                }
            }
            if (flag && circleCountInt == pageNumber) {
                // 如果记录数不是 10 的倍数，就需要将最后几个遗漏的记录放在里面。
                System.out.println("记录数不是 10 的倍数 时候 ");
                returnList = list.subList((circleCountInt - 1) * 10, list.size());
            }
        }
        return returnList;
    }
}
