import extensions.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SamokatTest {

    private WebDriver driver;
    private String orderButton;
    private String clientName;
    private String clientSurname;
    private String clientAdress;
    private String clientUnderground;
    private String clientPhoneNumber;
    private String clientDateDelivery;
    private String clientRentalPeriod;
    private String clientColourSamokat;
    private String clientCommentDeliveryBoy;

    public SamokatTest(String orderButton, String clientName, String clientSurname, String clientAdress,
                       String clientUnderground, String clientPhoneNumber, String clientDateDelivery,
                       String clientRentalPeriod, String clientColourSamokat, String clientCommentDeliveryBoy) {
        this.orderButton = orderButton;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientAdress = clientAdress;
        this.clientUnderground = clientUnderground;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientDateDelivery = clientDateDelivery;
        this.clientRentalPeriod = clientRentalPeriod;
        this.clientColourSamokat = clientColourSamokat;
        this.clientCommentDeliveryBoy = clientCommentDeliveryBoy;
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = WebDriverFactory.get("edge");
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Top","Павел","Кулямин","Улица Пушкина","5","89999999999","08.03.2023","трое суток","grey","GG Hf"},
                {"Bottom","Александр","Невский","Улица Абсолютли","15","89999999999","10.04.2023","четверо суток","black","Большое вам спасибо!"},
        };
    }

    @Test
    public void Order() {

        Boolean actual = new MainPage(driver)
                .clickAcceptCookie()
                .clickButtonOrder(orderButton)
                .setClientInfo(clientName,clientSurname,clientAdress,clientUnderground,clientPhoneNumber)
                .clickButtonNext()
                .setRentalInfo(clientDateDelivery,  clientRentalPeriod, clientColourSamokat, clientCommentDeliveryBoy)
                .clickYesRegistrationOrder()
                .isOrderCreate();

        assertTrue("Окно заказ оформлен отсутсвует! Заказ не оформлен!",actual);

    }

    @After
    public void teardown() {

        driver.quit();

    }
}
