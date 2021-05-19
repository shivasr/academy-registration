package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CourseStepdefs {
    @Given("I am administrator")
    public void iAmAdministrator() {
    }

    @When("I create a course named {string}")
    public void iCreateACourseNamed(String courseName) {
    }

    @Then("I should see a new course named {string}")
    public void iShouldSeeANewCourseNamed(String courseName) {
    }

    @And("create a course id {long}")
    public void createACourseId(long id) {
    }

    @When("I update the course with course Id {long} with a new name {string}")
    public void iUpdateTheCourseWithCourseIdWithANewName(long id, String courseName) {
    }

    @Then("I should see the course with id {long} with new name {string}")
    public void iShouldSeeTheCourseWithIdWithNewName(long id, String courseName) {
    }
}
