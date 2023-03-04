package extensions;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import static config.AppConfig.URL;

public class WebDriverFactory {

    public static WebDriver get(String browserName) {

        WebDriver driver;

        switch (browserName) {

            case "chrome":

                driver = new ChromeDriver();

                break;

            case "edge":

                driver = new EdgeDriver();

                break;

            default: throw new RuntimeException("Browser " + browserName + " not exist");

        }

        driver.manage().window().maximize();

        driver.navigate().to(URL);

        return driver;

    }

}