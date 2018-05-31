package barBossHouse;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

/*todo не совсем правильно здесь foreach по menuItem юзать, ибо по массиву он пойдет до length, а не size COMPLETE
так как ты реализовал Iterable (через List), то корректнее делать foreach по this */
public class TableOrder implements Order {

    private int size;
    private static final int DEFAULT_SIZE = 16;
    private MenuItem[] menuItems;
    private Customer customer;
    private LocalDateTime localDateTime;

    public TableOrder() {
        this.size = 0;
        this.menuItems = new MenuItem[DEFAULT_SIZE];
        this.localDateTime = LocalDateTime.now();
    }
    //todo где выброс NegativeSizeException COMPLETE
    public TableOrder(int dishCount, Customer customer) {
        this(new MenuItem[dishCount], customer);
        this.localDateTime = LocalDateTime.now();
    }

    public TableOrder(MenuItem[] dishesArray, Customer customer) {
        if(getSize() < 0) throw new NegativeSizeException();
        this.customer = customer;
        menuItems = dishesArray;
        size = getSize();
        this.localDateTime = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public boolean remove(String dishName) {
        for (int i = 0; i < size; i++) {
            if (menuItems[i].getName().equals(dishName)) {
                for (int j = i; j < size; j++) {
                    menuItems[j] = menuItems[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean remove(MenuItem menuItem) {
        for (int i = 0; i < size; i++) {
            if (menuItems[i].equals(menuItem)) {
                for (int j = i; j < size; j++) {
                    menuItems[j] = menuItems[j + 1];
                }
                menuItems[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int removeAll(String dishName) {
        int deletedDishCount = 0;
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (menuItems[i].getName().equalsIgnoreCase(dishName)) {
                menuItems[i] = null;
                deletedDishCount++;
            }
        }
        for (int i = 0; i < size - 1; i++) {
            if (menuItems[i] == null) {
                j = i;
                while ((menuItems[j] == null) & j < size) {
                    shiftArray(menuItems, j);
                    j++;
                }
            }
        }
        size -= deletedDishCount;
        return deletedDishCount;
    }

    public int removeAll(MenuItem menuItem) {
        int deletedDishCount = 0;
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (menuItems[i].equals(menuItem)) {
                menuItems[i] = null;
                deletedDishCount++;
            }
        }
        for (int i = 0; i < size - 1; i++) {
            if (menuItems[i] == null) {
                j = i;
                while ((menuItems[j] == null) & j < size) {
                    shiftArray(menuItems, j);
                    j++;
                }
            }
        }
        size -= deletedDishCount;
        return deletedDishCount;
    }

    public int itemQuantity(String str) {
        int itemQuantity;
        itemQuantity = 0;
        for (MenuItem item : this) { //todo foreach (MenuItem item : this) {} COMPLETE
            if (item.getName().equals(str)) itemQuantity++;
        }
        return itemQuantity;
    }


    public int itemQuantity(MenuItem menuItem) {
        int itemQuatity;
        itemQuatity = 0;
        for (MenuItem item : this) {//todo foreach (MenuItem item : this) {} COMPLETE
            if (item.equals(menuItem)) itemQuatity++;
        }
        return itemQuatity;
    }

    public int costTotal() {//todo foreach (MenuItem item : this) {} COMPLETE
        int costTotal = 0;
        for (MenuItem item : this) {
            costTotal += item.getCost();
        }
        return costTotal;
    }

    public int getDishSumPrice(String name) {
        int getDishSumPrice = 0;
        for (MenuItem item : this) {//todo foreach (MenuItem item : this) {} COMPLETE
            if (item.getName().equalsIgnoreCase(name)) getDishSumPrice += item.getCost();
        }
        return getDishSumPrice;
    }

    public String[] itemsName() {
        if (size != 0) {
            String[] buffer = new String[size];
            boolean isAdded = false;
            int indx = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < indx; j++) {
                    if (buffer[j].equalsIgnoreCase(menuItems[i].getName())) {
                        isAdded = true;
                        break;
                    }
                }
                if (isAdded) {
                    isAdded = false;
                    continue;
                } else {
                    buffer[indx] = menuItems[i].getName();
                    indx++;
                }
            }
            String[] dishesNames = new String[indx];
            System.arraycopy(dishesNames, 0, buffer, 0, buffer.length);
            return dishesNames;
        }
        return null;
    }

    public MenuItem[] sortedItemsByCostDesc() {
        if (size != 0) {
            MenuItem[] sortedItemsByCostDesc = toArray(menuItems);
            Arrays.sort(sortedItemsByCostDesc);
            return sortedItemsByCostDesc;
        }
        return null;
    }

    public int getDishCount(MenuItem menuItem) {
        if (size != 0) {
            int getDishCount = 0;
            for (MenuItem item : this) {//todo foreach (MenuItem item : this) {} COMPLETE
                if (item.equals(menuItem))
                    getDishCount++;
            }
            return getDishCount;
        } else return 0;
    }

    public int getDishCount(String name) {
        if (size != 0) {
            int getDishCount = 0;
            for (MenuItem item : this) {//todo foreach (MenuItem item : this) {} COMPLETE
                if (item.getName().equals(name))
                    getDishCount++;
            }
            return getDishCount;
        } else return 0;
    }

    private int getSize() {
        //todo какого здесь цикл делает? У тебя же size поле COMPLETE
        return size;
    }

    public static void quickSortDishes(MenuItem[] array, int lowIndx, int highIndx) {
        MenuItem dish = array[(lowIndx + highIndx) / 2];
        int dishPrice = dish.getCost();

        while (lowIndx <= highIndx) {
            while (array[lowIndx].getCost() > dishPrice) lowIndx++;
            while (array[highIndx].getCost() < dishPrice) highIndx--;
            if (lowIndx <= highIndx) {
                MenuItem tmpDish = array[highIndx];
                array[highIndx] = array[lowIndx];
                array[lowIndx] = tmpDish;
                lowIndx++;
                highIndx--;
            }
            if (lowIndx < highIndx) quickSortDishes(array, lowIndx, highIndx);
            if (highIndx > lowIndx) quickSortDishes(array, lowIndx, highIndx);
        }
    }


    private static void shiftArray(MenuItem[] array, int indx) {
        for (int i = indx; i < array.length + 1; i++)
            array[i] = array[i + 1];
    }

    @Override
    public String toString() {
        //todo вот зачем ты делаешь это здесь? циклом по this.menuItems при i=0..size не? COMPLETE
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append(":").append(size).append("\n");
        for (MenuItem item : this) {//todo foreach (MenuItem item : this) {} COMPLETE
            stringBuilder.append(item.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || !(obj instanceof TableOrder))
            return false;

        TableOrder tableOrder = (TableOrder) obj;

        if (this.size != tableOrder.getSize())
            return false;

        int hashCodeTableOrder = 0;
        int hashCodeEquals = 0;

        for (MenuItem item : this) //todo foreach (MenuItem item : this) {} COMPETE
            hashCodeTableOrder += item.hashCode();

        for (MenuItem item : tableOrder.menuItems)
            hashCodeEquals += item.hashCode();

        return (customer.equals(((TableOrder) obj).getCustomer()) & hashCodeEquals == hashCodeTableOrder);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (MenuItem item : this) { //todo foreach (MenuItem item : this) {} COMPLETE
            hash ^= item.hashCode();
        }
        return customer.hashCode()
                ^ size
                ^ hash
                ^ localDateTime.hashCode();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {

        for (MenuItem m : menuItems) {
            if (m.equals(o)) return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(menuItems, size);
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return new Iterator<MenuItem>() {
            int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public MenuItem next() {
                if (pos >= size)
                    throw new NoSuchElementException();
                return menuItems[pos++];
            }
        };
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(menuItems, size(), a.getClass());
    }

    @Override
    public boolean add(MenuItem menuItem) {
        if (menuItem instanceof Drink) {
            Drink drink = (Drink) menuItem;
            if (drink.getAlcoholVol() > 0 && customer.getAge() < 18) throw new UnlawfulActionException("Тебе нет 18, пиздюк");
        }
        if (size > menuItems.length) {
            MenuItem[] newDish = new MenuItem[size * 2];
            System.arraycopy(menuItems, 0, newDish, 0, size);
            menuItems = newDish;
        }
        menuItems[size] = menuItem;
        size++;
        return true;
    }


    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (menuItems[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public MenuItem remove(int index) {
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();

        MenuItem menuItem = menuItems[index];

        if (index < this.size - 1)
            System.arraycopy(menuItems, index + 1, menuItems, index, this.size - (index + 1));
        menuItems[size - 1] = null;
        size--;

        return menuItem;
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
    public boolean addAll(Collection<? extends MenuItem> c) {
        boolean addAll = true;

        if (size + c.size() > menuItems.length) {
            MenuItem[] copyMenuItems = new MenuItem[this.size * 2];
            System.arraycopy(this.menuItems, 0, copyMenuItems, 0, this.size);
            this.menuItems = copyMenuItems;
        }

        for (MenuItem menuItem : c) {
            addAll &= add(menuItem);
        }
        return addAll;
    }

    private void expand(int addSize) {
        if (this.size + addSize > this.menuItems.length) {
            MenuItem[] copyMenuItems = new MenuItem[this.size * 2 + addSize];
            System.arraycopy(menuItems, 0, copyMenuItems, 0, this.size);
            this.menuItems = copyMenuItems;
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();

        int sizeCollection = c.size();
        for (MenuItem o : c) {
            if (contains(o))
                sizeCollection--;
        }
        expand(sizeCollection);

        if (index < size)
            System.arraycopy(menuItems, index, menuItems, index + c.size(), menuItems.length - (index + c.size()));

        if (c.size() == 0)
            return false;

        for (MenuItem o : c) {
            if (!contains(o)) {
                menuItems[index++] = o;
                size++;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean deleted = false;
        for (Object o : c) {
            if (contains(o)) {
                remove(o);
                deleted = true;
            }
        }
        return deleted;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean saved = false;
        for (MenuItem o : menuItems) {
            if (!c.contains(o)) {
                remove(o);
                saved = true;
            }
        }
        return saved;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            menuItems[i] = null;
        }
        size = 0;
    }

    @Override
    public MenuItem get(int index) {
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();
        return menuItems[index];
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();
        MenuItem menuItem = menuItems[index];
        menuItems[index] = element;
        return menuItem;
    }

    @Override
    public void add(int index, MenuItem element) {
        if (index < 0 || index >= size - 1)
            throw new IndexOutOfBoundsException();
        expand(1);

        if (index < size - 1)
            System.arraycopy(menuItems, index, menuItems, index + 1, size - index);

        menuItems[index] = element;
        if (size < menuItems.length)
            size++;
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size; i++){
            if(menuItems[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = size - 1; i > -1; i--){
            if(menuItems[i].equals(o))
                return i;
        }
        return -1;
    }

    public ListIterator<MenuItem> listIterator() {
        return listIterator(0);
    }

    //todo не do - копипаст у Киржаева =))))) COMPLETE Дикая случайность (нет)
    @Override
    public ListIterator<MenuItem> listIterator(int index) {
        if(index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();

        TableOrder tableOrder = this;
        return new ListIterator<MenuItem>() {
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
                return menuItems.length > pos;
            }

            public MenuItem next() {
                switch (lastOperation) {
                    case ADD:
                        lastOperation = ListIteratorOperation.NEXT;
                        return menuItems[pos];
                    default:
                        lastOperation = ListIteratorOperation.NEXT;
                        if(pos >= size)
                            throw new NoSuchElementException();
                        return menuItems[pos++];
                }
            }

            public boolean hasPrevious() {
                return pos > 0;
            }

            public MenuItem previous() {
                switch (lastOperation) {
                    case ADD:
                        lastOperation = ListIteratorOperation.PREVIOUS;
                        pos = newElementPos;
                        return menuItems[pos];
                    default:
                        lastOperation = ListIteratorOperation.PREVIOUS;
                        if(pos < 0)
                            throw new NoSuchElementException();
                        return menuItems[pos--];
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
                        tableOrder.remove(--pos);
                        break;
                    case PREVIOUS:
                        tableOrder.remove(pos + 1);
                        break;
                    default:
                        illegalState();
                }
                lastOperation = ListIteratorOperation.REMOVE;
            }

            public void set(MenuItem menuItem) {
                switch (lastOperation) {
                    case NEXT:
                        tableOrder.set(pos - 1, menuItem);
                        break;
                    case PREVIOUS:
                        tableOrder.set(pos + 1, menuItem);
                        break;
                    default:
                        illegalState();
                }
                lastOperation = ListIteratorOperation.SET;
            }

            public void add(MenuItem menuItem) {
                if(size == 0) {
                    tableOrder.add(menuItem);
                }
                else{
                    switch (lastOperation){
                        case NONE:
                            tableOrder.add(0, menuItem);
                            pos++;
                            break;
                        case NEXT:
                            newElementPos = pos - 1;
                            if(pos - 1 < 0)
                                tableOrder.add(0, menuItem);
                            else
                                tableOrder.add(newElementPos, menuItem);
                            pos++;
                            break;
                        case PREVIOUS:
                            newElementPos = pos + 2;
                            if(pos + 2 > size - 1)
                                tableOrder.add(menuItem);
                            else
                                tableOrder.add(newElementPos, menuItem);
                            break;
                    }
                }
                lastOperation = ListIteratorOperation.ADD;
            }
        };
    }

    @Override
    public List<MenuItem> subList(int fromIndex, int toIndex){
        if(fromIndex < 0 || toIndex > size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        if(fromIndex == toIndex)
            return new TableOrder();
        TableOrder subList = new TableOrder( toIndex - fromIndex, customer);

        for (int i = fromIndex, j = 0; i < toIndex; i++, j++)
            subList.menuItems[j] = menuItems[i];

        subList.size = toIndex - fromIndex;
        return subList;
    }

}
