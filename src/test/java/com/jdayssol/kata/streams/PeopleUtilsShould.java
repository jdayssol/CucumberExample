package com.jdayssol.kata.streams;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class PeopleUtilsShould {

	private PeopleUtils utils = new PeoplesUtilsFunctionnal();
	
	private Person sara = new Person("Sara", 4, "Norwegian");
	private Person viktor = new Person("Viktor", 40, "Serbian");
	private Person eva = new Person("Eva", 42, "Norwegian");
    private Person anna = new Person("Anna", 6, "English");
    private Person joe = new Person("Joe", 28, "English");
	private List<Person> people = Arrays.asList(sara, viktor, eva, anna, joe);

	@Test
	public void convert_elements_to_upper_case() {
		List<String> expected = asList("SARA", "VIKTOR", "EVA", "ANNA", "JOE");

		assertThat(utils.namestoUpperCase(people))
			.hasSameElementsAs(expected);
	}

	@Test
	public void filter_so_that_only_elements_with_less_then_4_characters_are_returned() {
		assertThat(utils.filterPersonHavingShortNames(people))
			.hasSameElementsAs(asList(eva, joe));
	}

	@Test
	public void return_people_names_separated_by_comma() {
		assertThat(utils.namesToString(people))
			.isEqualTo("Names: Sara, Viktor, Eva, Anna, Joe.");
	}

	@Test
	public void flatten_multidimensional_collection() {
		List<List<Person>> collection = asList(asList(sara, viktor), asList(eva, anna));
		List<Person> expected = asList(sara, viktor, eva, anna);

		assertThat(utils.flatMapListOfList(collection))
			.hasSameElementsAs(expected);
	}

	@Test
	public void get_oldest_person_from_the_collection() {
		assertThat(utils.getOldestPerson(people))
			.isEqualToComparingFieldByField(eva);
	}

	@Test
	public void sum_all_elements_of_a_collection() {
		List<Integer> numbers = asList(1, 2, 3, 4, 5);
		assertThat(utils.sum(numbers))
			.isEqualTo(1 + 2 + 3 + 4 + 5);
	}
	
    @Test
    public void get_names_of_all_kids_under_age_of_18() {
        assertThat(utils.getKidNames(people))
            .contains("Sara", "Anna")
            .doesNotContain("Viktor", "Eva");
    }
    
    @Test
    public void build_statistics() {
    	Stats expected = new Stats(5, 40 + 42 + 4 + 6 + 28, 4, 42, (40 + 42 + 4 + 6 + 28) / 5.0);
    	
		assertThat(utils.getStats(people))
			.isEqualToComparingFieldByField(expected);
    }

    @Test
    public void separate_kids_from_adults() {
        Map<Boolean, List<Person>> result = utils.partitionAdults(people);
        
        assertThat(result.get(true)).hasSameElementsAs(asList(viktor, eva, joe));
        assertThat(result.get(false)).hasSameElementsAs(asList(sara, anna));
    }

    @Test
    public void group_people_by_nationality() {
    	Map<String, List<Person>> result = utils.groupByNationality(people);
    	
    	assertThat(result.get("Norwegian")).hasSameElementsAs(asList(sara, eva));
    	assertThat(result.get("Serbian")).hasSameElementsAs(asList(viktor));
    }
}
