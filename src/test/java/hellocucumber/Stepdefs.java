package hellocucumber;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import day.IsItFriday;




public class Stepdefs {
	private String today;
    private String actualAnswer;

	
	@Given("^today is \"([^\"]*)\"$")
	public void today_is(String today){
		this.today = today;
	}

	@When("^I ask whether its Friday yet$")
	public void i_ask_whether_is_s_Friday_yet() {
		this.actualAnswer = IsItFriday.isItFriday(today);
	}

	@Then("^I should be told \"([^\"]*)\"$")
	public void i_should_be_told(String expectedAnswer) {
		Assert.assertEquals(expectedAnswer, actualAnswer);
	}
}
