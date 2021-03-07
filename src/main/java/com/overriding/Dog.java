package com.overriding;

public class Dog extends Animal{
	@Override
	public void sound() {
		System.out.println("Inside Dog");
		super.sound();
	}
}
