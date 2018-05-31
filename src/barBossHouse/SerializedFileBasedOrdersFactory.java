package barBossHouse;

import io.*;

//todo смотри TextFileBasedOrdersFactory COMPLETE
public class SerializedFileBasedOrdersFactory extends OrdersFactory {

    private OrderManagerSerializedFileSource source;

    public SerializedFileBasedOrdersFactory(String path) {
        source = new OrderManagerSerializedFileSource(path);
    }

    @Override
    public Order createTableOrder() {
        return new ControlledTableOrder();
    }

    @Override
    public Order createTableOrder(int itemsLength, Customer customer) {
        return new ControlledTableOrder(itemsLength, customer);
    }

    @Override
    public Order createTableOrder(Order order) {
        return new ControlledTableOrder(order);
    }

    @Override
    public Order createTableOrder(MenuItem[] items, Customer customer) {
        return new ControlledTableOrder(items, customer);
    }

    @Override
    public Order createInternetOrder() {
        return new ControlledInternetOrder();
    }

    @Override
    public Order createInternetOrder(Customer customer,MenuItem[] items) {
        return new ControlledInternetOrder(customer,items);
    }

    @Override
    public Order createInternetOrder(Order order) {
        return new ControlledInternetOrder(order);
    }

    @Override
    public OrdersManager createTableOrdersManager() {
        return new ControlledTableOrderManager(source, this);
    }

    @Override
    public OrdersManager createTableOrdersManager(int numberOfTables) {
        return new ControlledTableOrderManager(numberOfTables, source, this);
    }

    @Override
    public OrdersManager createInternetOrderManager() {
        return new ControlledInternetOrderManager(source, this);
    }

    @Override
    public OrdersManager createInternetOrderManager(Order[] orders) throws AlreadyAddedException {
        return new ControlledInternetOrderManager(orders, source, this);
    }
}
