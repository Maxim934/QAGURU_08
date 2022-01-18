package maxim.kim;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParamTest {
    @DisplayName("Netflix Test")
    @Tag("blocker")
    @ValueSource(strings = {"Русский", "English"})
    @ParameterizedTest(name = "Netflix language picker test {0}")
    public void checkHeaderValueSource(String languagePicker) {
        open("https://www.netflix.com/kz-ru/");
        $("#lang-switcher-header-select").selectOption(languagePicker);
        $("h1[data-uia=\"hero-title\"]").shouldHave(
                Condition.text("Фильмы, сериалы и" +
                        " многое другое без ограничений."));
    }

    @DisplayName("Netflix test")
    @Tag("blocker")
    @CsvSource(value = {
            "Русский| Фильмы, сериалы и многое другое без ограничений.",
            "English| Unlimited movies, TV shows, and more."
    },
            delimiter = '|')
    @ParameterizedTest(name = "Netflix language pick {0}")
    public void checkHeaderCsvSource(String language, String expectedResult) {
        open("https://www.netflix.com/kz-ru/");
        $("#lang-switcher-header-select").selectOption(language);
        $("h1[data-uia=\"hero-title\"]").shouldHave(
                Condition.text(expectedResult));
    }
}
