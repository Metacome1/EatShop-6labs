package io;

import java.io.IOException;

public interface Source<T> {
        void load(T t) throws IOException;     // - восстановление состояния объекта из некоторого источника
        void store(T t) throws IOException;    // – запись состояния объекта в некоторый источник
        void delete(T t) throws IOException;   // – удаляющий информацию о состоянии объекта из некоторого источника
        void create(T t) throws IOException;   // - создающий новый источник, хранящий состояние объекта
        void deleteAll(T t) throws  IOException; // - удаляющий информацию о состоянии всех объектов
    }

