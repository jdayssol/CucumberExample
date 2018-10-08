package com.jdayssol.java8;

public class Person {
	
	private String firstName;	
	private String lastName;
	private int age;
	
	public Person(String firstname, String lastname, int age) {
		super();
		this.setFirstName(firstname);
		this.setLastName(lastname);
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString()
	{
		return this.firstName + " - " + this.lastName + " - " + age;
	}
}
