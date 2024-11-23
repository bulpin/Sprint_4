package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class OrderPage {
    private WebDriver driver;

    // Локаторы полей формы
    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    private By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationDropdown = By.className("select-search__input");
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Локаторы для шага 2
    private By datePicker = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentalDurationDropdown = By.className("Dropdown-arrow");
    private By colorCheckboxBlack = By.id("black");
    private By colorCheckboxGrey = By.id("grey");
    private By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Кнопки
    private By nextButton = By.xpath("//button[text()='Далее']");
    private By orderButton = By.xpath("//button[text()='Заказать']");
    private By confirmButton = By.xpath("//button[text()='Да']");//я использовал 4 вида локаторов и не один не подошел


    // Успешное сообщение
    private By successMessage = By.className("Order_Modal__success");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void fillLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void fillAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void selectMetroStation(String station) {
        driver.findElement(metroStationDropdown).sendKeys(station);
        driver.findElement(By.xpath("//div[text()='" + station + "']")).click();
    }

    public void fillPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void selectRentalDate(String date) {
        driver.findElement(datePicker).sendKeys(date);
    }

    public void selectRentalDuration(String duration) {
        driver.findElement(rentalDurationDropdown).click();
        driver.findElement(By.xpath("//div[text()='" + duration + "']")).click();
    }

    public void selectScooterColor(String color) {
        if (color.equalsIgnoreCase("чёрный жемчуг")) {
            driver.findElement(colorCheckboxBlack).click();
        } else if (color.equalsIgnoreCase("серая безысходность")) {
            driver.findElement(colorCheckboxGrey).click();
        }
    }

    public void fillComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        driver.findElement(confirmButton).click();
    }

    public void checkSuccessMessage(String expectedMessage) {
        String actualMessage = driver.findElement(successMessage).getText();
        assert actualMessage.contains(expectedMessage) : "Ожидалось сообщение: " + expectedMessage;
    }
}
//тестовый комментарий