package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

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
}
