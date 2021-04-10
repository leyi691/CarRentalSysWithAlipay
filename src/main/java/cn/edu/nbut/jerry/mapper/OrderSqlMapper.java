package cn.edu.nbut.jerry.mapper;

import cn.edu.nbut.jerry.POJO.Order;

import java.util.List;
import java.util.Map;

public interface OrderSqlMapper {
    public List<Order>          selectAll                           ();
    public List<Order>          selectOrderByStoreId                (Integer id);
    public List<Order>          selectOrderByUserId                 (Integer id);
    public List<Order>          selectAllTimoutOrder                (String orderDate);
    public List<Order>          selectAllOrderForCarStore           (Integer id);
    public List<Order>          searchOrderLikeStoreNameForUser     (Map<String, Object> map);
    public List<Order>          searchOrderLikeBrandForCarStore     (Map<String, Object> map);
    public List<Order>          searchOrderLikeStatueForCarStore    (Map<String, Object> map);
    public Order                selectOrderById                     (Integer id);
    public Order                selectOrderByOutTradeNoForUser      (String  id);
    public int                  insertOrderById                     (Order order);
    public int                  updateOrderStatusByOutTradeNo       (Order order);
    public int                  updateOrderRemarkByOutTradeNo       (Order order);
    public int                  deleteOrderById                     (Integer id);
}