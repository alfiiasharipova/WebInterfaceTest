package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebInterfaceTest {

    @BeforeEach
    void serUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        SelenideElement form = $(".form");
        form.$("[name=name]").setValue("Кашин Василий");
        form.$("[name=phone]").setValue("+79270000000");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $(".paragraph")
                .shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSubmitRequestWithInvalidName() {
        SelenideElement form = $(".form");
        form.$("[name=name]").setValue("Vasiliy");
        form.$("[name=phone]").setValue("+79270000000");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $("[data-test-id=name] .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitRequestWithEmptyName() {
        SelenideElement form = $(".form");
        form.$("[name=phone]").setValue("+79270000000");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitRequestWithInvalidPhone() {
        SelenideElement form = $(".form");
        form.$("[name=name]").setValue("Кашин Василий");
        form.$("[name=phone]").setValue("+79270");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $("[data-test-id=phone] .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestWithEmptyPhone() {
        SelenideElement form = $(".form");
        form.$("[name=name]").setValue("Кашин Василий");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitRequestWithoutCheckbox() {
        SelenideElement form = $(".form");
        form.$("[name=name]").setValue("Кашин Василий");
        form.$("[name=phone]").setValue("+79270000000");
        form.$(".button").click();
        $(".checkbox").shouldHave(cssClass("input_invalid"));
    }
}
