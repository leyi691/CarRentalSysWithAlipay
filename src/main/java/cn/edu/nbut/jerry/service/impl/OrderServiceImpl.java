package cn.edu.nbut.jerry.service.impl;

import cn.edu.nbut.jerry.POJO.Order;
import cn.edu.nbut.jerry.Utils.CommonUtils;
import cn.edu.nbut.jerry.Utils.MyBatisUtils;
import cn.edu.nbut.jerry.config.NamesConfig;
import cn.edu.nbut.jerry.mapper.OrderSqlMapper;
import cn.edu.nbut.jerry.service.OrderService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private SqlSession sqlSession = null;
    private OrderSqlMapper mapper = null;

    @Override
    public List<Order> getAllOrder() {
        List<Order> orders = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            orders = mapper.selectAll();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrderByStoreId(int orderId) {
        List<Order> orders = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            orders = mapper.selectOrderByStoreId(orderId);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrderByUserId(int userId) {
        List<Order> orders = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            orders = mapper.selectOrderByUserId(userId);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return orders;
    }

    @Override
    public List<Order> getAllTimoutOrder(String orderDate) {
        List<Order> orders = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            orders = mapper.selectAllTimoutOrder(orderDate);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrderForCarStore(int storeId) {
        List<Order> orders = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            orders = mapper.selectAllOrderForCarStore(storeId);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrderForCarStoreSpecificPage(int storeId, int nowPage) {
        List<Order> orders = null;
        List<Order> ordersReturn = null;
        nowPage = nowPage - 1;  // 符合用户使用逻辑
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            orders = mapper.selectAllOrderForCarStore(storeId);
            ordersReturn = (List<Order>) CommonUtils.getSpecificPage(nowPage, orders, NamesConfig.ONE_PAGE_SIZE_SHORT);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return ordersReturn;
    }

    @Override
    public List<Order> searchOrderLikeStoreNameForUser(String storeName, int userId) {
        List<Order> orders = null;
        List<Order> ordersReturn = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("searchTxt", "%" + storeName + "%");
            map.put("userId", userId);
            orders = mapper.searchOrderLikeStoreNameForUser(map);
            if (orders.size() >= 10) {
                ordersReturn = orders.subList(0, 10);
            } else {
                ordersReturn = orders;
            }
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return ordersReturn;
    }

    @Override
    public List<Order> searchOrderLikeBrandForStore(String brandName, int storeId) {
        List<Order> orders = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("searchTxt", "%" + brandName + "%");
            map.put("storeId", storeId);
            orders = mapper.searchOrderLikeBrandForCarStore(map); // 实现模糊查询
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return orders;
    }

    @Override
    public List<Order> searchOrderLikeStatueForStore(String statue, int storeId) {
        List<Order> orders = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("searchTxt", "%" + statue + "%");
            map.put("storeId", storeId);
            orders = mapper.searchOrderLikeStatueForCarStore(map); // 实现模糊查询
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return orders;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            order = mapper.selectOrderById(orderId);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return order;
    }

    @Override
    public Order getOrderByOutTradeNoForUser(String outTradeNo) {
        Order order = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            order = mapper.selectOrderByOutTradeNoForUser(outTradeNo);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return order;
    }

    @Override
    public int addOrder(Order order) {
        int result = 0;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            result = mapper.insertOrderById(order);
            sqlSession.commit();
            System.out.println("Service: 订单新增成功！");
        } catch (SqlSessionException exception) {
            sqlSession.rollback();
            exception.printStackTrace();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    @Override
    public int updateOrderStatusByOutTradeNo(Order order) {
        int result = 0;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            result = mapper.updateOrderStatusByOutTradeNo(order);
            sqlSession.commit();
            System.out.println("订单状态更新成功");
        } catch (SqlSessionException exception) {
            sqlSession.rollback();
            exception.printStackTrace();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    @Override
    public int updateOrderRemarkByOutTradeNo(Order order) {
        int result = 0;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            result = mapper.updateOrderRemarkByOutTradeNo(order);
            sqlSession.commit();
        } catch (SqlSessionException exception) {
            sqlSession.rollback();
            exception.printStackTrace();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    @Override
    public int deleteOrder(int orderId) {
        int result = 0;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            mapper = sqlSession.getMapper(OrderSqlMapper.class);
            result = mapper.deleteOrderById(orderId);
            sqlSession.commit();
        } catch (SqlSessionException exception) {
            sqlSession.rollback();
            exception.printStackTrace();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }
}
