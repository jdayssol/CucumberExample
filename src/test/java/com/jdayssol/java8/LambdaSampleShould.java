package com.jdayssol.java8;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;



public class LambdaSampleShould {

	@Test
	public void collectionCompare()
	{
		// Une expression lambda est une sorte de méthode “anonyme”
		List<Integer> numbers = asList(10, 1, 1000, 100);
		
		Collections.sort(numbers, (a, b) -> a.compareTo(b));
		
		Assert.assertEquals(asList(1, 10, 100, 1000), numbers);
	}
	
	@Test
	public void lambdaComposeAndThen() {
		

		//Une Function prend un argument et retourne un résultat.
		Function<Integer, String> toString = n -> String.valueOf(n);
		Function<String, Integer> toInteger = s -> Integer.valueOf(s);

		assert "4".equals(toString.apply(4));
		assert toInteger.apply("4") == 4;

		// La méthode compose applique la fonction toInteger puis la fonction toString 
		assert "4".equals(toString.compose(toInteger).apply("4"));
		// La méthode andThen applique toString puis toInteger.
		assert toString.andThen(toInteger).apply(4) == 4;
	}
}
