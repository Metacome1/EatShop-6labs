package io;

import barBossHouse.MenuItem;
import barBossHouse.Order;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class OrderManagerSerializedFileSource extends OrderManagerFileSource {
    private static final String EXTENSION = ".ser";

    public OrderManagerSerializedFileSource (String path){
        setPath(path);
    }

    public String getPath() {
        return super.getPath();
    }

    public void setPath(String path) {
        super.setPath(path);
    }

    public void load(Order order) throws IOException {
            /* todo не верно сделал. Здесь нужно восстановить состояние объекта, переданного в качестве параметра COMPLETE
             * то есть банально сделать
             * Order newOrder = (Order) in.readObject();
             * order.clear();
             * order.addAll(newOrder);
             * ну и еще кастомера так же установить
             */
            Path path = Paths.get(getPath() + order.getCustomer().getSecondName() + EXTENSION);
            Order loadOrder;
            try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))) {
                loadOrder = (Order) inputStream.readObject();
                inputStream.close();
                order.clear();
                order.addAll(Arrays.asList((MenuItem[]) loadOrder.toArray()));
                order.setLocalDateTime(loadOrder.getLocalDateTime());
                order.setCustomer(loadOrder.getCustomer());
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
    }

    public void store(Order order) throws IOException {
        Path path = Paths.get(getPath() + order.getCustomer().getSecondName()+ EXTENSION);
        ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path));
        outputStream.writeObject(order);
        outputStream.close();
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
        while (Files.exists(path))
            Files.delete(path);
    }
}
