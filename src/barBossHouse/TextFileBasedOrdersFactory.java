package barBossHouse;

import io.*;

//todo объясню на примере этой фабрики. В остальных аналогично. COMPLETE
public class TextFileBasedOrdersFactory extends OrdersFactory {

    //todo Во-первых, Здесь хранишь не source, а ссылку на PATH!! NECOMPLETE, but looked this shit!!!

    private OrderManagerTextFileSource source;

    public TextFileBasedOrdersFactory(String path) {
        source = new OrderManagerTextFileSource(path);
    }

    //todo во-вторых, вызывать source.create() при создании Order в фабрике НЕ НАДО - его вызываешь в OrderManager, в вызовах методо Add, AddAll COMPLETE
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
    public Order createInternetOrder(Customer customer,MenuItem[] menuItems) {
        return new ControlledInternetOrder(customer, menuItems);
    }

    @Override
    public Order createInternetOrder(Order order) {
        return new ControlledInternetOrder(order);
    }

    /*todo в третьих, в методах создания OrderManager-ов, COMPLETE
      1) нужно создавать соответсвующий source (путь берется из атрибута фабрики Path)
      2) в OrderManager ты передаешь ссылку на этот source, и на ЭТУ ФАБРИКУ (this)
      3) фабрика будет использоваться OrderManager-ом для создания ордеров
      4) а потом, логично нужно вызывать sourse.create(controlledInternetOrderManager)
      при вызове каждого метода создания Ордерменеджера будет создаваться новый сурс
      Далее смотри todoшки в controlledTableOrderManager
     */
    @Override
    public OrdersManager createTableOrdersManager() {
        return new ControlledTableOrderManager(source, this);
    }

    @Override
    public OrdersManager createTableOrdersManager(int numberOfTables) {
        return new ControlledTableOrderManager(numberOfTables ,source, this);
    }

    @Override
    public OrdersManager createInternetOrderManager() {
        return new ControlledInternetOrderManager(source, this);
    }

    @Override
    public OrdersManager createInternetOrderManager(Order[] orders) {
        return new ControlledInternetOrderManager(orders, source, this);
    }

}
