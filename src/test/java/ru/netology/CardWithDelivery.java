package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardWithDelivery {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldCardApplication() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1600x900";
        String planningDate = generateDate(4);
        open("http://localhost:9999");
        $("[data-test-id='city'] input").val("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(planningDate);
        $("[data-test-id='name'] input").val("Иванов Иван");
        $("[data-test-id='phone'] input").val("+71232135588");
        $("[data-test-id='agreement']>span").click();
        $("[role='button'] span [class='button__text']").click();
        $(withText("Успешно!")).should(appear, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }
}
