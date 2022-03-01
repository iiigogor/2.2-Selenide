package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
//import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
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
        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
    }
    @Test
    void shouldTest() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Йошкар-Ола");
        $("[data-test-id=date] input").doubleClick().setValue(String.valueOf(setDateCurrent));;
        $("[data-test-id=name] input").setValue("Петров-Водкин Кузьма");
        $("[data-test-id=phone] input").setValue("+72345678901");
        $(".checkbox__box").click();
        $(withText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + setDateCurrent));
    }
    @Test
    void withoutAgreementTest(){
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Петропавловск-Камчатский");
        $("[data-test-id=date] input").doubleClick().setValue(String.valueOf(setDateCurrent));;
        $("[data-test-id=name] input").setValue("Петров-Водкин Кузьма");
        $("[data-test-id=phone] input").setValue("+72345678901");
        //$(".checkbox__box").click();
        $(withText("Забронировать")).click();
        $(".checkbox__text").shouldHave(text("Я соглашаюсь с условиями обработки  и использования моих персональных данных"));
    }
}
