package com.igor.ns.persistence;

import java.util.List;

public interface Persistence<T> {
    void persist(final T persistenceObject);

    T merge(final T persistenceObject);

    void remove(final long id);

    T find(final long id);

    List<T> findAll();
}
