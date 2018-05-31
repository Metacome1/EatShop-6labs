package barBossHouse;

import io.OrderManagerFileSource;

//todo OrdinaryOrdersFactory создает ОБЫЧНЫЕ Ордера и ОрдерМэнеджеры, БЕЗ СУРСОВ. Метод setPath getPath выбрасывают UnsupportedOperationException COMPLETE
public class OrdinaryOrdersFactory extends OrdersFactory {

    @Override
    Order createInternetOrder(){
        return new InternetOrder();
    }

    @Override
    public Order createInternetOrder(Order order) {
        return new InternetOrder(order.getCustomer(),(MenuItem[]) order.toArray());
    }

    @Override
    public Order createTableOrder(Order order) {
        return new TableOrder(order.size(), order.getCustomer());
    }

    @Override
    Order createInternetOrder(Customer customer, MenuItem[] menuItems){
        return new InternetOrder(customer, menuItems);
    }

    @Override
    Order createTableOrder(){
        return new TableOrder();
    }

    @Override
    Order createTableOrder(int dishCount, Customer customer){
        return new TableOrder(dishCount, customer);

    }

    @Override
    Order createTableOrder(MenuItem[] dishesArray, Customer customer){
        return new TableOrder(dishesArray, customer);
    }

    @Override
    OrdersManager createInternetOrderManager(){
        return new InternetOrderManager();
    }

    @Override
    OrdersManager createInternetOrderManager(Order[] orders){
        return new InternetOrderManager(orders);
    }

    @Override
    OrdersManager createTableOrdersManager(){
        return new TableOrdersManager();
    }

    @Override
    OrdersManager createTableOrdersManager(int tablesCount){
        return new TableOrdersManager(tablesCount);
    }

}
