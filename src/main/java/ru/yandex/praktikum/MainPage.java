package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class MainPage {

    private final WebDriver driver;
    //стрелочка разворачивания вопроса
    private final By fullQuestionBtn = By.className("accordion__button");
    //раскрывающийся текст ответа
    private final By answerText = By.xpath(".//div[@class='accordion__panel']/p");
    //кнопка заказа в хедере
    private final By headerOrderBtn = By.xpath(".//div[@class='Header_Nav__AGCXC']" +
            "/button[@class='Button_Button__ra12g']");
    //кнопка заказа в низу страницы
    private final By footerOrderBtn = By.xpath(".//div[@class='Home_FinishButton__1_cWm']" +
            "/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //кнопка принятия куки
    private final By cookieBtn = By.className("App_CookieButton__3cvqF");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //принять куки и закрыть алерт
    public void clickCookieBtn() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(cookieBtn))).click();
    }

    //клик по стрелочке вопроса
    public void clickQuestionBtn(int questionNumber) {
        WebElement element = driver.findElements(fullQuestionBtn).get(questionNumber);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    //вернуть текст ответа на вопрос
    public String getAnswerText(int questionNumber) {
        List<WebElement> elements = driver.findElements(answerText);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(elements.get(questionNumber)));//ожидание элемента
        return elements.get(questionNumber).getText();
    }

    //метод нажатия на кнопку заказа
    public void clickOrderBtn(String locationOrderBtn) {
        By orderButton;
        switch (locationOrderBtn) {
            case "header":
                orderButton = headerOrderBtn;
                break;
            case "footer":
                orderButton = footerOrderBtn;
                break;
            default:
                throw new RuntimeException("orderButton location '" + locationOrderBtn + "' not exist");
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButton));
        driver.findElement(orderButton).click();
    }


}
