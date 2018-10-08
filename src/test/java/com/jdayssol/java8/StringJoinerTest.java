package com.jdayssol.java8;

import java.util.Arrays;
import java.util.StringJoiner;

import org.junit.Test;

/**
 * On peut enfin concaténer des String séparées par un délimiteur sans utiliser de librairies.
 */
public class StringJoinerTest {

	@Test
	public void stringJoiner(){
		String join = new StringJoiner(",")
		        .add("a")
		        .add("b")
		        .add("c")
		        .toString();
		assert join.equals("a,b,c");

		String join2 = String.join(",", Arrays.asList("a", "b", "c"));
		assert join2.equals("a,b,c");
	}

}
