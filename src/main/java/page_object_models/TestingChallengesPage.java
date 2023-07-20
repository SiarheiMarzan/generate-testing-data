package page_object_models;

import logger.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestingChallengesPage extends BasePage {
    private static final Log LOGGER = new Log(TestingChallengesPage.class);
    @FindBy(xpath = "//input[@class='inputbox']")
    private WebElement inputField;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//span[@class='block_label' and contains(.,'YOU HAVE DONE IT. Please enter your name.')]")
    private WebElement correctAnswer;

    public void clickOnEmptyField(String value) {
        LOGGER.INFO("Click in input field");
        inputField.sendKeys(value);
    }

    public void clickButtonCheckValidity() {
        LOGGER.INFO("Click on submit button");
        submitButton.click();
    }

    public String getCorrectText() {
        LOGGER.INFO("Get correct control text from web site");
        return correctAnswer.getText();
    }
}
