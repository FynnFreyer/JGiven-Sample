# JGiven

## Installation

füge folgendes in die dependencies der `pom.xml` ein.

    <dependency>
        <groupId>com.tngtech.jgiven</groupId>
        <artifactId>jgiven-core</artifactId>
        <version>0.18.2</version>
    </dependency>

und für JUnit (4) Support:

    <dependency>
       <groupId>com.tngtech.jgiven</groupId>
       <artifactId>jgiven-junit</artifactId>
       <version>0.18.2</version>
       <scope>test</scope>
    </dependency>

JUnit muss seperat eingebunden werden, da jgiven-junit nicht direkt von JUnit abhängt. 
Für JUnit5 Support wird stattdessen jgiven-junit5 eingebunden.

## Nutzung

Erstelle eine JUnit Testklasse die von `com.tngtech.jgiven.junit.ScenarioTest` ableitet.

    import com.tngtech.jgiven.junit.ScenarioTest;
    
    public class Testklasse
            extends ScenarioTest<Gegeben, Wenn, Dann> {
    }

die drei Typenparameter erwarten Klassen in denen die einzelnen Schritte der Szenarios (gegeben, wenn & dann) definiert werden.
Alternativ kann man von `com.tngtech.jgiven.junit.SimpleScenarioTest` ableiten und alle Schrittdefinitionen in eine Klasse packen.

Schrittdefinitionen kommen in die dem Szenario übergebenen Klassen (hier `Gegeben` `Wenn` und `Dann`)
z.B. so:

    import com.tngtech.jgiven.Stage;
    
    public class Dann extends Stage<Dann> {
    }

es macht Sinn von `com.tngtech.jgiven.Stage` abzuleiten, 
da dann automatisch Conveniencefunktionen wie `and()` genutzt werden können 
um Schritte zusammenzufassen.

Wenn zwischen den Schrittklassen Variablen geteilt werden sollen, kann das über Annotationen mit `@ProvidedScenarioState` bzw. `@ExpectedScenarioState` realisiert werden.
JGiven stellt dann die gleichnamigen annotierten Variablen automatisch in der anderen Klasse zur Verfügung.

das könnte z.B. so aussehen:

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

die `Gegeben`-Klasse instanziiert einen Webdriver mit dem später in der `Wenn`-Klasse Aktionen durchgeführt werden sollen

    public class Wenn extends Stage<Wenn> {
    
        @ExpectedScenarioState(required = true)
        WebDriver driver;
    
        public Wenn $_geklickt_wird(@Quoted String elementId) {
            driver.findElement(By.id(elementId)).click();
            return self();
        }
    
    }

durch die optionale Erweiterung `(required = true)` wird automatisch geprüft ob die Variable in der vorigen Stufe einen Wert zugewiesen bekommen hat.

Als Alternative zur Schrittdefinition in verschiedenen Klassen gibt es die Möglichkeit alles in eine Klasse zu packen:

Dazu leitet man seine Testklasse nicht von `ScenarioTest` sondern von `SimpleScenarioTest` ab. 
e.g.

    public class Testklasse extends SimpleScenarioTest<Schritte> {
    
        @Test
        public void foo_bar() {
            given().foo();
            when().bar();
            then().baz();
        }
        
        static class Schritte extends Stage<Schritte> {
        
            public Schritte foo(){
                return self();
            }
        
            public Schritte bar(){
                return self();
            }
        
            public Schritte baz(){
                return self();
            }
        }
    }


Ein `$` in Methodennamen (oder dem Text einer `@As`-Annotation) wird im Report mit dem übergeben Parameter ersetzt.

Eine Annotation eines Parameters mit `@Quoted` fügt Anführungszeichen im Report hinzu.

Mithilfe der `@As("FooBar")` Annotation kann man eigene Texte für den Report schreiben.

`@BeforeStage`, `@BeforeScenario`, `@AfterStage` und `@AfterScenario` ersetzen `@BeforeEach`, `@BeforeAll` etc. 
und können für SetUp und TearDown benutzt werden


