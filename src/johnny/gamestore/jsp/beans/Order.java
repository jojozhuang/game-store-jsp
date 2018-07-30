/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.gamestore.jsp.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Johnny
 */
public class Order implements java.io.Serializable {
    private String id;
    private String username;
    private String address;
    private String creditcard;
    private String confirmation;
    private Date deliverydate;
    private ArrayList<OrderItem> items = new ArrayList();
    
    public Order() {
    }
    public Order(String id, String username, String address, String creditcard, String confirmation, Date deliverydate) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.creditcard = creditcard;
        this.confirmation = confirmation;
        this.deliverydate = deliverydate;
    }
    
    public Order Clone() {
        Order clone = new Order();
        clone.setId(id);
        clone.setUserName(username);
        clone.setAddress(address);
        clone.setCreditCard(creditcard);
        clone.setConfirmation(confirmation);
        clone.setDeliveryDate(deliverydate);
        ArrayList<OrderItem> cloneItems = new ArrayList<OrderItem>();
        for (OrderItem oit : items) {
            cloneItems.add(oit.Clone());
        }
        clone.setItems(cloneItems);
        return clone;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserName() {
        return username;
    }
    
    public void setUserName(String username) {
        this.username = username;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCreditCard() {
        return creditcard;
    }
    
    public void setCreditCard(String creditcard) {
        this.creditcard = creditcard;
    }
    
    public String getConfirmation() {
        return confirmation;
    }
    
    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }
    
    public Date getDeliveryDate() {
        return deliverydate;
    }
    
    public void setDeliveryDate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }
       
    public String getFormatDeliveryDate() {
        if (deliverydate == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return sdf.format(deliverydate);
        }
    }
    
    public ArrayList<OrderItem> getItems() {
        return items;
    }
    
    public void setItems(ArrayList<OrderItem> items) {
        this.items = new ArrayList<OrderItem>(items);
    }
    
    
    public synchronized void addItem(OrderItem item) {
        items.add(item);
    }
    
    public synchronized void setItemQuantity(String id, int quantity) {
        OrderItem orderItem;
        for(int i = 0; i < items.size(); i++) {
            orderItem = items.get(i);
            if (orderItem.getItemId().equals(id)) {
                if (quantity <= 0) {
                    items.remove(i);
                } else {
                    orderItem.setQuantity(quantity);
                }
                return;
            }
        }        
    }
}
