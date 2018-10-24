package com.jdayssol.java8;

public class StringSwith {

public static void main(String[] args) {
	String a = null;
    switch (a) {
        case "Monday":
        case "Tuesday":
        case "Wednesday":
            System.out.println("boring");
            break;
        case "Thursday":
            System.out.println("getting better");
        case "Friday":
        case "Saturday":
        case "Sunday":
            System.out.println("much better");
            break;
        default :
        	System.out.println("Not a day");
        	break;
    }

}
}
