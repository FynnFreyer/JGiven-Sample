package Steps;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Wenn extends Stage<Wenn> {

    @ExpectedScenarioState(required = true)
    WebDriver driver;

    public Wenn $_geklickt_wird(@Quoted String elementId) {
        driver.findElement(By.id(elementId)).click();
        return self();
    }

}
