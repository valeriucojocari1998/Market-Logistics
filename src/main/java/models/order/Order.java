package models.order;

import enums.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private List<String> productIds;
    private String customerId;
    private Date orderDate;
    private String orderAddress;
    private Float orderPrice;
    private OrderStatus status;

    public Order(List<String> productIds, String customerId, Date orderDate, String orderAddress, Float orderPrice, OrderStatus status) {
        this.id = UUID.randomUUID().toString();
        this.productIds = productIds;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderAddress = orderAddress;
        this.orderPrice = orderPrice;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Float getOrderPrice() {
        return orderPrice;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
