package barBossHouse;

import java.time.LocalDateTime;
import java.util.List;

//todo удали дубли методов отсюда и из реализации (методы List первичные). Подсказки смотри ниже в виде комментов COMPLETE
public interface Order extends List<MenuItem> {

    String[] itemsName();

    int itemQuantity(String str);

    int itemQuantity(MenuItem menuItem);

    boolean remove(String str);

    int removeAll(String str);

    int removeAll(MenuItem menuItem);

    MenuItem[] sortedItemsByCostDesc();

    int costTotal();

    Customer getCustomer();

    void setCustomer(Customer customer);

    String toString();

    boolean equals(Object obj);

    int hashCode();

    LocalDateTime getLocalDateTime();

    void setLocalDateTime(LocalDateTime localDateTime);
}
