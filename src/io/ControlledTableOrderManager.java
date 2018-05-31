package io;

import barBossHouse.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;

public class ControlledTableOrderManager extends TableOrdersManager {
    protected Source<Order> orderSource;
    private OrdersFactory ordersFactory;

    public ControlledTableOrderManager(Source<Order> source, OrdersFactory factory) {
        super();
        orderSource = source;
        ordersFactory = factory;
    }

    public ControlledTableOrderManager(int tablesCount,Source<Order> source, OrdersFactory factory) {
        super(tablesCount);
        orderSource = source;
        ordersFactory = factory;
    }
    //todo добавь коструктор, который принимает source и OrderFactory, и храни их в полях класса  COMPLETE
    public Source<Order> getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Source<Order> orderSource) {
        this.orderSource = orderSource;
    }


    private ControlledTableOrder createControlledTableOrder(Order order) {
        ControlledTableOrder tableOrder = (ControlledTableOrder) ordersFactory.createTableOrder(order);
        this.create(tableOrder);
        return tableOrder;
    }

    private List<? extends Order> createControlledTableOrders(Collection<? extends Order> collection){
        ControlledTableOrder[] tableOrders = new ControlledTableOrder[collection.size()];
        int i = 0;
        for (Order order : collection) {
            tableOrders[i++] = createControlledTableOrder(order);
        }
        return Arrays.asList(tableOrders);
    }



    /*todo нада все методы, добавления\удаления\изменения\создания переопределить: COMPLETE
    @Override
    public boolean addDishToOrder(int tableNumber, MenuItem menuItem) {
        return super.addDishToOrder(tableNumber, menuItem);
    }

    @Override
    public void freeTable(int tableNumber) {
        super.freeTable(tableNumber);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Order> c) {
        return super.addAll(index, c);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Order remove(int index) {
        return super.remove(index);
    }

    @Override
    public List<Order> subList(int fromIndex, int toIndex) {
        return super.subList(fromIndex, toIndex);
    }
    */

    //todo копипастер хренов! Какой нафиг controlledDepartment COMPLETE
    //todo а вот создание объекта - с помощью фабрики делается, а не конструктора COMPLETE
    //todo вот именно в этом классе и вызываются методы source, а не в фабрике! COMPLETE

    @Override
    public boolean add(Order order){
        return super.add(createControlledTableOrder(order));
    }


    @Override
    public void add(int i, Order order){
        super.add(i, createControlledTableOrder(order));
    }

    @Override
    public Order remove(int tableNumber) {
        try {
            orderSource.delete(super.get(tableNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.remove(tableNumber);
    }

    @Override
    public int remove(Order order) throws IOException {
            orderSource.delete(order);
            return super.remove(order);
        }

    @Override
    public int removeAll(Order order) {
        try {
            orderSource.deleteAll(order);
        } catch (IOException ex) {
            return 0;
        }
        return super.removeAll(order);
    }

    @Override
    public boolean addAll(int i, Collection<? extends Order> collection) {
        return super.addAll(i, createControlledTableOrders(collection));
    }

    @Override
    public Order set(int i, Order order) {
        Order oldOrder = super.set(i, createControlledTableOrder(order));
        try {
            orderSource.delete(oldOrder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oldOrder;
    }


    @Override
    public boolean remove(Object o) {
        try {
            orderSource.delete((Order) o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.remove(o);

    }

    @Override
    public boolean addAll(Collection<? extends Order> collection){ //todo копипастер хренов! Какой нафиг controlledDepartment COMPLETE
        return super.addAll(createControlledTableOrders(collection));//todo а вот создание объекта - с помощью фабрики делается, а не конструктора COMPLETE
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object obj : collection) {
            try {
                orderSource.delete((Order) obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        for (Order order : this) {
            if (!collection.contains(order)) {
                try {
                    orderSource.delete(order);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.retainAll(collection);
    }

    @Override
    public void clear() {
        for (Order order : this) {
            try {
                orderSource.delete(order);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.clear();
    }

    public void store() throws IOException {
        for (Order order : this) {
            if (order instanceof ControlledTableOrder && ((ControlledTableOrder) order).isChanged) {
                this.create((ControlledTableOrder) order);
            }
        }
    }

    private void create(ControlledTableOrder order) {
        try {
            orderSource.create(order);
        } catch (IOException ex) {
        }
    }

    public void load() throws IOException {
        for (Order order : this) {
            orderSource.load(order);
        }
    }
}
