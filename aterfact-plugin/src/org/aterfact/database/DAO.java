package org.aterfact.database;

import java.util.Map;

public interface DAO<T> {
    Map<Integer, T> getData();

    boolean create(T obj);
    boolean delete(T obj);
    boolean update(T obj);
  
    T load(int id);
    T load(String id);
}