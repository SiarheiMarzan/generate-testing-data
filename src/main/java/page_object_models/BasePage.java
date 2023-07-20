package page_object_models;

import drivers.DriverFactory;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected BasePage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

}

