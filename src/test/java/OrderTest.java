import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;
import pageobject.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    private String browser; // Chrome или Firefox
    private String name;
    private String address;

    public OrderTest(String browser, String name, String address) {
        this.browser = browser;
        this.name = name;
        this.address = address;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Chrome", "Иван Иванов", "Москва, ул. Тверская, 1"},
                {"Firefox", "Анна Петрова", "Санкт-Петербург, Невский пр., 10"}
        };
    }

    @Before
    public void setUp() {
        if ("Chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if ("Firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testOrder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButtonTop();

        OrderPage orderPage = new OrderPage(driver);
        String[] fullName = name.split(" ");
        orderPage.fillName(fullName[0]);
        orderPage.fillLastName(fullName[1]);
        orderPage.fillAddress(address);
        orderPage.selectMetroStation("Сокол");
        orderPage.fillPhone("+79991234567");

        orderPage.clickNextButton();
        orderPage.selectRentalDate("22.11.2024");
        orderPage.selectRentalDuration("двое суток");
        orderPage.selectScooterColor("чёрный жемчуг");
        orderPage.fillComment("Позвоните за час до доставки");

        orderPage.clickOrderButton();
        orderPage.confirmOrder();

        orderPage.checkSuccessMessage("Заказ оформлен");
    }
}
