package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    //Локатор кнопки с принятием Cookies
    private final By acceptCookie = By.id("rcc-confirm-button");
    //Локатор блока вопросов
    private final By questionBlock = By.className("Home_FAQ__3uVm4");
    //Локатор верхнеей кнопки заказа самоката
    private final By topButtonOrder = By.xpath("//button[@class='Button_Button__ra12g']");
    //Локатор нижней кнопки заказа самоката
    private final By bottomButtonOrder = By.xpath("//div[@class = 'Home_FinishButton__1_cWm']/button[text()='Заказать']");
    //Локатор логотипа самоката
    private final By samokatLogo = By.xpath("//img[@alt = 'Scooter']");
    //Локатор полной загрузки страницы Самоката
    private final By pageSamokatFlag = By.className("App_App__15LM-");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage clickAcceptCookie(){
        driver.findElement(acceptCookie).click();
        return this;
    }

    //Получение текста ответа
    public String getTextAnswer(String idQuestion) {

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id =\"accordion__panel-" + idQuestion +"\"]/p")));

        return driver.findElement(By.xpath("//*[@id =\"accordion__panel-" + idQuestion + "\"]/p")).getText();
    }

    //Клик по полю с вопросом
    public MainPage clickQuestionButton(String question){

        WebElement element = driver.findElement(questionBlock);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        driver.findElement(By.xpath("//*[text() = '"+ question + "']")).click();

        return this;
    }

    public OrderPage clickButtonOrder(String orderButton){

        switch (orderButton) {

            case "Top":

                driver.findElement(topButtonOrder).click();

                break;

            case "Bottom":

                WebElement element = driver.findElement(bottomButtonOrder);
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);


                driver.findElement(bottomButtonOrder).click();

                break;

            default: driver.findElement(topButtonOrder).click();

        }


        return new OrderPage(driver);
    }

    public String clickSamokatLogo(){

        driver.findElement(samokatLogo).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(pageSamokatFlag));

        return driver.getCurrentUrl();
    }

}
