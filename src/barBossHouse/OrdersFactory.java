package barBossHouse;

public abstract class OrdersFactory {

    abstract Order createInternetOrder();
    public abstract Order createInternetOrder(Order order);
    abstract Order createInternetOrder(Customer customer, MenuItem[] menuItems);

    abstract Order createTableOrder();
    public abstract Order createTableOrder(Order order);
    abstract Order createTableOrder(int dishCount, Customer customer);
    abstract Order createTableOrder(MenuItem[] dishesArray, Customer customer);

    abstract OrdersManager createInternetOrderManager();
    abstract OrdersManager createInternetOrderManager(Order[] orders);

    abstract OrdersManager createTableOrdersManager();
    abstract OrdersManager createTableOrdersManager(int tablesCount);


    static public OrdersFactory getOrdersFactory(OrdersFactoryTypesEnumeration type, String path){
        switch (type){
            case ORDINARY_ORDERS_FACTORY:
                return new OrdinaryOrdersFactory();
            case TEXT_FILE_BASED_ORDERS_FACTORY:
                return new TextFileBasedOrdersFactory(path);
            case BINARY_FILE_BASED_ORDERS_FACTORY:
                return new BinaryFileBasedOrdersFactory(path);
            case SERIALIZED_FILE_BASED_ORDERS_FACTORY:
                return new SerializedFileBasedOrdersFactory(path);
            case SOCKET_BASED_ORDERS_FACTORY:
                return null;
                //todo И все же непонятно, что тут происходит и должно происходить COMPLETE
                //todo  ОТВЕТ - этой ветви пока вообще быть не должно, это на будущее - лабу по сокетам COMPLETE
        }
        return new OrdinaryOrdersFactory();
    }

}
