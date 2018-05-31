package io;

import barBossHouse.*;

import java.io.*;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Scanner;

public class OrderManagerBinaryFileSource extends OrderManagerFileSource {
    private static String EXTENSION = ".txt";

    public OrderManagerBinaryFileSource (String path){
        setPath(path);
    }

    public String getPath() {
        return super.getPath();
    }

    public void setPath(String path) {
        super.setPath(path);
    }
    public void load(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName() + EXTENSION);
        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(Files.newInputStream(path)));
        Customer customer = loadCustomer(inputStream);
        LocalDateTime orderTime = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0,  ZoneOffset.UTC);
        MenuItem[] items = loadMenuItems(inputStream);
        inputStream.close();

        order.clear();
        order.setCustomer(customer);
        order.setLocalDateTime(orderTime);
        order.addAll(Arrays.asList(items));
    }

    private MenuItem[] loadMenuItems (DataInputStream inputStream) throws IOException {
        int size = inputStream.readInt();
        MenuItem[] items = new MenuItem[size];
        String name, description, type;
        int cost;
        double alcoholVol;
        for (int i = 0; i < items.length; i++) {
            name = inputStream.readUTF();
            description = inputStream.readUTF();
            cost = inputStream.readInt();
            if (inputStream.readUTF().equals("Drink")) {
                alcoholVol = inputStream.readDouble();
                type = inputStream.readUTF();
                items[i] = new Drink(name, description,cost, DrinkTypeEnum.valueOf(type), alcoholVol);
            } else
                items[i] = new Dish(name, description, cost);
        }
        return items;
    }

    private Customer loadCustomer (DataInputStream inputStream) throws IOException {
        //load customer name and birthDate
        String firstName = inputStream.readUTF();
        String lastName = inputStream.readUTF();
        //load birthDate
        LocalDate birthDate = LocalDate.ofEpochDay(inputStream.readLong());
        Address address = loadAddress(inputStream);

        return new Customer(firstName, lastName, birthDate, address);
    }

    private Address loadAddress (DataInputStream inputStream) throws IOException {
        String cityName = inputStream.readUTF();
        int zipCode = inputStream.readInt();
        String streetName = inputStream.readUTF();
        int buildingNumber = inputStream.readInt();
        char buildingLetter = inputStream.readChar();
        int apartmentNumber = inputStream.readInt();

        return new Address(cityName, zipCode, streetName, buildingNumber, buildingLetter, apartmentNumber);
    }

    @Override
    public void store(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName() + EXTENSION);
        DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(path)));
        writeCustomer(outputStream, order);
        outputStream.writeLong(order.getLocalDateTime().toEpochSecond(ZoneOffset.UTC));
        writeMenuItems(outputStream, order);
        outputStream.close();
    }

    private void writeMenuItems(DataOutputStream outputStream, Order order) throws IOException {
        outputStream.writeInt(order.size());
        for (MenuItem item : order) {
            outputStream.writeUTF(item.getName());
            outputStream.writeUTF(item.getDescription());
            outputStream.writeInt(item.getCost());
            if (item instanceof Drink) {
                outputStream.writeUTF("Drink");
                outputStream.writeDouble(((Drink) item).getAlcoholVol());
                outputStream.writeUTF(((Drink) item).getType().toString());
            } else
                outputStream.writeUTF("Dish");

        }
    }

    private void writeCustomer(DataOutputStream outputStream, Order order) throws IOException {
        //store customer
        outputStream.writeUTF(order.getCustomer().getFirstName());
        outputStream.writeUTF(order.getCustomer().getSecondName());
        //customer birthDate
        outputStream.writeLong(order.getCustomer().getBirthDate().toEpochDay());
        writeAddress(outputStream, order);

    }

    private void writeAddress(DataOutputStream outputStream, Order order) throws IOException {
        outputStream.writeUTF(order.getCustomer().getAddress().getCityName());
        outputStream.writeInt(order.getCustomer().getAddress().getZipCode());
        outputStream.writeUTF(order.getCustomer().getAddress().getStreetName());
        outputStream.writeInt(order.getCustomer().getAddress().getBuildingNumber());
        outputStream.writeChar(order.getCustomer().getAddress().getBuildingLetter());
        outputStream.writeInt(order.getCustomer().getAddress().getApartamentNumber());
    }

    public void delete(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName() + EXTENSION);
        Files.delete(path);
    }

    public void create(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName() + EXTENSION);
        Files.createFile(path);
        store(order);
    }

    @Override
    public void deleteAll(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName() + EXTENSION);
        while (Files.exists(path)) {
            Files.delete(path);
        }
    }
}
