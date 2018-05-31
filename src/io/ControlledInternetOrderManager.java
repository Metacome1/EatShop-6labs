package io;

import barBossHouse.InternetOrderManager;
import barBossHouse.Order;
import barBossHouse.OrdersFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

//todo аналогично ControlledTableOrderManager COMPLETE
public class ControlledInternetOrderManager extends InternetOrderManager {

    protected Source<Order> orderSource;
    private OrdersFactory ordersFactory;

    public ControlledInternetOrderManager(Source<Order> source, OrdersFactory factory){
        super();
        orderSource = source;
        ordersFactory = factory;
    }

    public ControlledInternetOrderManager(Order[] orders, Source<Order> source, OrdersFactory factory){
        super(orders);
        orderSource = source;
        ordersFactory = factory;
    }

    private ControlledInternetOrder createControlledOrder(Order order) {
        ControlledInternetOrder internetOrder = (ControlledInternetOrder) ordersFactory.createInternetOrder(order);
        this.create(internetOrder);
        return internetOrder;
    }
    private List<? extends Order> createControlledOrders(Collection<? extends Order> collection) {
        ControlledInternetOrder[] internetOrders = new ControlledInternetOrder[collection.size()];
        int i = 0;
        for (Order order : collection) {
            internetOrders[i++] = createControlledOrder(order);
        }
        return Arrays.asList(internetOrders);
    }



    @Override
    public void clear() {
        try {
            for (Order order : this) {
                orderSource.delete(order);
            }
        } catch (IOException ex) {
            ex.printStackTrace();//i don't know
        }
        super.clear();
    }

    @Override
    public boolean remove(Object o) {
        try {
            orderSource.delete((Order) o);
            return super.remove(o);
        } catch (IOException ex) {
            return false;
        }

    }

    @Override
    public Order pollFirst() {
        try {
            orderSource.delete(super.peek());
            return super.pollFirst();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public Order pollLast() {
        try {
            orderSource.delete(super.peekLast());
            return super.pollLast();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Order> collection) {
        return super.addAll(createControlledOrders(collection));
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        try {
            for (Object obj : c) {
                orderSource.deleteAll((Order) obj);
                super.remove(obj);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        try {
            for (Order order : this) {
                if (!collection.contains(order))
                    orderSource.delete(order);
            }
            return super.retainAll(collection);
        } catch (IOException ex ) {
            return false;
        }
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        try {
            orderSource.delete((Order) o);
            return super.removeFirstOccurrence(o);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        try {
            orderSource.delete((Order) o);
            return super.removeLastOccurrence(o);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean offer(Order order) {
        return super.offer(createControlledOrder(order));
    }

    @Override
    public Order poll() {
        try {
            orderSource.delete(super.peek());
            return super.poll();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void push(Order order) {
        super.push(createControlledOrder(order));
    }

    @Override
    public Order pop() {
        try {
            orderSource.delete(super.getFirst());
            return super.pop();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void addFirst(Order order) {
        super.addFirst(createControlledOrder(order));
    }

    @Override
    public void addLast(Order order) {
        super.addLast(createControlledOrder(order));
    }

    @Override
    public boolean offerFirst(Order order) {
        return super.offerFirst(createControlledOrder(order));
    }

    @Override
    public boolean offerLast(Order order) {
        return super.offerLast(createControlledOrder(order));
    }

    @Override
    public Order removeFirst() {
        try {
            orderSource.delete(super.getFirst());
            return super.removeFirst();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public Order removeLast() {
        try {
            orderSource.delete(super.getLast());
            return super.removeLast();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public boolean add(Order order) {
        return super.add(createControlledOrder(order));
    }


    @Override
    public Order remove() {
        try {
            orderSource.delete(super.getFirst());
            return super.remove();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public boolean remove(Order order) {
        try {
            orderSource.delete(order);
            return super.remove(order);
        } catch (IOException ex) {
            return false;
        }
    }

    private void create(ControlledInternetOrder order) {
        try {
            orderSource.create(order);
        } catch (IOException ex) {}
    }

    public void load() throws IOException {
        for (Order order : this) {
            orderSource.load(order);
        }
    }

    public void store() {
        for (Order order : this) {
            if (order instanceof ControlledInternetOrder && ((ControlledInternetOrder) order).isChanged())
                this.create((ControlledInternetOrder) order);
        }
    }

}
