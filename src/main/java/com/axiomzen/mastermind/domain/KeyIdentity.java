package com.axiomzen.mastermind.domain;

import java.util.Objects;

public abstract class KeyIdentity<T> {

    private T value;

    public KeyIdentity(T value) {
        if (value == null) {
            throw new IllegalArgumentException("id can not be null.");
        }
        this.value = value;
    }

    public T value() {
        return value;
    }


    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof KeyIdentity && Objects.equals(value, ((KeyIdentity<?>) obj).value());

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.value);
        return hash;
    }
}
