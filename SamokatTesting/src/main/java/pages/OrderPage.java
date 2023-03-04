package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    //Локатор заголовка страницы про клиента
    private final By titleClientInfoPage = By.xpath("//*[@class = 'Order_Header__BZXOb' and text() = 'Для кого самокат']");
    //Локатор для заголовка страницы про аренду
    private final By titleRentalInfo = By.xpath("//*[@class = 'Order_Header__BZXOb' and text() = 'Про аренду']");
    //Локатор для заколовка страницы согласия на оформление заказа
    private final By titleRegistrationOrder = By.className("Order_ModalHeader__3FDaJ");
    //Локатор поля для имени
    private final By nameField = By.xpath("//input[@placeholder = '* Имя']");
    //Локатор поля для фамилии
    private final By surnameField = By.xpath("//input[@placeholder = '* Фамилия']");
    //Локатор для поля адрес
    private final By addressField = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Локатор для поля станции метро
    private final By undergroundField = By.xpath("//input[@placeholder = '* Станция метро']");
    //Локатор для выпадающего поля со станциями метро
    private final By selectUndergroundField = By.className("select-search__select");
    //Локатор для поля номера телефона
    private final By phoneNumberField = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Локатор для кнопки далее
    private final By nextButton = By.className("Button_Middle__1CSJM");
    //Локатор для поля даты доставки
    private final By dateDeliveryField = By.xpath("//input[@placeholder = '* Когда привезти самокат']");
    //Локатор для поля срока аренды
    private final By rentalPeriodField = By.className("Dropdown-placeholder");
    //Локатор для поля комментария курьеру
    private final By commentDeliveryBoyField = By.xpath("//input[@placeholder = 'Комментарий для курьера']");
    //Локатор для кнопки Заказать
    private final By orderButton = By.xpath("//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']");
    //Локатор кнопки согласия на оформление заказа
    private final By yesOrderButtonField = By.xpath("//button[text() = 'Да']");
    //Локатор для окна с подтверждением создания заказа
    private final By orderCreateField = By.xpath("//*[text() = 'Заказ оформлен']");

    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage setClientName(String clientName){

        driver.findElement(nameField).sendKeys(clientName);

        return this;
    }

    public OrderPage setClientSurname(String clientSurname){

        driver.findElement(surnameField).sendKeys(clientSurname);

        return this;
    }

    public OrderPage setClientAddress(String clientAddress){

        driver.findElement(addressField).sendKeys(clientAddress);

        return this;
    }

    public OrderPage setClientUnderground(String clientUnderground){

        driver.findElement(undergroundField).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(selectUndergroundField));

        WebElement element = driver.findElement(By.xpath("//button[@value = '"+clientUnderground+"']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        driver.findElement(By.xpath("//button[@value = '"+clientUnderground+"']")).click();

       return this;
    }

    public OrderPage setClientPhoneNumber(String clientPhoneNumber){

        driver.findElement(phoneNumberField).sendKeys(clientPhoneNumber);

        return this;
    }

    public OrderPage setClientInfo(String clientName,String clientSurname,String clientAddress,String clientUnderground,
                                   String clientPhoneNumber){

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(titleClientInfoPage));

        setClientName(clientName);
        setClientSurname(clientSurname);
        setClientAddress(clientAddress);
        setClientUnderground(clientUnderground);
        setClientPhoneNumber(clientPhoneNumber);
        return this;
    }

    public OrderPage clickButtonNext(){

        driver.findElement(nextButton).click();

        return this;
    }
    public OrderPage setDateDelivery(String dateDelivery){

        driver.findElement(dateDeliveryField).sendKeys(dateDelivery);
        driver.findElement(dateDeliveryField).sendKeys(Keys.ENTER);

        return this;
    }

    public OrderPage setRentalPeriod(String rentalPeriod){

        driver.findElement(rentalPeriodField).click();

        driver.findElement(By.xpath("//*[text() = '"+ rentalPeriod + "']")).click();

        return this;
    }

    public OrderPage setColourSamokat(String colourSamokat){

        driver.findElement(By.id(colourSamokat)).click();

        return this;
    }

    public OrderPage setCommentDeliveryBoy(String commentDeliveryBoy){

        driver.findElement(commentDeliveryBoyField).sendKeys(commentDeliveryBoy);

        return this;
    }

    public OrderPage clickOrderButton(){

        driver.findElement(orderButton).click();

        return this;
    }

    public OrderPage setRentalInfo(String dateDelivery, String rentalPeriod,String colourSamokat,String commentDeliveryBoy){

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(titleRentalInfo));

        setDateDelivery(dateDelivery);
        setRentalPeriod(rentalPeriod);
        setColourSamokat(colourSamokat);
        setCommentDeliveryBoy(commentDeliveryBoy);
        clickOrderButton();

        return this;
    }


    public  OrderPage clickYesRegistrationOrder(){

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(titleRegistrationOrder));

        driver.findElement(yesOrderButtonField).click();

        return this;
    }

    public boolean isOrderCreate(){

        return driver.findElement(orderCreateField).isDisplayed();

    }

}
