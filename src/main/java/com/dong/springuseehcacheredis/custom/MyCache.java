package com.dong.springuseehcacheredis.custom;

import org.springframework.cache.Cache;

import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @Author: dong
 * @Date: 2021/12/11
 */
public class MyCache implements Cache {
    private MyLinkedHashMap<String> myMap = new MyLinkedHashMap<>(2, 1, true);
    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getNativeCache() {
        return myMap;
    }

    @Override
    public ValueWrapper get(Object key) {
        String value = myMap.get(key);
        return value == null ? null : () -> value;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        Object value = myMap.get(key);
        if (value != null && type != null && !type.isInstance(value)) {
            throw new IllegalStateException(
                    "Cached value is not of required type [" + type.getName() + "]: " + value);
        }
        return (T) value;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return Optional.ofNullable((T) myMap.get(key)).orElseGet(() -> {
            try {
                return valueLoader.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void put(Object key, Object value) {
        myMap.put((String) key, (String) value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return () -> Optional.ofNullable((Object) myMap.get(key)).orElse(value);
    }

    @Override
    public void evict(Object key) {
    }

    @Override
    public boolean evictIfPresent(Object key) {
        return false;
    }

    @Override
    public void clear() {
        myMap.clear();
    }

    @Override
    public boolean invalidate() {
        return false;
    }

}
