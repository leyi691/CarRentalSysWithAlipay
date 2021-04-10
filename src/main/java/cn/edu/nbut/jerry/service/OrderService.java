package cn.edu.nbut.jerry.service;

import cn.edu.nbut.jerry.POJO.Order;

import java.util.List;

public interface OrderService {
    public List<Order>      getAllOrder                             ();                                 // 查询所有订单
    public List<Order>      getAllOrderByStoreId                    (int orderId);                      // 按照商铺Id查询订单
    public List<Order>      getAllOrderByUserId                     (int userId);                       // 按照用户Id查询订单
    public List<Order>      getAllTimoutOrder                       (String orderDate);                 // 查询订单状态为未支付且超时的订单
    public List<Order>      getAllOrderForCarStore                  (int storeId);                      // 查询所有待出车订单
    public List<Order>      getAllOrderForCarStoreSpecificPage      (int storeId, int nowPage);         // 查询所有待出车订单
    public List<Order>      searchOrderLikeStoreNameForUser         (String storeName, int userId);     // 按照商店名模糊查询
    public List<Order>      searchOrderLikeBrandForStore            (String brandName, int storeId);    // 按照汽车品牌模糊查询
    public List<Order>      searchOrderLikeStatueForStore           (String brandName, int storeId);    // 按照汽车品牌模糊查询
    public Order            getOrderById                            (int orderId);                      // 按照orderId查询订单
    public Order            getOrderByOutTradeNoForUser             (String outTradeNo);                // 按照outTradeNo查询订单
    public int              addOrder                                (Order order);                      // 新增订单
    public int              updateOrderStatusByOutTradeNo           (Order order);                      // 修改订单状态
    public int              updateOrderRemarkByOutTradeNo           (Order order);                      // 修改订单备注
    public int              deleteOrder                             (int orderId);                      // 删除订单
}