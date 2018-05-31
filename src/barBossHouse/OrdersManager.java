package barBossHouse;

import java.time.LocalDate;
import java.util.Collection;
//todo удали дубли методов отсюда и из реализации (методы Collection первичные). Подсказки смотри ниже в виде комментов COMPLETE
public interface OrdersManager extends Collection<Order> {

    int itemQuantity (String str);

    int itemQuantity (MenuItem menuItem);

    int ordersCostSummary();

    int countMenuItemsNowDay(LocalDate localDate);

    OrdersManager getMenuItemNowDay(LocalDate localDate);

    OrdersManager getMenuItemsCustomer(Customer customer);
}
