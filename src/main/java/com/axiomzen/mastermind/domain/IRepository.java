package com.axiomzen.mastermind.domain;

public interface IRepository<T extends Entity<?>, K extends KeyIdentity<?>> {

    T load(K id);

}
