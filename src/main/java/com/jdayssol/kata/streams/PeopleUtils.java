package com.jdayssol.kata.streams;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PeopleUtils {

    public List<String> namestoUpperCase(List<Person> collection);
    public List<Person> filterPersonHavingShortNames(List<Person> people);
    public String namesToString(List<Person> people);
    public List<Person> flatMapListOfList(List<List<Person>> people);
    public Person getOldestPerson(List<Person> people);
    public Integer sum(List<Integer> numbers);
    public Set<String> getKidNames(List<Person> people);
    public Stats getStats(List<Person> people);
    public Map<Boolean, List<Person>> partitionAdults(List<Person> people);
    public Map<String, List<Person>> groupByNationality(List<Person> people);

}
