package com.bld.korean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JamoTest {

    @Test
    public void test_가_jamo_from_character() {

        Jamo jamo = new Jamo('가');

        assertThat(jamo.getCharacter(), is('가'));
        assertThat(jamo.getLead(), is(1));
        assertThat(jamo.getVowel(), is(1));
        assertThat(jamo.getTail(), is(0));
    }

    @Test
    public void test_가_jamo_from_lead_vowel_consonant() {

        Jamo jamo = new Jamo(1,1,0);

        assertThat(jamo.getCharacter(), is('가'));
        assertThat(jamo.getLead(), is(1));
        assertThat(jamo.getVowel(), is(1));
        assertThat(jamo.getTail(), is(0));
    }

    @Test
    public void test_한_jamo_from_character() {

        Jamo jamo = new Jamo('한');

        assertThat(jamo.getCharacter(), is('한'));
        assertThat(jamo.getLead(), is(19));
        assertThat(jamo.getVowel(), is(1));
        assertThat(jamo.getTail(), is(4));
    }

    @Test
    public void test_한_jamo_from_lead_vowel_consonant() {

        Jamo jamo = new Jamo(19,1,4);

        assertThat(jamo.getCharacter(), is('한'));
        assertThat(jamo.getLead(), is(19));
        assertThat(jamo.getVowel(), is(1));
        assertThat(jamo.getTail(), is(4));
    }
}