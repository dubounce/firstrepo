package com.example.mlbandroid.interfaces;

public class Main {

	
	
	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		PoolObjectFactory<Sound> objFactory = new PoolObjectFactoryImpl<Sound>(Sound.class);
		Sound sound = objFactory.createObject();
		sound.play(4f);
	}

}
