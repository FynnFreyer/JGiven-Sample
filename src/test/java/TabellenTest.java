import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterScenario;
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.junit.SimpleScenarioTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TabellenTest extends SimpleScenarioTest<TabellenTest.TabellenStepDefs> {

    @Test
    public void summe_ist_korrekt() {
        given().der_Browser_wird_geöffnet().
                and().die_Seite_$_wird_aufgerufen("http://localhost/mockapp/table.php");
        when().der_$_Knopf_gedrückt_wird("sumBtn");
        then().wird_die_Summe_angezeigt();
    }

    @Test
    public void durchschnitt_ist_korrekt() {
        given().der_Browser_wird_geöffnet().
                and().die_Seite_$_wird_aufgerufen("http://localhost/mockapp/table.php");
        when().der_$_Knopf_gedrückt_wird("avgBtn");
        then().wird_der_Durchschnitt_angezeigt();
    }



    static class TabellenStepDefs extends Stage<TabellenStepDefs> {
        private WebDriver driver;

        @AfterScenario
        public void closeBrowser() {
            driver.close();
        }

        public TabellenStepDefs der_Browser_wird_geöffnet(){
            driver = new FirefoxDriver();
            return self();
        }


        public TabellenStepDefs die_Seite_$_wird_aufgerufen(@Quoted String s) {
            driver.get(s);
            return self();
        }

        public TabellenStepDefs der_$_Knopf_gedrückt_wird(@Quoted String elementID) {
            driver.findElement(By.id(elementID)).click();
            return self();
        }

        public TabellenStepDefs wird_die_Summe_angezeigt() {
            String actual = driver.findElement(By.id("result")).getText();
            Assert.assertEquals("21", actual);
            return self();
        }

        public TabellenStepDefs wird_der_Durchschnitt_angezeigt() {
            String actual = driver.findElement(By.id("result")).getText();
            Assert.assertEquals("7", actual);
            return self();
        }
    }
}


