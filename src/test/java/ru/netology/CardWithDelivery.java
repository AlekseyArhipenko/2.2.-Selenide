package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardWithDelivery {

    public LocalDate dateNow = LocalDate.now();
    public String setDate = dateNow.plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Test
    void shouldCardApplication() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1600x900";
        open("http://localhost:9999");
        $("[data-test-id='city'] input").val("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(setDate);
        $("[data-test-id='name'] input").val("Иванов Иван");
        $("[data-test-id='phone'] input").val("+71232135588");
        $("[data-test-id='agreement']>span").click();
        $("[role='button'] span [class='button__text']").click();
        $(withText("Успешно!")).should(appear, Duration.ofSeconds(15));
    }
}
