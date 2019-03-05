package com.geektech.util.shared;

public interface SharedStorage {

    <T> T get(String key, T defValue);

    <T> void set(String key, T value);

    boolean contains(String key);

    void delete(String key);

}
