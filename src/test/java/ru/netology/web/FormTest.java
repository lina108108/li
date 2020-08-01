package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.cssSelector;

public class FormTest {

    @Test
    void shouldSubmitRequestIfFullyValid() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79111985678");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestIfNameInCaps() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("ЛИАНА ВЕЛЬДЯЕВА");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79064345456");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestIfNameInSmallLetters() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("лиана вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79110987654");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSubmitRequestIfOnlyName() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79110987654");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitRequestIfNameAndSurnameOneLetter() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("л В");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79110987654");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }


    @Test
    void shouldNotSubmitRequestIfNameAndSurname40Letters() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Оооооооооооооооооооооооооооооооооооооооо Ннннннннннннннннннннннннннннннннннннннннннннн");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+78097547699");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitRequestIfNameWithHyphen() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Маша Петрова-Иванова");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+78097547699");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSubmitRequestIfNameInLatinLetters() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Liana Veldiaeva");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+78097547699");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitIfFormIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitIfNameIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79111234567");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitIfPhoneIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }


    @Test
    void shouldNotSubmitIfAgreementIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+77777777777");
        form.$(cssSelector("[role=button]")).click();
        $(".input_invalid [role=presentation]").shouldHave(Condition.text("Я соглашаюсь"));
    }

    @Test
    void shouldNotSubmitIfNameInvalidSymbols() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("@ $");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79111234567");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneIs1Number() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("7");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneIs10Numbers() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("89111111111");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneWithoutPlus() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("79111111111");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneIs12Numbers() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+7911111789059");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneIsLetters() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+лаоврлолаов");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneIsSymbols() {
        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+@#$%^&*(&^%");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    
}


