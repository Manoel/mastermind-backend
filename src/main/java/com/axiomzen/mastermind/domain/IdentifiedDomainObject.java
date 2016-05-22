package com.axiomzen.mastermind.domain;

public abstract class IdentifiedDomainObject<K extends KeyIdentity<?>> extends DomainObject {

    private K key;

    private PersistenceStatus state = PersistenceStatus.UNBOUND;

    public IdentifiedDomainObject() {
    }

    public IdentifiedDomainObject(K key) {
        setKey(key);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K id) {

        if (id == null) {
            throw new IllegalArgumentException("id can not be null.");
        }

        key = id;
        state = PersistenceStatus.BOUND;

        onBound();
    }

    protected void onBound() {
    }

    protected boolean isBound() {
        return this.state.equals(PersistenceStatus.BOUND);
    }
}
