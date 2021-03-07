package com.overriding;

public class Pig extends Animal{
	@Override
	public void sound() {
		System.out.println("Inside pig");
		super.sound();
	}
}
