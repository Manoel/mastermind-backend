package com.axiomzen.mastermind.domain;


import java.util.Objects;

public abstract class Entity<K extends KeyIdentity<?>> extends IdentifiedDomainObject<K> {

    public Entity() {
    }

    public Entity(K key) {
        super(key);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Entity<?> other = (Entity<?>) obj;

        if (isBound() && other.isBound()) {
            return Objects.equals(this.getKey(), other.getKey());
        } else {
            return this == other;
        }
    }

    @Override
    public int hashCode() {
        if (isBound()) {
            return 23 * 7 + this.getKey().hashCode();
        } else {
            return System.identityHashCode(this);
        }
    }

}
