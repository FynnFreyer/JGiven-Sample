package Steps;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Gegeben extends Stage<Gegeben> {

    @ProvidedScenarioState
    WebDriver driver;

    public Gegeben der_Browser_wird_geöffnet(){
        driver = new FirefoxDriver();
        return self();
    }

    public Gegeben die_Seite_$_wird_aufgerufen(@Quoted String pageURL) {
        driver.get(pageURL);
        return self();
    }
}
