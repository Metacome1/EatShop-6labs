package barBossHouse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class TableOrdersManager implements OrdersManager, List<Order> {
    private static final  int MIDLE_COST = 500;
    private static final int DEFAULT_CAPACITY_VALUE = 0;
    private TableOrder[] orders;
    private int capacity = DEFAULT_CAPACITY_VALUE;

    public TableOrdersManager(){}

    //todo где выброс NegativeSizeException COMPLETE
    public TableOrdersManager(int tablesCount) {
        if(capacity < 0) throw new NegativeSizeException();
        orders = new TableOrder[tablesCount];
        capacity = tablesCount;
    }

    public void add(int tableNumber, Order newOrder) {
        if (newOrder != null & isValidNumber(tableNumber)) {
            orders[tableNumber] =  (TableOrder) newOrder;
            capacity++;
        } else throw new AlreadyAddedException("this table is busy!");
    }

    public TableOrder getOrderByTableNumber(int tableNumber) {
        if (isValidNumber(tableNumber)) {
            return orders[tableNumber];
        } else return null;
    }

    //todo вот зачем ты здесь проверяешь на UnlawfulActionException, когда в методе add Order-а оно все равно проверяется и выбрасывается COMPLETE
    public boolean addDishToOrder(int tableNumber, MenuItem menuItem) {
        if (orders[tableNumber] != null) throw new NoFreeTableException("Столик под номером:" + tableNumber + " занят");
        if (menuItem != null) {
            if (isValidNumber(tableNumber)) {
                orders[tableNumber].add(menuItem); //ибо этот метод выбросит тебе UnlawfulActionException если что не так
                return true;
            }
        }
        return false;
    }

    public void freeTable(int tableNumber) {
        if (isValidNumber(tableNumber)) {
            orders[tableNumber] = null;
            capacity--;
        }
    }

    public int getFirstFreeTableNumber() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) return i;
        }
        throw new NoFreeTableException("Нет свободных столиков");
    }

    //todo нене в TableOrderManager логика другая. Тут сдвигать массив после удаления не надо COMPLETE
    public int remove(Order order) throws IOException {
        for(int i = 0; i < this.orders.length; i++) {
            if (this.orders[i] != null && this.orders[i].equals(order)) {
                this.orders[i] = null;
                capacity--;
                return i;
            }
        }
        return -1;
    }

    //todo тип параметра - интерфейс Order COMPLETE
    public int removeAll(Order order) {
        int removeAllMenuItems;
        removeAllMenuItems = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(order)) {orders[i] = null;
            removeAllMenuItems++;
            }
        }
        if(removeAllMenuItems > 0) return removeAllMenuItems;
        return (-1);
    }

    public int[] getFreeTables() {
        return getTables(x -> x == null);
    }

    public int[] getNonFreeTables() {
        return getTables(x -> x != null);
    }

    public int[] getTables(Predicate<TableOrder> predicate){
        int[] result = null;
        if (capacity !=0) {
            result = new int[capacity];
            int j = 0;
            for (int i = 0; i < capacity; i++) {
                if (predicate.test(orders[i])) {
                    result[j] = i;
                    j++;
                }
            }
        }
        return result;
    }


    public int ordersCostSummary() {
        int cents = 0;
       //todo ненене нафиг здесь массив создавать. foreach (Order order : this) с проверкой Order!=null COMPLETE
         {
            for (Order order : this)
                if (order != null)
                cents += order.costTotal();
        }
        return cents;
    }


    public int getDishesCountInOrders() {
        int getDishesCountInOrders = 0;
        //todo ненене нафиг здесь массив создавать. foreach (Order order : this) с проверкой Order!=null COMPLETE
            for (Order order : this)
                if (order != null)
                    for (MenuItem menuItem : order)
                        getDishesCountInOrders ++;

        return getDishesCountInOrders;
    }



    private boolean isValidNumber(int number) {
        if (number > -1 & number < orders.length) {
            return true;
        } else {
            return false;
        }
    }

    public int itemQuantity(String name)
    {
        int itemQuantity = 0;
        //todo ненене нафиг здесь массив создавать. foreach (Order order : this) с проверкой Order!=null COMPLETE
            for (Order order : this)
                if (order != null)
                    for (MenuItem menuItem : order)
                       if (menuItem.getName().equals(name)) itemQuantity ++;
            return itemQuantity;
    }

    //odo аналогично InternetOrderManager COMPLITED

    public int itemQuantity(MenuItem menuItem)  //todo ненене нафиг здесь массив создавать. foreach (Order order : this) с проверкой Order!=null COMPLETE
    {
        int itemQuantity = 0;
        for (Order order : this)
            if (order != null)
                for (MenuItem item : order)
                    if (item.getName().equals(menuItem)) itemQuantity ++;
        return itemQuantity;
    }

    @Override
    public int countMenuItemsNowDay(LocalDate localDate) {
        int countMenuItemsNowDay = 0;
        //todo и нафиг тебе не используемая переменная? COMPLETE
        for (Order order : this) { //todo  foreach (Order order : this) с проверкой Order!=null COMPLETE
            if (order != null)
                if (localDate.equals(order.getLocalDateTime().toLocalDate())) countMenuItemsNowDay++;
        }
        return countMenuItemsNowDay;
    }

    @Override
    public OrdersManager getMenuItemNowDay(LocalDate localDate){
        //todo А какого компилятора в классе TableOrderManager у тебя возвращается InternetOrderManager?!?! COMPLETE
        TableOrdersManager tableOrdersManager = new TableOrdersManager();
        for (Order order : this) //todo  foreach (Order order : this) с проверкой Order!=null COMPLETE
            if (order != null)
                if(order.getLocalDateTime().toLocalDate().equals(localDate)) add(order);
        return tableOrdersManager;
    }

    public OrdersManager getMenuItemsCustomer(Customer customer){
        //todo А какого компилятора в классе TableOrderManager у тебя возвращается InternetOrderManager?!?! COMPLETE
        TableOrdersManager tableOrdersManager = new TableOrdersManager();
        for (Order order : this) {//todo  foreach (Order order : this) с проверкой Order!=null COMPLETE
            if (order != null)
                if(order.getCustomer().equals(customer))add(order);
        }
        return tableOrdersManager;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return capacity == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Order order: orders) {
            if (order.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<Order> iterator() {
        return new Iterator<Order>() {
            int pos = 0;

            public boolean hasNext() {
                return capacity > pos;
            }

            public Order next() {
                if(pos >= capacity)
                    throw new NoSuchElementException();
                return orders[pos++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        TableOrder[] getOrders;
        if (capacity != 0) {
            getOrders = new TableOrder[capacity];
            int j = 0;
            for (TableOrder order : orders) {
                if (order != null) {
                    getOrders[j] = order;
                    j++;
                }
            }
            return getOrders;
        }
        else return new TableOrder[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(toArray(), size(), a.getClass());
    }

    @Override
    public boolean add(Order order) {
        int num = getFirstFreeTableNumber();
        add(num, (TableOrder) order);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int n = indexOf(o);
        if (n >= 0) {
            freeTable(n);
            return true;
        }
        return false;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        boolean addAll = true;
        for (Order order: c) {
            addAll &= add(order);
        }
        return addAll;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Order> c) {
         //todo вот почему в методе выше ты не делал массив из коллекции, а здесь почему то решил?!?! Метод выше корректный COMPLETE
        for (Object order : c) {
            if (!add((Order) order)) return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
         //todo for по c, а не по массиву, полученному из c COMPLETE
        for (Object order : c) {
            if (!remove(order)) return false;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (Order o: orders) {
            if(!c.contains(o)){
                try {
                    remove(o);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            orders[i] = null;
        }
        capacity = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        TableOrdersManager tableOrdersManager = (TableOrdersManager) obj;

        if(this.capacity != tableOrdersManager.capacity)
            return false;

        int firstHashCode = 0;
        int secondHashCode = 0;

        for (Order order : this) //todo  foreach (Order order : this) с проверкой Order!=null COMPLETE
            firstHashCode += order.hashCode();

        for (Order order : tableOrdersManager)
        secondHashCode += order.hashCode();

        return firstHashCode == secondHashCode;
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(orders); //todo а нафига capacity? COMPLETE
    }

    @Override
    public Order get(int index) {
        if(index < 0 || index > capacity - 1)
            throw new IndexOutOfBoundsException();

        return orders[index];
    }

    @Override
    public Order set(int index, Order element) {
        if (index > 0 & index < orders.length) {
            Order order = orders[index];
            orders[index] = (TableOrder) element;
            return order;
        }
        return null;
    }

    //todo удали этот метод, а addOrder переименуй под add и т.д. - это про удаление дублей методов COMPLETE

    @Override
    public Order remove(int index) {
        if (index >= 0 & index < orders.length) {
            Order ord = orders[index];
            freeTable(index);
            return ord;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        Order order = (Order) o;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(order)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Order ord = (Order) o;
        for (int i = orders.length; i > 0; i--) {
            if (orders[i].equals(ord)) return i;
        }
        return -1;
    }

    @Override
    public ListIterator<Order> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<Order> listIterator(int index) {
        if(index < 0 || index > capacity - 1)
            throw new IndexOutOfBoundsException();

        TableOrdersManager tableOrdersManager = this;
        return new ListIterator<Order>() {
            int pos = index;
            int newElementPos = 0;

            ListIteratorOperation lastOperation = ListIteratorOperation.NONE;

            private void illegalState(){
                switch (lastOperation){
                    case NONE:
                        throw new IllegalStateException("Не были вызваны методы \"next()\" или \"previous()\"");
                    case ADD:
                        throw new IllegalStateException("Последний вызов: \"add()\"");
                    case REMOVE:
                        throw new IllegalStateException("Последний вызов: \"remove()\"");
                }
            }

            public boolean hasNext() {
                return orders.length > pos;
            }

            public Order next() {
                switch (lastOperation) {
                    case ADD:
                        lastOperation = ListIteratorOperation.NEXT;
                        return orders[pos];
                    default:
                        lastOperation = ListIteratorOperation.NEXT;
                        if(pos >= capacity)
                            throw new NoSuchElementException();
                        return orders[pos++];
                }
            }

            public boolean hasPrevious() {
                return pos > 0;
            }

            public Order previous() {
                switch (lastOperation) {
                    case ADD:
                        lastOperation = ListIteratorOperation.PREVIOUS;
                        pos = newElementPos;
                        return orders[pos];
                    default:
                        lastOperation = ListIteratorOperation.PREVIOUS;
                        if(pos < 0)
                            throw new NoSuchElementException();
                        return orders[pos--];
                }
            }

            public int nextIndex() {
                return pos + 1;
            }

            public int previousIndex() {
                return pos - 1;
            }
            public void remove() {
                switch (lastOperation) {
                    case NEXT:
                        tableOrdersManager.remove(--pos);
                        break;
                    case PREVIOUS:
                        tableOrdersManager.remove(pos + 1);
                        break;
                    default:
                        illegalState();
                }
                lastOperation = ListIteratorOperation.REMOVE;
            }

            public void set(Order order) {
                switch (lastOperation) {
                    case NEXT:
                        tableOrdersManager.set(pos - 1, order);
                        break;
                    case PREVIOUS:
                        tableOrdersManager.set(pos + 1, order);
                        break;
                    default:
                        illegalState();
                }
                lastOperation = ListIteratorOperation.SET;
            }

            public void add(Order order) {
                if(capacity == 0) {
                    tableOrdersManager.add(order);
                }
                else{
                    switch (lastOperation){
                        case NONE:
                            tableOrdersManager.add(0, order);
                            pos++;
                            break;
                        case NEXT:
                            newElementPos = pos - 1;
                            if(pos - 1 < 0)
                                tableOrdersManager.add(0, order);
                            else
                                tableOrdersManager.add(newElementPos, order);
                            pos++;
                            break;
                        case PREVIOUS:
                            newElementPos = pos + 2;
                            if(pos + 2 > capacity - 1)
                                tableOrdersManager.add(order);
                            else
                                tableOrdersManager.add(newElementPos, order);
                            break;
                    }
                }
                lastOperation = ListIteratorOperation.ADD;
            }
        };
    }

    @Override
    public List<Order> subList(int fromIndex, int toIndex){
        if(fromIndex < 0 || toIndex > capacity || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        if(fromIndex == toIndex)
            return new TableOrdersManager();
        TableOrdersManager subList = new TableOrdersManager(toIndex - fromIndex);

        for (int i = fromIndex, j = 0; i < toIndex; i++, j++)
            subList.orders[j] = orders[i];

        subList.capacity = toIndex - fromIndex;

        return subList;
    }



}
