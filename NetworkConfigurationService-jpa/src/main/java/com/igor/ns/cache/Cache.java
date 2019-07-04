package com.igor.ns.cache;

import com.igor.ns.event.CachePurgeEvent;

import javax.enterprise.event.Observes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Cache<T> {

    private final Map<Long, T> CACHE = new HashMap<>();

    protected void add(final long id, final T persistenceObject) {
        CACHE.put(id, persistenceObject);
    }

    protected void remove(final long id) {
        CACHE.remove(id);
    }

    protected boolean contains(final long id) {
        return CACHE.containsKey(id);
    }

    protected T get(final long id) {
        return CACHE.get(id);
    }

    protected List<T> getAll() {
        return new ArrayList<>(CACHE.values());
    }

    protected void clear() {
        CACHE.clear();
    }

    protected int size() {
        return CACHE.size();
    }

    protected void listenForPurgeEvent(@Observes final CachePurgeEvent event) {
        updateCache();
    }

    protected abstract void updateCache();
}
