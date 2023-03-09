package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateOrderPage {

    private final WebDriver driver;

    //найти поле Имя
    private final By userNameField = By.xpath(".//input[contains(@placeholder, 'Имя')]");

    //поле фамилия
    private final By userLastNameField = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");

    //поле адрес
    private final By userAddressField = By.xpath(".//input[contains(@placeholder, 'Адрес')]");

    //поле станция метро
    private final By metroStationField = By.className("select-search__input");

    //выпадающий список станций
    private final By metroStationList = By.xpath(".//div[@class = 'Order_Text__2broi']/parent::button");

    //поле телефон
    private final By userPhoneField = By.xpath(".//input[contains(@placeholder, 'Телефон')]");

    //кнопка Далее
    private final By nextPageBtn = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    //поле с датой
    private final By dateField = By.xpath(".//div[@class = 'react-datepicker__input-container']/input");

    //поле выбора срока аренды
    private final By rentalPeriodField = By.className("Dropdown-root");

    //выпадающий список срока аренды
    private final By rentalPeriodOptions = By.className("Dropdown-option");

    //выбор цвета самоката
    private final By colourCheckBox = By.className("Checkbox_Label__3wxSf");

    //поле комментарий
    private final By commentField = By.xpath(".//input[contains(@placeholder, 'Комментарий')]");

    //кнопка заказать
    private final By orderFinishBtn = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    //кнопка подтверждения заказа
    private final By orderConfirmBtn = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']/div" +
            "/button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    //алерт успеха
    private final By successAlert = By.className("Order_ModalHeader__3FDaJ");


    public CreateOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //методы на заполнение полей первой части формы заказа
    public void setUserNameField(String userName) {
        driver.findElement(userNameField).sendKeys(userName);
    }

    public void setUserLastNameField(String userLastName) {
        driver.findElement(userLastNameField).sendKeys(userLastName);
    }

    public void setUserAddressField(String userAddress) {
        driver.findElement(userAddressField).sendKeys(userAddress);
    }

    public void setMetroStationField(String metroStation) {
        driver.findElement(metroStationField).sendKeys(metroStation);
        driver.findElements(metroStationList).get(0).click();
    }

    public void setUserPhoneField(String userPhone) {
        driver.findElement(userPhoneField).sendKeys(userPhone);
    }

    public void clickNextPageBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(nextPageBtn));
        driver.findElement(nextPageBtn).click();
    }

    //заполняем первую часть формы заказа
    public void createFirstPartOrder(String userName, String userLastName, String userAddress, String metroStation, String phoneNumber) {
        setUserNameField(userName);
        setUserLastNameField(userLastName);
        setUserAddressField(userAddress);
        setMetroStationField(metroStation);
        setUserPhoneField(phoneNumber);
        clickNextPageBtn();
    }

    //методы на заполнение полей второй части формы заказа
    public void setDateField(String date) {
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dateField).sendKeys(Keys.ENTER);
    }

    public void setRentalPeriodField(int indexRentDays) {
        driver.findElement(rentalPeriodField).click();
        driver.findElements(rentalPeriodOptions).get(indexRentDays).click();
    }

    public void setColourCheckBox(int indexColour) {
        driver.findElements(colourCheckBox).get(indexColour).click();
    }

    public void setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderFinishBtn() {
        driver.findElement(orderFinishBtn).click();
    }

    public void clickOrderConfirmBtn() {
        driver.findElement(orderConfirmBtn).click();
    }

    //заполняем вторую часть формы заказа
    public void createSecondPartOrder(String date, int rentDays, int indexColour, String comment) {
        setDateField(date);
        setRentalPeriodField(rentDays);
        setColourCheckBox(indexColour);
        setCommentField(comment);
        clickOrderFinishBtn();
        clickOrderConfirmBtn();
    }

    //дожидаемся алерта об успехе
    public String getSuccessAlert() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(successAlert))).isDisplayed();
        return driver.findElement(successAlert).getText();
    }
}
