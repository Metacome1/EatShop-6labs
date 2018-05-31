package barBossHouse;

import java.time.LocalDateTime;
import java.util.*;


// odo переписать список СДЕЛАНО
public class InternetOrder implements Order {
    private int size;
    private ListNode head;
    private ListNode tail;
    private Customer customer;
    private LocalDateTime localDateTime;

    public InternetOrder() {
        head = null;
    }

    public InternetOrder(Customer customer, MenuItem[] menuItems) {
        this.customer = customer;
        this.size = menuItems.length;
        this.localDateTime = LocalDateTime.now();
        addItems(menuItems);
    }

    private void addItems(MenuItem[] menuItems) {
        if (menuItems.length > 0) {
            for (MenuItem menuItem : menuItems) { //todo foreach COMPLETE
                add(menuItem);
            }
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }



    public boolean remove(String dishName) {
        ListNode listNode = head;
        while (listNode != null) {
            if (listNode.getValue().getName().equals(dishName)) {
                if (listNode == head) {
                    listNode.getNext().setPrev(null);
                    head = listNode.getNext();
                } else if (listNode == tail) {
                    listNode.getPrev().setNext(null);
                    tail = listNode.getPrev();
                } else {
                    listNode.getPrev().setNext(listNode.getNext());
                    listNode.getNext().setPrev(listNode.getPrev());
                }
                size--;
                return true;
            }
            listNode = listNode.getNext();
        }
        return false;
    }

    public boolean remove(MenuItem menuItem) {
        //odo хранишь ссылку на предыдущий нод COMPLITED
        ListNode listNode = head;
        while (listNode != null) {
            if (listNode.getValue().equals(menuItem)) {
                if (listNode == head) {
                    listNode.getNext().setPrev(null);
                    head = listNode.getNext();
                } else if (listNode == tail) {
                    listNode.getPrev().setNext(null);
                    tail = listNode.getPrev();
                } else {
                    listNode.getPrev().setNext(listNode.getNext());
                    listNode.getNext().setPrev(listNode.getPrev());
                }
                size--;
                return true;
            }
            listNode = listNode.getNext();
        }
        return false;
    }

    public int removeAll(String dishName) {

        int deletedDishCount = 0;
        ListNode listNode = head;
        while (listNode != null) {
            if (listNode.getValue().getName().equals(dishName)) {
                if (listNode == head) {
                    listNode.getNext().setPrev(null);
                    head = listNode.getNext();
                } else if (listNode == tail) {
                    listNode.getPrev().setNext(null);
                    tail = listNode.getPrev();
                } else {
                    listNode.getPrev().setNext(listNode.getNext());
                    listNode.getNext().setPrev(listNode.getPrev());
                }
                size--;
            }
            listNode = listNode.getNext();
        }
        return deletedDishCount;
    }

    public int removeAll(MenuItem menuItem) {

        int deletedDishCount = 0;
        ListNode listNode = head;
        while (listNode != null) {
            if (listNode.getValue().equals(menuItem)) {
                if (listNode == head) {
                    listNode.getNext().setPrev(null);
                    head = listNode.getNext();
                } else if (listNode == tail) {
                    listNode.getPrev().setNext(null);
                    tail = listNode.getPrev();
                } else {
                    listNode.getPrev().setNext(listNode.getNext());
                    listNode.getNext().setPrev(listNode.getPrev());
                }
                size--;
            }
            listNode = listNode.getNext();
        }
        return deletedDishCount;
    }


    public int costTotal() {
        int allCost;
        allCost = 0;
        //todo  foreach (MenuItem item : this) COMPLETE
        for (MenuItem item : this) {
            allCost += item.getCost();
        }
        return allCost;
    }
    //todo  foreach (MenuItem item : this) COMPLETE
    public int itemQuantity(String NameInArray) {
        int allNameInArray = 0;
        for (MenuItem item : this) {
            if (item.getName().equals(NameInArray)) allNameInArray++;
        }
        return allNameInArray;
    }
    //todo  foreach (MenuItem item : this) COMPLETE
    public int itemQuantity(MenuItem menuItem) {
        int allDishInArray = 0;
        for (MenuItem item : this) {
            if (item.equals(menuItem)) allDishInArray++;
        }
        return allDishInArray;
    }

    public String[] itemsName() {
        String[] strings;
        if (size != 0) {
            int index = 0;
            strings = new String[size];
            ListNode listNode = head;
            boolean addName = false;
            listNode = head;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (strings[j] == null) break;
                    if (listNode.getValue().getName().equals(strings[j])) {
                        addName = true;
                        break;
                    }
                }
                if (!addName) {
                    strings[index] = listNode.getValue().getName();
                    index++;
                }
                addName = false;
                listNode = listNode.getNext();
            }
            strings = cutString(strings);
        }
        else
            strings = new String[0];
        return strings;
    }

    public String[] cutString(String[] strings)
    {
        int index = 0;
        String[] cutString = new String[0];
        for (int i = 0; i < strings.length; i++) {
            if(strings[i] == null) break;
            else index++;
        }
        if (index != 0) {cutString = new String[index];
            System.arraycopy(strings, 0, cutString, 0, index);
        }
        return cutString;
    }

    public MenuItem[] sortedItemsByCostDesc() {
        MenuItem[] menuItems = (MenuItem[]) toArray();
        TableOrder.quickSortDishes(menuItems, 0, size - 1);
        return menuItems;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append(":").append(size).append("\n").append(getCustomer().toString()).append("\n");
        //todo  foreach (MenuItem item : this) COMPLETE
        for (MenuItem item : this) {
            stringBuilder.append(item.toString()).append("\n");
        }
        return stringBuilder.toString();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || !(obj instanceof InternetOrder))
            return false;

        InternetOrder internetOrder = (InternetOrder) obj;

        //todo какого фига в строчке далее идет каст к TableOrder? COMPLETE
        return (customer.equals((internetOrder.getCustomer())) & internetOrder.size == internetOrder.size() & localDateTime == internetOrder.getLocalDateTime());

    }

    @Override
    public int hashCode() {
        return customer.hashCode()
                ^ size
                ^ localDateTime.hashCode();
//todo в hashCode() обычно участвуют те же поля, что и в equals() COMPLETE
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
        for (MenuItem menuItem: this) {
            if (menuItem.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return new Iterator<MenuItem>() {
            ListNode node = head;
            int pos = 0;

            public boolean hasNext() {
                return size > pos;
            }

            public MenuItem next() {
                MenuItem menuItem = node.getValue();
                if (pos != size) {
                    node = node.getNext();
                    pos++;
                }
                return menuItem;
            }
        };
    }

    @Override
    public Object[] toArray() {
        ListNode listNode;
        MenuItem[] menuItems = new MenuItem[size];
        listNode = tail;
        for (int i = 0; i < size; i++) {
            menuItems[i] = listNode.getValue();
            listNode = listNode.getNext();
        }
        return menuItems;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(toArray(), size(), a.getClass());
    }

    @Override
    public boolean add(MenuItem menuItem) {

        ListNode listNode = new ListNode();
        listNode.setValue(menuItem);
        if (head == null)
            head = listNode;
        else {
            tail.setNext(listNode);
            listNode.setPrev(tail);
        }
        tail = listNode;
        size++;
        return true;
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
        for (MenuItem menuItem: c) {
            addAll &= add(menuItem);
        }
        return addAll;
    }

    private ListNode getNode(int index){
        ListNode listNode;
        if(index <= size / 2) {
            listNode = head;
            for (int i = 0; i < index; i++) {
                listNode = listNode.getNext();
            }
        }
        else {
            listNode = tail;
            for (int i = size - 1; i > index; i--) {
                listNode = listNode.getPrev();
            }
        }
        return listNode;
    }

    @Override
    public boolean remove(Object o) {
        ListIterator<MenuItem> listIterator = listIterator();
        while(listIterator.hasNext()) {
            if (listIterator.next().equals(o)) {
                remove(listIterator.previousIndex());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        if(index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();
        if(c.size() == 0)
            return false;
        ListNode listNode = getNode(index);

        for (MenuItem o : c) {
            if(!contains(o)){
                ListNode newListNode = new ListNode(o);
                if(index > 0) {
                    newListNode.setPrev(listNode.getPrev());
                    listNode.getPrev().setNext(newListNode);
                }
                else
                    head = newListNode;

                newListNode.setNext(listNode);
                listNode.setPrev(newListNode);
                size++;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        ListNode listNode = head;
        boolean changed = false;
        while(listNode != null) {
            ListNode nextNode = listNode.getNext();

            if(c.contains(listNode.getValue())){
                remove(listNode);
                changed = true;
            }
            listNode = nextNode;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        ListNode listNode = head;
        boolean changed = false;
        while(listNode != null) {
            ListNode nextNode = listNode.getNext();

            if(!c.contains(listNode.getValue())){
                remove(listNode);
                changed = true;
            }
            listNode = nextNode;
        }
        return changed;
    }

    public void clear() {
        ListNode node = head;
        while (node != tail){//todo неверно, он у тебя вызывает версию remove(Object o), а ты туда нод пихаешь. Ручками ссылки надо обнулять COMPLETE
            ListNode nextNode = node.getNext();
            node.setPrev(null);
            node.setNext(null);
            node = nextNode;
        }
        head = null;
        tail = null;
    }

    @Override
    public MenuItem get(int index) {
        if(index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();

        return getNode(index).getValue();
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        if(index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();

        MenuItem menuItem = getNode(index).getValue();
        getNode(index).setValue(element);

        return menuItem;
    }

    //todo а где здесь выброс UnlawfulActionException? COMPLETE
    @Override
    public void add(int index, MenuItem element) {
        if(index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();

        if (element instanceof Drink) {
            Drink drink = (Drink) element;
            if (drink.getAlcoholVol() > 0 && customer.getAge() < 18) throw new UnlawfulActionException("Тебе нет 18, пиздюк");
        }

        ListNode listNode = getNode(index);
        ListNode newListNode = new ListNode(element);

        if(listNode != head)
            newListNode.setPrev(listNode.getPrev());
        newListNode.setNext(listNode);
        listNode.setPrev(newListNode);
        size++;
    }

    @Override
    public MenuItem remove(int index) {
        if(index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();

        ListNode listNode = getNode(index);
        MenuItem menuItem = listNode.getValue();

        remove(listNode);

        size--;

        return menuItem;
    }

    @Override
    public int indexOf(Object o) {
        ListNode listNode = head;
        for(int i = 0; i < size; i++){
            if(listNode.getValue().equals(o))
                return i;
            listNode = listNode.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        ListNode listNode = tail;
        for(int i = size - 1; i > -1; i--){
            if(listNode.getValue().equals(o))
                return i;
            listNode = listNode.getPrev();
        }
        return -1;
    }

    @Override
    public ListIterator<MenuItem> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<MenuItem> listIterator(int index) {
        if(index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();

        InternetOrder internetOrder = this;
        return new ListIterator<MenuItem>() {
            ListNode listNode = getNode(index);
            int pos = index;
            ListNode newElement = null;
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
                return size > pos;
            }

            public MenuItem next() {
                switch (lastOperation) {
                    case ADD:
                        lastOperation = ListIteratorOperation.NEXT;
                        return listNode.getValue();
                    default:
                        lastOperation = ListIteratorOperation.NEXT;
                        MenuItem menuItem = listNode.getValue();
                        if (pos >= size)
                            throw new NoSuchElementException();
                        listNode = listNode.getNext();
                        pos++;

                        return menuItem;
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
                        listNode = newElement;
                        return listNode.getValue();
                    default:
                        lastOperation = ListIteratorOperation.PREVIOUS;
                        MenuItem menuItem = listNode.getValue();
                        if (pos < 0)
                            throw new NoSuchElementException();

                        listNode = listNode.getPrev();
                        pos--;

                        return menuItem;
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
                        pos--;
                        internetOrder.remove(listNode.getPrev());
                        break;
                    case PREVIOUS:
                        internetOrder.remove(listNode.getNext());
                        break;
                    default:
                        illegalState();
                }
                lastOperation = ListIteratorOperation.REMOVE;
            }

            public void set(MenuItem menuItem) {
                switch (lastOperation) {
                    case NEXT:
                        listNode.getPrev().setValue(menuItem);
                        break;
                    case PREVIOUS:
                        listNode.getNext().setValue(menuItem);
                        break;
                    default:
                        illegalState();
                }
                lastOperation = ListIteratorOperation.SET;
            }

            public void add(MenuItem menuItem) {
                if(size == 0) {
                    internetOrder.add(menuItem);
                }
                else{
                    switch (lastOperation){
                        case NONE:
                            internetOrder.add(0, menuItem);
                            pos++;
                            break;
                        case NEXT:
                            newElementPos = pos - 1;
                            if(pos - 1 < 0)
                                internetOrder.add(0, menuItem);
                            else {
                                internetOrder.add(newElementPos, menuItem);
                                newElement = listNode.getPrev();
                            }
                            pos++;
                            break;
                        case PREVIOUS:
                            newElementPos = pos + 2;
                            if(pos + 2 > size - 1)
                                internetOrder.add(menuItem);
                            else{
                                internetOrder.add(newElementPos, menuItem);
                                newElement = listNode.getNext().getNext();
                            }
                            break;
                    }
                }
                lastOperation = ListIteratorOperation.ADD;
            }
        };
    }

    @Override
    public List<MenuItem> subList(int fromIndex, int toIndex){
        if(fromIndex < 0 || toIndex > size - 1 || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        if(fromIndex == toIndex)
            return new InternetOrder();

        InternetOrder subList = new InternetOrder();

        ListNode listNode = getNode(fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(listNode.getValue());
            listNode = listNode.getNext();
        }
        subList.size = toIndex - fromIndex;

        return subList;
    }


}
