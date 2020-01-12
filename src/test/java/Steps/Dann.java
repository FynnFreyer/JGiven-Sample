package Steps;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterScenario;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

public class Dann extends Stage<Dann> {

    @ExpectedScenarioState(required = true)
    WebDriver driver;

    public Dann lautet_der_Titel_$(@Quoted String title) {
        Assert.assertEquals(title, driver.getTitle());
        return self();
    }

    @AfterScenario
    public void closeBrowser() {
        driver.close();
    }
}
