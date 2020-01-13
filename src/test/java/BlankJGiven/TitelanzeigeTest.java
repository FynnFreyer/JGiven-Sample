package BlankJGiven;


import BlankJGiven.Steps.Dann;
import BlankJGiven.Steps.Gegeben;
import BlankJGiven.Steps.Wenn;
import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class TitelanzeigeTest extends ScenarioTest<Gegeben, Wenn, Dann> {

    @Test
    public void der_Titel_ändert_sich() {
        given().der_Browser_wird_geöffnet().
                and().die_Seite_$_wird_aufgerufen("http://localhost/mockapp");
        when().$_geklickt_wird("bar");
        then().lautet_der_Titel_$("Bar");
    }
}
