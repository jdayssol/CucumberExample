package com.jdayssol.kata.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class PeoplesUtilsFunctionnal implements PeopleUtils {

	@Override
	public List<String> namestoUpperCase(List<Person> collection) {
		// -> Methode lambda
		List<String> results = collection.stream()
				.map(person -> person.getName())
				.map(name -> name.toUpperCase())
				.collect(toList());
		
		// Methode reference
		List<String> results2 = collection.stream()
				.map(Person::getName)
				.map(String::toUpperCase)
				.collect(toList());
		return results;
	}

	@Override
	public List<Person> filterPersonHavingShortNames(List<Person> people) {
		// Utilisation d'un predicat sous forme de methode lambda
		List<Person> results = people.stream()
				.filter(person -> person.getName().length() < 4)
				.collect(toList());
		// Utilisation d'un pr�dicat sous forme de fonction
		List<Person> results2 = people.stream()
				.filter(PeoplesUtilsFunctionnal::filterShortName)
				.collect(toList());
		return results;
	}

	private static boolean filterShortName(Person person) {
		String name = person.getName();
		return name.length() < 4;
	}

	@Override
	public String namesToString(List<Person> people) {
		String result =people.stream()
		.map(Person::getName)
		.collect(joining(", ","Names: ","."));
		
        return result;
	}

	@Override
	public List<Person> flatMapListOfList(List<List<Person>> people) {
		//flatMap permet de faire un traitement sur une liste de stream.
		List<Person> result = people.stream()
				.flatMap(List::stream).collect(toList());
        return result;
	}

	@Override
	public Person getOldestPerson(List<Person> people) {
		// Un optional permet d'indiquer au developpeur que l'objet retourn� peux �tre null
		// On peut faire un get pour le r�cuperer, 
		// isPresent pour tester si il est null, 
		// orElse permet de passer un supplier de person
		
		Comparator<Person> comparatorAge = new Comparator<Person>() {
	        @Override
	        public int compare(Person person1, Person person2) {    
	        	return Integer.compare(person1.getAge(), person2.getAge());
	        }
	    };
	    
	    // Avec un comparator sous forme de fonction
		Optional<Person> oldestPerson2 = people.stream()
				.max(comparatorAge);
		
		// Avec un comparator sous forme de fonction lambda
		Optional<Person> oldestPerson3 = people.stream()
				.max((p1,p2) -> p1.getAge() - p2.getAge());
		
		// Avec un comparator sous forme de fonction lambda
				Optional<Person> oldestPerson = people.stream()
				.max(Comparator.comparingInt(Person::getAge));

		// Pour renvoyer la plus jeune
		Optional<Person> oldestPerson4 = people.stream().min(Comparator.comparingInt(Person::getAge));

		// Si la liste est vide, la spec dit de ramener une personne avec l'age 0 . 
		//On peut alors utiliser orElse(new Person("", 0, "") , mais le new person sera toujours execute
		//Sinon orElseGet qui utilise un supplier , cela permet 
		Person oldestPersonOrElse = people.stream().max(Comparator.comparingInt(Person::getAge))
				.orElseGet(() -> new Person("", 0, ""));
		return oldestPerson.get();
	}

	@Override
	public Integer sum(List<Integer> numbers) {
		// reduce
		Integer sum = numbers.stream()
				.reduce(new Integer(0),Integer::sum);
        return sum;
	}

	@Override
	public Set<String> getKidNames(List<Person> people) {
		// filter + map
		Set <String> kidsNames = people.stream()
				.filter(person -> person.getAge() < 18)
				.map(person -> person.getName()).collect(toSet());
        return kidsNames;
	}

	@Override
	public Stats getStats(List<Person> people) {
		//summaryStatistics
		IntSummaryStatistics statistics = people.stream()
				.mapToInt(Person::getAge)
				.summaryStatistics();	
        return new Stats(statistics.getCount(), statistics.getSum(), statistics.getMin(), statistics.getMax(), statistics.getAverage());
	}

	@Override
	public Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
		// partitioningBy
	    return people.stream()
					.collect(partitioningBy(person -> person.getAge() > 18));
	}

	@Override
	public Map<String, List<Person>> groupByNationality(List<Person> people) {
		// groupingBy
		return people.stream()
				.collect(Collectors.groupingBy(person -> person.getNationality()));
	}

}
