package io;

import barBossHouse.Customer;
import barBossHouse.InternetOrder;
import barBossHouse.MenuItem;
import barBossHouse.Order;

import java.time.LocalDateTime;
import java.util.Collection;

public class ControlledInternetOrder extends InternetOrder {

    protected boolean isChanged = false;

    public ControlledInternetOrder() {
        super();
    }

    public ControlledInternetOrder(Customer customer, MenuItem[] menuItems) {
        super(customer, menuItems);
    }

    public ControlledInternetOrder(Order order) {
        super(order.getCustomer(), (MenuItem[]) order.toArray());
    }

    protected boolean isChanged() {
        return isChanged;
    }

    @Override
    public void setCustomer(Customer customer) {
        super.setCustomer(customer);
        isChanged = true;
    }

    @Override
    public void setLocalDateTime(LocalDateTime localDateTime) {
        super.setLocalDateTime(localDateTime);
        isChanged = true;
    }

    @Override
    public boolean remove(String dishName) {
        boolean remove = super.remove(dishName);
        isChanged = true;
        return remove;
    }

    @Override
    public boolean remove(MenuItem menuItem) {
        boolean remove = super.remove(menuItem);
        isChanged = true;
        return remove;
    }

    @Override
    public int removeAll(String dishName) {
        int removeAll = super.removeAll(dishName);
        isChanged = true;
        return removeAll;
    }

    @Override
    public int removeAll(MenuItem menuItem) {
        int removeAll = super.removeAll(menuItem);
        isChanged = true;
        return removeAll;
    }

    @Override
    public boolean add(MenuItem menuItem) {
        boolean add = super.add(menuItem);
        isChanged = true;
        return add;
    }

    @Override
    public boolean addAll(Collection<? extends MenuItem> c) {
        boolean addAll = super.addAll(c);
        isChanged = true;
        return addAll;
    }

    @Override
    public boolean remove(Object o) {
        boolean remove = super.remove(o);
        isChanged = true;
        return remove;
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        boolean addAll = super.addAll(index, c);
        isChanged = true;
        return addAll;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removeAll = super.removeAll(c);
        isChanged = true;
        return removeAll;
    }

    @Override
    public void clear() {
        super.clear();
        isChanged = true;
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        MenuItem set = super.set(index, element);
        isChanged = true;
        return set;
    }

    @Override
    public void add(int index, MenuItem element) {
        super.add(index, element);
        isChanged = true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean retainAll = super.retainAll(collection);
        isChanged = true;
        return retainAll;
    }


    @Override
    public MenuItem remove(int index) {
        MenuItem remove = super.remove(index);
        isChanged = true;
        return remove;
    }


/* todo по заданию COMPLETE
   а также переопределяет методы, которые так или иначе меняют состояние класса
   (изменение, добавление, удаление позиций заказа, изменение клиента, времени заказа)
   – в этих методах сначала вызывается реализация метода в суперклассе, а затем изменяется isChanged на true.
 */

}
