import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MainPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuestionsTest {

    private WebDriver driver;
    private final int questionIndex;

    public QuestionsTest(int questionIndex) {
        this.questionIndex = questionIndex;
    }
    // Данные для параметризации
    @Parameterized.Parameters
    public static Object[] getQuestionsIndices() {
        return new Object[]{0, 1, 2, 3, 4, 5, 6, 7}; // Индексы вопросов
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }

    @Test
    public void testQuestionsTextIsVisible() {
        MainPage mainPage = new MainPage(driver);

        // Скроллим к вопросу
        mainPage.scrollToQuestion(questionIndex);

        // Кликаем по вопросу
        mainPage.clickQuestion(questionIndex);

        // Получаем текст ответа
        String answer = mainPage.getAnswerText(questionIndex);

        // Проверяем, что текст ответа отображается
        assertFalse("Ответ на вопрос №" + (questionIndex + 1) + " не отображается!", answer.isEmpty());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}