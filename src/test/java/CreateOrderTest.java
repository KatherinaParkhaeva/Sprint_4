import config.AppConfig;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageObject.CreateOrderPage;
import pageObject.MainPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.StringContains.containsString;


@RunWith(Parameterized.class)
public class CreateOrderTest {

    private WebDriver driver;
    private String locationOrderBtn;
    private String userName;
    private String userLastName;
    private String userAddress;
    private String metroStation;
    private String userPhone;
    private String date;
    private int indexRentDays;
    private int indexColour;
    private String comment;




    public CreateOrderTest(String locationOrderBtn,String userName,String userLastName,
                           String userAddress,String metroStation,String userPhone, String date, int indexRentDays,
                           int indexColour, String comment){
        this.locationOrderBtn=locationOrderBtn;
        this.userName=userName;
        this.userLastName=userLastName;
        this.userAddress=userAddress;
        this.metroStation=metroStation;
        this.userPhone=userPhone;
        this.date=date;
        this.indexRentDays =indexRentDays;
        this.indexColour=indexColour;
        this.comment=comment;
    }

    //для запуска в хроме
   @Before
    public void websiteLaunch(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().to(AppConfig.MAIN_PAGE_URL);
    }

    //для запуска в фф
    /*@Before
    public void websiteLaunch(){
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().to(AppConfig.MAIN_PAGE_URL);
    }*/

    @Parameterized.Parameters
    public static Object[][] newOrderParams(){
        return new Object[][]{
                {"header","Иван","Грозный","Кремль, 1","Охотный ряд","+79990000000","08.03.23",1,0,""},
                {"footer","МарфаВасильевна", "Собакина","проспект Андропова, вл21А","Коломенская","89990000000","10.05.23",5,1,"Танцуют все!"},
        };
    }

    @Test
    public void createNewOrder (){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookieBtn();
        objMainPage.clickOrderBtn(locationOrderBtn);
        CreateOrderPage objCreateOrderPage = new CreateOrderPage(driver);
        objCreateOrderPage.createFirstPartOrder(userName,userLastName,userAddress,metroStation,userPhone);
        objCreateOrderPage.createSecondPartOrder(date,indexRentDays,indexColour,comment);
        MatcherAssert.assertThat(objCreateOrderPage.getSuccessAlert(),containsString("Заказ оформлен"));
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
