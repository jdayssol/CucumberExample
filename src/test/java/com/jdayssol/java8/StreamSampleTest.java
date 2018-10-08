package com.jdayssol.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

/**
 * https://jeanchristophegay.com/java8-lambda-stream/
 */
public class StreamSampleTest {

	List<Person> persons;
	
	@Before
	public void init()
	{
		persons = Arrays.asList(
	            new Person("John", "Doe", 30),
	            new Person("Jane", "Doe", 20),
	            new Person("Jim", "Smith", 15)		
			    );
	}
	

	@Test
	public void listPerson()
	{
		/*
		 * Un Stream est une séquence d’éléments sur laquelle on peut effectuer des opérations. Un Stream se compose d’une source (un tableau, une collection, etc), de zéro ou plusieurs opérations intermédiaires (transformation du Stream en un autre via filter par exemple) et d’une opération terminale (qui produit le résultat).
			Les calculs ne sont effectués qu’à l’initialisation de l’opération finale et la source est consommée que si c’est nécessaire.
			On peut créer un Stream à partir d’une collection en utilisant la méthode stream(),
		 */

	assert persons.stream().count() == 3;
	}
	
	@Test
	public void intStream()
	{
		//En utilisant IntStream, DoubleStream, LongStream pour créer des Stream numériques,
		assert IntStream.range(0, 10).sum() == 45;
	}
	
	@Test
	public void listStream()
	{
		/**
		 * Effectue une opération sur chacun des éléments en utilisant un Consumer. 
		 * C’est une opération terminale qui consomme le Stream. On ne peut pas appeler d’autres opérations après un forEach.
		 */
		Stream.of("a", "b", "c").forEach(System.out::println);

		Stream.builder().add("a").add("b").add("c")
		        .build()
		        .forEach(System.out::println);
	}
	
	@Test
	public void filterListStream()
	{
		// Accepte un Predicate pour filtrer les éléments. 
		// C’est une opération intermédiaire qui nous permet donc de chaîner d’autres opérations à sa suite.
		persons.stream()
        .filter(p -> p.getLastName().startsWith("D"))
        .forEach(System.out::println);
	}
	
	@Test
	public void sortedListStream()
	{
		// Une opération intermédiaire qui permet de trier les éléments à l’aide d’un  Comparable.
		persons.stream()
        .sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()))
        .forEach(System.out::println);
	}
	
	@Test
	public void mapStream(){
		// Applique une Function sur les éléments, c’est également une opération intermédiaire.
		persons.stream()
        .map(Person::getAge)
        .sorted()
        .forEach(System.out::println);
		
		// Plusieurs méthodes permettant de vérifier que zéro/un/des éléments vérifient un  Predicate. Toutes ces opérations sont terminales.
		assert persons.stream().allMatch(p -> p.getFirstName().startsWith("J"))== true;
		assert persons.stream().noneMatch(p -> p.getAge() == 35)== true;
		assert persons.stream().anyMatch(p -> "Doe".equals(p.getLastName()))== true;
		// Une opération terminale qui retourne le nombre d’élément dans un Stream.
		assert persons.stream().filter(p -> p.getAge() >= 20).count()== 2;
		// Retourne la somme de tous les éléments.
		assert IntStream.rangeClosed(1, 10).sum() == 55;
	}
	
	@Test
	public void reduceStream(){
		/*
		 * C’est une opération terminale qui réduit les éléments du Stream avec un  BinaryOperator.
		 * On obtient au final le résultat sous la forme d’un Optional,
		 * On va créer un monstre en fusionnant nos personnes,
		 */
		persons.stream()
        .reduce((p1, p2) -> new Person(p1.getFirstName(), p2.getLastName(), p1.getAge() + p2.getAge()))
        .ifPresent(System.out::println);
		
		// On peut additionner les âges de nos personnes,
		assert persons.stream()
        .map(Person::getAge)
        .reduce(0, Integer::sum)== 65;
		// L’argument idendity est l’élément identité de la réduction,
		// il ne doit avoir aucun effet sur la fonction d’accumulation (accumulator dans la signature de reduce). C’est le cas ici puisque x + 0 = x.
		
		assert persons.stream().reduce(0,
                (result, person) -> result + person.getAge(),
                (a, b) -> a + b)== 65;
	}
	
	@Test
	public void collectStream(){
		// Permet de réunir tous les éléments en utilisant un Collector. C’est bien entendu une opération terminale.
		// La classe Collectors regorge d’implémentations de Collector pour faciliter l’opération.
		persons.stream()
        .map(Person::getLastName)
        .collect(Collectors.toList());
	}
	
	@Test
	public void concatStream(){
		// On peut concaténer des Stream.
		IntStream.concat(
		        IntStream.range(0, 4),
		        IntStream.range(4, 6)
		).forEach(System.out::print);
	}
	
	@Test
	public void findStream(){
		// Des opérations terminales qui permettent de retourner le premier élément du  Stream s’il existe en tant qu’Optional,
		persons.stream()
        .findFirst()
        .ifPresent(System.out::print);
		
		// Le résultat de findAny n’est pas constant, le résultat peut varier.
		persons.stream()
        .parallel()
        .findAny()
        .ifPresent(System.out::print);
	}
	
	@Test
	public void flatMapStream(){
		// C’est une opération intermédiraire qui permet de mettre à plat un Stream.
		// On peut par exemple transformer un Stream<List<Person>> en Stream<Person> via une Function<List<Person>, Stream<Person>> :
		Stream.<List<Person>>builder()
        .add(Arrays.asList(new Person("John", "Doe", 30), new Person("Jane", "Doe", 20)))
        .add(Arrays.asList(new Person("Jim", "Smith", 15)))
        .build()
        .flatMap(persons -> persons.stream())
        .filter(person -> "Doe".equals(person.getLastName()))
        .forEach(System.out::println);
	}
	
	@Test
	public void limitSkipStream(){
		// Permet de se déplacer ou de bloquer le nombre d’éléments d’un Stream. Ce sont des opérations intermédiaires.
		persons.stream()
        .limit(2)
        .skip(1)
        .findFirst()
        .ifPresent(System.out::print);
	}
	
	@Test
	public void peek(){
		// Une méthode utile pour débugger entre les opérations efféctuées sur un Stream.
		// C’est une opération intermédiaire qui exécute un Consumer sur chacun des éléments.
		persons.stream()
        .filter(p -> "Doe".equals(p.getLastName()))
        .peek(System.out::println)
        .filter(p -> p.getAge() < 25)
        .peek(System.out::println)
        .collect(Collectors.toSet());
	}
	
	@Test
	public void randomStream()
	{
	Random random = new Random();
	Stream.generate(() -> random.nextInt(10))
	        .limit(50)
	        .forEach(System.out::println);
	//Un Stream peut-être infini, dans ce cas il faut avoir une opération stoppante,
/*
	new Random().ints()
	            .limit(10)
	            .forEach(System.out::println);
	            */
	}
}
