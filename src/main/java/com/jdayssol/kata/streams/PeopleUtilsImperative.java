package com.jdayssol.kata.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PeopleUtilsImperative implements PeopleUtils{

	@Override
	public List<String> namestoUpperCase(List<Person> collection) {
        List<String> newCollection = new ArrayList<>();
        for (Person element : collection) {
            newCollection.add(element.getName().toUpperCase());
        }
        return newCollection;
	}

	@Override
	public List<Person> filterPersonHavingShortNames(List<Person> people) {
        List<Person> newCollection = new ArrayList<>();
        for (Person element : people) {
            if (element.getName().length() < 4) {
                newCollection.add(element);
            }
        }
        return newCollection;
	}

	@Override
	public String namesToString(List<Person> people) {
        String label = "Names: ";
        StringBuilder sb = new StringBuilder(label);
        for (Person person : people) {
            if (sb.length() > label.length()) {
                sb.append(", ");
            }
            sb.append(person.getName());
        }
        sb.append(".");
        return sb.toString();
	}

	@Override
	public List<Person> flatMapListOfList(List<List<Person>> people) {
        List<Person> newCollection = new ArrayList<>();
        for (List<Person> subCollection : people) {
            for (Person value : subCollection) {
                newCollection.add(value);
            }
        }
        return newCollection;
	}

	@Override
	public Person getOldestPerson(List<Person> people) {
        Person oldestPerson = new Person("", 0, "");
        for (Person person : people) {
            if (person.getAge() > oldestPerson.getAge()) {
                oldestPerson = person;
            }
        }
        return oldestPerson;
	}

	@Override
	public Integer sum(List<Integer> numbers) {
		Integer total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
	}

	@Override
	public Set<String> getKidNames(List<Person> people) {
        Set<String> kids = new HashSet<>();
        for (Person person : people) {
            if (person.getAge() < 18) {
                kids.add(person.getName());
            }
        }
        return kids;
	}

	@Override
	public Stats getStats(List<Person> people) {
        long sum = 0;
        int min = people.get(0).getAge();
        int max = 0;
        double avg = 0.0;
        for (Person person : people) {
            int age = person.getAge();
            sum += age;
            min = Math.min(min, age);
            max = Math.max(max, age);
        }
        avg = people.size() > 0 ? (double) sum / people.size() : 0.0d;
        return new Stats(people.size(), sum, min, max, avg);
	}

	@Override
	public Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
        Map<Boolean, List<Person>> map = new HashMap<>();
        map.put(true, new ArrayList<>());
        map.put(false, new ArrayList<>());
        for (Person person : people) {
            map.get(person.getAge() >= 18).add(person);
        }
        return map;
	}

	@Override
	public Map<String, List<Person>> groupByNationality(List<Person> people) {
        Map<String, List<Person>> map = new HashMap<>();
        for (Person person : people) {
            if (!map.containsKey(person.getNationality())) {
                map.put(person.getNationality(), new ArrayList<>());
            }
            map.get(person.getNationality()).add(person);
        }
        return map;
	}

}
