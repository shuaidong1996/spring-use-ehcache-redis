package com.dong.springuseehcacheredis.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: dong
 * @Date: 2021/12/11
 */
public class MyLinkedHashMap<T> extends LinkedHashMap<String, T> {
    private int capacity;

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }

    public MyLinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        //可选参数accessOrder 设置为true 即可实现LRU逻辑
        super(initialCapacity, loadFactor, accessOrder);
        this.capacity = initialCapacity;
    }
}
