package com.example.mlbandroid.interfaces;

public class PoolObjectFactoryImpl<T> implements PoolObjectFactory {

	private Class myClass;

	public PoolObjectFactoryImpl(Class myClass) {
		this.myClass = myClass;
	}
	
	@Override
	public T createObject() throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return (T) myClass.newInstance();
	}	

}
