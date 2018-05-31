package io;

import barBossHouse.Order;

public interface FileSource extends Source<Order> {
    void setPath(String path);  // – изменяющий путь к файлу, в который записывается состояние объекта
    String getPath();           // – возвращающий путь к файлу, в который записывается состояние объекта
}
