import constants.SiteUrl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.CreateOrderPage;
import ru.yandex.praktikum.MainPage;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final String locationOrderBtn;
    private final String userName;
    private final String userLastName;
    private final String userAddress;
    private final String metroStation;
    private final String userPhone;
    private final String date;
    private final int indexRentDays;
    private final int indexColour;
    private final String comment;
    private WebDriver driver;

    public CreateOrderTest(String locationOrderBtn, String userName, String userLastName,
                           String userAddress, String metroStation, String userPhone, String date, int indexRentDays,
                           int indexColour, String comment) {
        this.locationOrderBtn = locationOrderBtn;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userAddress = userAddress;
        this.metroStation = metroStation;
        this.userPhone = userPhone;
        this.date = date;
        this.indexRentDays = indexRentDays;
        this.indexColour = indexColour;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Данные для формы заказа. Тестовые данные {0},{1}")
    public static Object[][] newOrderParams() {
        return new Object[][]{
                {"header", "Иван", "Грозный", "Кремль, 1", "Охотный ряд", "+79990000000", "08.03.23", 1, 0, ""},
                {"footer", "МарфаВасильевна", "Собакина", "проспект Андропова, вл21А", "Коломенская", "89990000000", "10.05.23", 5, 1, "Танцуют все!"},
        };
    }

    //для запуска в фф
    /*@Before
    public void websiteLaunch(){
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().to(AppConfig.MAIN_PAGE_URL);
    }*/

    //для запуска в хроме
    @Before
    public void websiteLaunch() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().to(SiteUrl.MAIN_PAGE_URL);
    }

    @Test
    public void createNewOrder() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookieBtn();
        objMainPage.clickOrderBtn(locationOrderBtn);
        CreateOrderPage objCreateOrderPage = new CreateOrderPage(driver);
        objCreateOrderPage.createFirstPartOrder(userName, userLastName, userAddress, metroStation, userPhone);
        objCreateOrderPage.createSecondPartOrder(date, indexRentDays, indexColour, comment);
        //MatcherAssert.assertThat(objCreateOrderPage.getSuccessAlert(), containsString("Заказ оформлен"));
        assertEquals("Не удалось оформить заказ",true,objCreateOrderPage.getSuccessAlert().contains("Заказ оформлен"));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}