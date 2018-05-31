package io;

import barBossHouse.Customer;
import barBossHouse.MenuItem;
import barBossHouse.Order;
import barBossHouse.TableOrder;

import java.time.LocalDateTime;
import java.util.Collection;

public class ControlledTableOrder extends TableOrder {

    protected boolean isChanged = false;

    public ControlledTableOrder() {
        super();
    }

    public ControlledTableOrder(int dishCount, Customer customer) {
        super(dishCount, customer);
    }

    public ControlledTableOrder(MenuItem[] dishesArray, Customer customer) {
        super(dishesArray, customer);
    }

    public ControlledTableOrder(Order order) {
        super((MenuItem[]) order.toArray(), order.getCustomer());
    }

    protected boolean isChanged() {
        return isChanged;
    }

    @Override
    public void setLocalDateTime(LocalDateTime localDateTime) {
        super.setLocalDateTime(localDateTime);
        isChanged = true;
    }

    @Override
    public void setCustomer(Customer customer) {
        super.setCustomer(customer);
        isChanged = true;
    }

    @Override
    public boolean remove(String str) {
        boolean remove = super.remove(str);
        isChanged = true;
        return remove;
    }

    @Override
    public int removeAll(String str) {
        int removeAll = super.removeAll(str);
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
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        boolean addAll = super.addAll(c);
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
    public boolean retainAll(Collection<?> c) {
        boolean retailAll = super.retainAll(c);
        isChanged = true;
        return retailAll;
    }

    /* todo по заданию COMPLETE
   а также переопределяет методы, которые так или иначе меняют состояние класса
   (изменение, добавление, удаление позиций заказа, изменение клиента, времени заказа)
   – в этих методах сначала вызывается реализация метода в суперклассе, а затем изменяется isChanged на true.
 */
}
