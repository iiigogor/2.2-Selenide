package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
//import static java.time.Duration.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CardDeliveryTest {
    public LocalDate startDate = LocalDate.now();
    public String setDateCurrent = startDate.plusDays(3)
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1280x1024";
        Configuration.headless = true;
        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
    }

    @Test
    void shouldTest() {

        open("http://localhost:9999/");

        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").val("+79123456789");
        $("[data-test-id=agreement]").click();
        $(byText("Продолжить")).click();
        $("[data-test-id='order-success']")
                .shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }
}
