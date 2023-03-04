import extensions.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class AdditionalTest {
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = WebDriverFactory.get("chrome");
    }

    @Test
    public void checkClickSamokatLogo() {

          String actual = new MainPage(driver)
                    .clickSamokatLogo();

        assertEquals("Переход или не выполнен или выполнен не на главную страницу 'Самоката'!","https://qa-scooter.praktikum-services.ru/",actual);

    }

    @After

    public void teardown() {

        driver.quit();

    }
}
