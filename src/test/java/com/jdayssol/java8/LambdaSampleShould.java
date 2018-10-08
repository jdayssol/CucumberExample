package com.jdayssol.java8;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.*;

public class LambdaSampleShould {

	
	/*
	 * https://jeanchristophegay.com/java8-lambda-stream/
	 * Ça fait beaucoup d’interfaces fonctionnelles (et je n’ai même pas lister les versions spécifiques pour les types primitifs), ce qu’il faut retenir :	
	 * 
		s’il n’y a pas de retour, on utilise un Consumer,
		
		s’il faut retourner un booléen, on utilise un Predicate,
		
		s’il faut produire un numérique primitif (int, double, long), on utilise un  (Type)ToIntFunction, (Type)ToDoubleFunction, (Type)ToLongFunction,
		
		s’il faut retourner une valeur sans prendre d’argument, c’est un Supplier,
		
		si le seul argument de la fonction est un int, double, long on utilise un  Int(something), Double(something), Long(Something),
		
		si la fonction prend deux arguments, c’est une Bi(something),
		
		si la fonction prend deux arguments de même type, c’est un BinaryOperator,
		
		si une fonction retourne une valeur de même type que son unique argument, c’est un UnaryOperator,
		
		si une fonction prend en argument un type primitif et un autre type sans retourner de valeur, c’est un Obj(Int|Double|Long)Consumer,
		
		sinon c’est une Function.
	 */
	
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
	
	@Test
	public void BiFunction(){
		//C’est un spécialisation d’une Function qui prend deux arguments et retourne un résultat.
		BiFunction<Integer, String, String> concat = (Integer i, String s) -> s + ": " + i;

		assert "un: 1".equals(concat.apply(1, "un"));
	}
	
	@Test
	public void UnaryOperator(){
		// C’est une Function qui prend un argument et retourne un résultat du même type. 
		// Par exemple la fonction identity retourne toujours la valeur passée en argument.
		assert UnaryOperator.identity().apply("un").equals("un");
	}
	
	@Test
	public void BinaryOperator(){
		//Un BinaryOperator est une spécialisation d’une BiFunction dont les paramètres et le résultat partagent le même type.
		BinaryOperator<String> concatString = (s1, s2) -> s1.concat(": ").concat(s2);

		assert "un: 1".equals(concatString.apply("un", "1"));
	}
	
	@Test
	public void Predicate(){
		//Un Predicate prend un argument et retourne un booléen.
		Predicate<String> isEmpty = s -> s == null || s.isEmpty();
		Predicate<String> isTrimmed = s -> s.equals(s.trim());

		assert isEmpty.test(null) == true;
		assert isEmpty.test("") == true;
		assert isEmpty.test("not empty") == false;

		assert isEmpty.negate().and(isTrimmed).test("not empty") == true;
		assert isEmpty.negate().and(isTrimmed).test(" not empty ") == false;

		assert isEmpty.or(isTrimmed).test("") == true;
		assert isEmpty.or(isTrimmed).test("not empty") == true;

		assert Predicate.isEqual("hello").test("hello") == true;
		
		// negate inverse un prédicat,
		// and et or permettent de chaîner des prédicats selon l’opérateur logique,
		// La méthode statique isEqual teste l’égalité de deux objets selon Object#equals
	}
	
	@Test
	public void Supplier(){
		Supplier<String> emptyString = () -> "supplier";

		assert "supplier".equals(emptyString.get());
	}
	
	@Test
	public void Consumer(){
		//Un Consumer prend un argument mais ne retourne pas de résultat.
		Consumer<String> print = s -> System.out.println(s);
		Consumer<String> hello = s -> System.out.printf("Hello %s !", s);

		print.accept("something"); // something
		print.andThen(hello).accept("JC"); // JC Hello JC !
		
		//La méthode andThen permet de chaîner les consommateurs.
	}
	
	@Test
	public void Comparator(){
		java.util.Comparator<Integer> ascending = (a, b) -> a.compareTo(b);

		assert ascending.compare(10, 1) > 0;
		assert ascending.reversed().compare(10, 1) < 0;
	}
	
	@Test
	public void usingPointPoint(){
		/*
		 * ava 8 introduit le mot clef :: pour extraire des références de méthodes. 
		 * C’est utile pour remplacer des expressions lambda qui se contentent de faire appel à une méthode qui existe déjà.
		 */
		String hello = new String("hello");
		Predicate<String> startsWith = hello::startsWith;
		
		Supplier<String> newString = String::new;
		
		List<String> names = Arrays.asList("Barbara", "James", "Mary", "John");
		Collections.sort(names, String::compareToIgnoreCase);
		
		assert startsWith.test("he") == true;
	}
}
