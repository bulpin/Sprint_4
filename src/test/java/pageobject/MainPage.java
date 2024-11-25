package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы
    //Выпадающие вопросы
    private By questionButton = By.xpath("//div[@class='accordion__button']");
    //Текст ответа
    private By questionText = By.xpath("//div[contains(@class, 'accordion__panel')]");
    //Кнопка Заказать (вверху страницы)
    private By orderButtonTop = By.xpath("//button[@class='Button_Button__ra12g']");
    //Кнопка Заказать (внизу страницы)
    private By orderButtonBottom = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    // Метод для скроллинга до вопроса
    public void scrollToQuestion(int index) {
        WebElement questionElement = driver.findElements(questionButton).get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement);
    }

    public void clickQuestion(int index) {
        driver.findElements(questionButton).get(index).click();
        WebElement questionElement = driver.findElements(questionButton).get(index);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Ждём, пока элемент станет кликабельным, и кликаем
        wait.until(ExpectedConditions.elementToBeClickable(questionElement));
        // wait.until(driver -> questionElement.isDisplayed());
        questionElement.click();
    }
    // Метод для получения текста ответа
    public String getAnswerText(int index) {
        WebElement answerElement = driver.findElements(questionText).get(index);
        return answerElement.getText().trim();
    }
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }
    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

}
