/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.gamestore.jsp.dao;

import johnny.gamestore.jsp.beans.Order;
import johnny.gamestore.jsp.beans.OrderItem;
import johnny.gamestore.jsp.common.Constants;
import johnny.gamestore.jsp.common.SerializeHelper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johnny
 */
public class OrderDao {
    private static OrderDao dao;
    private static List<Order> orders = new ArrayList<Order>();
    private OrderDao() {}
    
    public static synchronized OrderDao createInstance() {
        if (dao == null) {
            dao = new OrderDao();
            init();
        }
        return dao;
    }
    
    private static void init() {
        if (SerializeHelper.exsitDataFile(Constants.DATA_FILE_ORDER)) {
            orders = (List<Order>)SerializeHelper.readFromFile(Constants.DATA_FILE_ORDER);
            if (orders==null) {
                orders = new ArrayList<Order>();
            }
        } 
    }    
  
    public List<Order> getOrders() {
      return orders;
    }
    
    public List getOrders(String username) {
        if (username == null || username.isEmpty()) {
            return orders;
        }
        ArrayList<Order> res = new ArrayList<Order>();
        for (Order order: orders) {
            if (order.getUserName().equals(username)) {
                res.add(order);
            }
        }
        return res;
    }
    
    public synchronized Order getOrder(String id) {
        if (orders==null || orders.isEmpty()) {
            return null;
        } 
        for (Order order: orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }
            
    public boolean isExisted(String id) {
        return getOrder(id) == null ? false : true;
    }
    
    public synchronized void addOrder(Order order) {
        orders.add(0, order);
        SerializeHelper.writeToFile(Constants.DATA_FILE_ORDER, orders);
    }

    public synchronized void updateOrder() {
        SerializeHelper.writeToFile(Constants.DATA_FILE_ORDER, orders);
    }
    
    public synchronized void deleteOrder(String id) {
        if (orders==null || orders.isEmpty()) {
            return;
        } 
        
        Order order = getOrder(id);
        if (order==null) {
            return;
        } else {
            orders.remove(order);
        }
        SerializeHelper.writeToFile(Constants.DATA_FILE_ORDER, orders);
    }
    
    public synchronized void setItemQuantity(String orderid, String itemid, int type, int quantity) {
        Order order;
        for(int i = 0; i < orders.size(); i++) {
            order = orders.get(i);
            if (order.getId().equals(orderid)) {
                List<OrderItem> items= order.getItems();
                for (int j = 0; j < items.size(); j++) {
                    OrderItem orderItem = items.get(j);
                    if (orderItem.getItemId().equals(itemid)) {
                        if (quantity <= 0) {
                            items.remove(j);
                        } else {
                            orderItem.setQuantity(quantity);
                        }
                        return;
                    }
                }
                break;
            }
        }
        SerializeHelper.writeToFile(Constants.DATA_FILE_ORDER, orders);
    }
}
