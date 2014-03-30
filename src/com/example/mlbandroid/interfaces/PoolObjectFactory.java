package com.example.mlbandroid.interfaces;

public interface PoolObjectFactory<T> {

	public T createObject() throws InstantiationException, IllegalAccessException;
}
