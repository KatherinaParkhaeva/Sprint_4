import constants.SiteUrl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.MainPage;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQTest {
    private final int questionNumber;
    private final String answerText;
    private WebDriver driver;

    public FAQTest(int questionNumber, String answerText) {
        this.questionNumber = questionNumber;
        this.answerText = answerText;
    }

    @Parameterized.Parameters(name="Сверка блока FAQ. Тестовые данные {0},{1},{2},{3},{4},{5},{6},{7}")
    public static Object[][] getItemFAQ() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                        "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
                        "даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    //для запуска в фф
    /*@Before
    public void websiteLaunch(){
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().to(AppConfig.MAIN_PAGE_URL);
    }*/

    @Before
    public void websiteLaunch() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().to(SiteUrl.MAIN_PAGE_URL);
    }

    @Test
    public void checkQuestion() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookieBtn();
        objMainPage.clickQuestionBtn(questionNumber);
        assertEquals(answerText, objMainPage.getAnswerText(questionNumber));
    }

    @After
    public void teardown() {
        driver.quit();
    }

}