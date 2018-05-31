import barBossHouse.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Address add1 = new Address();
        Address add2 = new Address("Masara", 1, "Bulochkina", 6, 'A', 7);
        Address add3 = new Address("Keke", 10, 'h', 200);
        System.out.println(add1.toString());
        System.out.println(add2.toString());
        System.out.println(add3.toString());
        System.out.println(add2.getBuildingLetter());
        System.out.println(add2.getApartamentNumber());
        System.out.println(add2.getCityName());
        System.out.println(add2.getStreetName());
        System.out.println(add2.getZipCode());
        System.out.println(add2.getBuildingNumber());
        System.out.println(add2.equals(add1));
        System.out.println(add2.hashCode());

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer("Igor", "Darmon", LocalDate.of(1998, 7,26), add2);
        System.out.println(customer1.toString());
        System.out.println(customer2.toString());
        System.out.println(customer3.toString());
        System.out.println(customer3.getAddress());
        System.out.println(customer3.getFirstName());
        System.out.println(customer3.getAge());
        System.out.println(customer3.equals(customer2));
        System.out.println(customer3.getSecondName());
        System.out.println(customer3.hashCode());

        Dish menuItem1 = new Dish("Kyrochka", "Vkysno i piZdato", 10);
        Dish menuItem2 = new Dish("Pepe", "Vkysno", 90);
        Dish menuItem3 = new Dish("Kyrka", "RRR", 80);
        Dish menuItem4 = new Dish("Free cheez", "NeVkysno i nepiZdato");
        System.out.println(menuItem1.toString());
        System.out.println(menuItem2.toString());
        Drink drink2 = new Drink("Free cheez", "NeVkysno i nepiZdato");


        Order order1 = new TableOrder();
        order1.add(menuItem1);
        order1.add(menuItem2);
        order1.add(menuItem3);
        order1.add(menuItem4);
        System.out.println(order1.toString());

        System.out.println();
        order1.remove(menuItem3);

        System.out.println(order1.toString());

        InternetOrder internetOrder = new InternetOrder();
        internetOrder.add(menuItem2);
        internetOrder.add(menuItem2);
        internetOrder.add(menuItem3);
        internetOrder.add(menuItem4);
        internetOrder.add(menuItem2);
        System.out.println();
        System.out.println(internetOrder.toString());

        internetOrder.remove(menuItem2);
        System.out.println(internetOrder.toString());

        internetOrder.removeAll(menuItem2);
        System.out.println(internetOrder.toString());

        internetOrder.add(menuItem2);
        internetOrder.add(menuItem2);
        internetOrder.add(menuItem2);

        String[] newnew;
        newnew = internetOrder.itemsName();
        for (int i = 0; i < newnew.length; i++) {
            System.out.println(newnew[i]);
        }

        System.out.println(internetOrder.getLocalDateTime());
        System.out.println(customer3.getAge());
        System.out.println();
        System.out.println();

        System.out.println(Customer.NOT_MATURE_UNKNOWN_CUSTOMER.getAge());
        System.out.println(Customer.MATURE_UNKNOWN_CUSTOMER.getAge());

        System.out.println();



        Dish menuItem5 = new Dish("Japan cheez", "Proverka formatirovaniyz", 20000);
        Dish menuItem6 = new Dish("Slsa", "Mozhet i vkusno", 1000);
        Dish menuItem7 = new Dish("Home pureshka", "Noviy vkus", 80);

        System.out.println(menuItem5.toString());
        System.out.println(menuItem6.toString());
        System.out.println(menuItem7.toString());

        System.out.println();

        System.out.println(customer3.toString());

        System.out.println();

        String[] strings;
        strings = internetOrder.itemsName();

        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }
}
