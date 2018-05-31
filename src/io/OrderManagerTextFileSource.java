package io;

import barBossHouse.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Scanner;

//todo аналогично BinaryFileSOurce COMPLETE
public class OrderManagerTextFileSource extends OrderManagerFileSource {
    private static String EXTENSION = ".txt";

    public OrderManagerTextFileSource(String path) {
        setPath(path);
    }

    public String getPath() {
        return super.getPath();
    }
    public void setPath(String path) {
        super.setPath(path);
    }


    public void load(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName()+ EXTENSION);
        Scanner scanner = new Scanner(path);

        LocalDateTime loadOrderTime = LocalDateTime.ofEpochSecond(scanner.nextLong(), 0, ZoneOffset.UTC);
        Customer customer = loadCustomer(scanner);

        MenuItem[] loadItems = loadMenuItems(scanner);
        scanner.close();

        order.clear();
        order.setLocalDateTime(loadOrderTime);
        order.setCustomer(customer);
        order.addAll(Arrays.asList(loadItems));
    }

    private MenuItem[] loadMenuItems (Scanner scanner) {
        int size = scanner.nextInt();
        MenuItem[] items= new MenuItem[size];

        for (int i = 0 ; i < size; i++) {
            String type = scanner.next();
            String name = scanner.next();
            String description = scanner.next();
            int cost = scanner.nextInt();
            if (type.equals("Drink")){
                String drinkType = scanner.next();
                double alcoholVol = scanner.nextDouble();
                items[i] = new Drink(name, description,cost, DrinkTypeEnum.valueOf(drinkType), alcoholVol);
            }else
                items[i] = new Dish(name, description, cost);
        }
        return items;
    }

    private Customer loadCustomer(Scanner scanner) {
        String firstName = scanner.next();
        String lastName = scanner.next();
        LocalDate birthDate = LocalDate.ofEpochDay(scanner.nextLong());
        Address address = loadAddress(scanner);
        return new Customer(firstName, lastName, birthDate, address);
    }

    private Address loadAddress(Scanner scanner) {
        String city = scanner.next();
        int zipCode = scanner.nextInt();
        String streetName = scanner.next();
        int buildingNumber = scanner.nextInt();
        char buildingLetter = scanner.next().toCharArray()[0];//ew
        int apartmentNumber = scanner.nextInt();
        return new Address (city, zipCode, streetName,buildingNumber, buildingLetter, apartmentNumber);
    }

    @Override
    public void store(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName() + EXTENSION);
        PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path));
    }

    public void delete(Order order) {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName() + EXTENSION);
        try {
            Files.delete(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void create(Order order) {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName() + EXTENSION);
        try {
            Files.createFile(path);
            store(order);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAll(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName() + EXTENSION);
        while(Files.exists(path)){
            Files.delete(path);
        }
    }
}
