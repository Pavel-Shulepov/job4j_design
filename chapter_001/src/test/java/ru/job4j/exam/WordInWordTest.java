package ru.job4j.exam;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class WordInWordTest {

    @Test
    public void whenWordInWordThenTrue() {
        String origin = "Привет";
        String sub = "иве";

        assertThat(WordInWord.contains(origin, sub), is(true));
    }

    @Test
    public void whenNotWordInWordThenFalse() {
        String origin = "Привет";
        String sub = "ива";

        assertThat(WordInWord.contains(origin, sub), is(false));
    }

    @Test
    public void whenLargeTextThenSubstringFind() {
        String origin = "Работа – это поиск решения задач. "
                + "Искать в том, на что способен влиять и можешь контролировать – это здраво, "
                + "хотя может оказаться скучнее скучного и монотоннее монотонного. Но, почувствуйте, "
                + "«пассивно выигранное очко» –  не путь успешных. Лезть туда, куда никто не решился, "
                + "или задумываться о том, на чем остальные давно поставили крест, точно не сделает тебя слабее. "
                + "Не все могут быть красивыми, кому-то надо вырасти умным. Конечно, все это находится вне зоны комфорта, "
                + "однако страх не должен определять будущее. Ну, а чтобы не бояться, нужно просто верить в процесс. "
                + "Логика проста: без веры нет страсти, а работа без страсти превращается в халтуру. "
                + "Заметьте, мы либо тратимся на то, во что верим, либо мучительно ожидаем момент, когда возникнет новый повод поверить.";
        String sub = "или задумываться о том, на чем остальные давно поставили крест, точно не сделает тебя слабее.";
        assertThat(WordInWord.contains(origin, sub), is(true));
    }

}
