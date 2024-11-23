import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MainPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuestionsTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }

    @Test
    public void testQuestionsTextIsVisible() {
        MainPage mainPage = new MainPage(driver);

        // Цикл проверки всех вопросов
        for (int i = 0; i < 8; i++) {
            mainPage.scrollToQuestion(i); // Скроллим к вопросу
            mainPage.clickQuestion(i);   // Кликаем по вопросу
            String answer = mainPage.getAnswerText(i); // Получаем текст ответа

            // Проверяем, что текст ответа отображается
            assertFalse("Ответ на вопрос №" + (i + 1) + " не отображается!", answer.isEmpty());
        }
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
