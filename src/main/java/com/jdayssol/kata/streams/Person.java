package com.jdayssol.kata.streams;

public class Person {

    private String name;
    private int age;
    private String nationality;

    public Person(final String nameValue, final int ageValue, final String nationalityValue) {
        name = nameValue;
        age = ageValue;
        nationality = nationalityValue;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

}