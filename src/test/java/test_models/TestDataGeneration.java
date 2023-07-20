package test_models;

import drivers.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*;
import logger.Log;
import org.testng.Assert;
import page_object_models.TestingChallengesPage;
import utils.CNPGenerator;
import utils.PropertiesReader;

import java.util.ArrayList;
import java.util.List;

@Epic("Testing")
@Feature("Unique Identification Number Generation")
public class TestDataGeneration {
    private static final Log LOGGER = new Log(TestingChallengesPage.class);
    private TestingChallengesPage testingChallengesPage;
    private List<String> generatedCNPs;
    private DriverFactory driverFactory;


    @Description("Test Description : Open web page with field for data input")
    @Severity(SeverityLevel.NORMAL)
    @Given("I am on the webpage")
    public void goToCheckCNPGenerationValuePage() {
        LOGGER.INFO("Open web page with field for data input");
        Allure.step("Open web page with field for data input");
        DriverFactory.getDriver().get(PropertiesReader.readProperty("url"));
        testingChallengesPage = new TestingChallengesPage();
    }

    @Description("Test Description : Enter generated numbers in the input field and submit")
    @Severity(SeverityLevel.NORMAL)
    @When("I submit 5 different valid CNP numbers one at a time")
    public void generateValidCNPsAndSubmit() {
        LOGGER.INFO("Enter generated numbers in the input field and submit");
        Allure.step("Enter generated numbers in the input field and submit");
        generatedCNPs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String cnp = CNPGenerator.generateCNP();
            generatedCNPs.add(cnp);
            testingChallengesPage.clickOnEmptyField(cnp);
            testingChallengesPage.clickButtonCheckValidity();
        }
    }

    @Description("Test Description : Check result on the page")
    @Severity(SeverityLevel.NORMAL)
    @Then("I should see 'YOU HAVE DONE IT. Please enter your name.*' message")
    public void verifyResult() {
        LOGGER.INFO("Check result on the page");
        Allure.step("Check result on the page");
        Assert.assertEquals(testingChallengesPage.getCorrectText(), "YOU HAVE DONE IT. Please enter your name.*");
    }

}
