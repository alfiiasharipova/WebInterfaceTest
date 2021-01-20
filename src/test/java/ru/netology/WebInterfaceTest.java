package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebInterfaceTest {

    @Test
    void shouldSubmitRequest()
    {
        open("http://localhost:9999");
        SelenideElement form=$(".form");
        form.$("[name=name]").setValue("Василий");
        form.$("[name=phone]").setValue("+79270000000");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $(".paragraph").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSubmitRequestWithInvalidName()
    {
        open("http://localhost:9999");
        SelenideElement form=$(".form");
        form.$("[name=name]").setValue("Vasiliy");
        form.$(".button").click();
        $("[data-test-id=name]").shouldHave(cssClass("input_invalid"));
    }

    @Test
    void shouldNotSubmitRequestWithEmptyName()
    {
        open("http://localhost:9999");
        SelenideElement form=$(".form");
        form.$(".button").click();
        $("[data-test-id=name]").shouldHave(cssClass("input_invalid"));
    }

    @Test
    void shouldNotSubmitRequestWithInvalidPhone()
    {
        open("http://localhost:9999");
        SelenideElement form=$(".form");
        form.$("[name=name]").setValue("Василий");
        form.$("[name=phone]").setValue("+79270");
        form.$(".button").click();
        $("[data-test-id=phone]").shouldHave(cssClass("input_invalid"));
    }

    @Test
    void shouldNotSubmitRequestWithEmptyPhone()
    {
        open("http://localhost:9999");
        SelenideElement form=$(".form");
        form.$("[name=name]").setValue("Василий");
        form.$(".button").click();
        $("[data-test-id=phone]").shouldHave(cssClass("input_invalid"));
    }

    @Test
    void shouldNotSubmitRequestWithoutCheckbox()
    {
        open("http://localhost:9999");
        SelenideElement form=$(".form");
        form.$("[name=name]").setValue("Василий");
        form.$("[name=phone]").setValue("+79270000000");
        form.$(".button").click();
        $(".checkbox").shouldHave(cssClass("input_invalid"));
    }
}
