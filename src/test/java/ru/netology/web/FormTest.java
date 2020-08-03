package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.cssSelector;

    public class FormTest {
        SelenideElement form;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        form = $("[action]");
    }


    @Test

    void shouldSubmitRequestIfFullyValid() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79111985678");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestIfNameInCaps() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("ЛИАНА ВЕЛЬДЯЕВА");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79064345456");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitRequestIfNameInSmallLetters() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("лиана вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79110987654");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitRequestIfOnlyName() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79110987654");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitRequestIfNameAndSurnameOneLetter() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("л В");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79110987654");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitRequestIfNameAndSurname40Letters() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Оооооооооооооооооооооооооооооооооооооооо Ннннннннннннннннннннннннннннннннннннннннннннн");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+78097547699");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitRequestIfNameWithHyphen() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Маша Петрова-Иванова");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+78097547699");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSubmitRequestIfNameInLatinLetters() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Liana Veldiaeva");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+78097547699");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitIfFormIsEmpty() {
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitIfNameIsEmpty() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79111234567");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSubmitIfPhoneIsEmpty() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }


    @Test
    void shouldNotSubmitIfAgreementIsEmpty() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+77777777777");
        form.$(cssSelector("[role=button]")).click();
        $(".input_invalid [role=presentation]").shouldHave(Condition.text("Я соглашаюсь"));
    }

    @Test
    void shouldNotSubmitIfNameInvalidSymbols() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("@ $");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+79111234567");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneIs1Number() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("7");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneIs10Numbers() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("89111111111");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneWithoutPlus() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("79111111111");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneIs12Numbers() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+7911111789059");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneIsLetters() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+лаоврлолаов");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSubmitRequestIfPhoneIsSymbols() {
        form.$(cssSelector("[data-test-id=name] input")).sendKeys("Лиана Вельдяева");
        form.$(cssSelector("[data-test-id=phone] input")).sendKeys("+@#$%^&*(&^%");
        form.$(cssSelector("[data-test-id=agreement]")).click();
        form.$(cssSelector("[role=button]")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}


