/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.gamestore.jsp.beans;

/**
 *
 * @author Johnny
 */
public class OrderItem implements java.io.Serializable {
    private ProductItem item;
    private int quantity;

    public OrderItem(ProductItem item) {
        setItem(item);
        setQuantity(1);
    }
    
    public OrderItem Clone() {
        ProductItem prod = new ProductItem(item.getId(), item.getName(), item.getType(), item.getPrice(), item.getImage(), item.getMaker(), item.getDiscount(), null);
        OrderItem clone = new OrderItem(prod);
        clone.setQuantity(quantity);
        return clone;        
    }

    public ProductItem getItem() {
        return item;
    }

    protected void setItem(ProductItem item) {
        this.item = item;
    }

    public String getItemId() {
        return getItem().getId();
    }
    
    public String getItemName() {
        return getItem().getName();
    }

    public int getItemType() {
        return getItem().getType();
    }

    public double getUnitPrice() {
        return getItem().getDiscountedPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementItemQuantity() {
        setQuantity(getQuantity() + 1);
    }

    public void cancelOrder() {
        setQuantity(0);
    }

    public double getTotalCost() {
        return(getQuantity() * getUnitPrice());
    }
}

