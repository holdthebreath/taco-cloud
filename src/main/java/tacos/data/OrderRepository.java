package tacos.data;

import tacos.Order;

/**
 * @ClassName OrderRepository
 * @Description 订单数据库接口类
 * @Author hwd
 * @Date 2020/7/11 11:15 PM
 * @Version 1.0
 */
public interface OrderRepository {
    Order save(Order order);
}
