package com.bld.korean;

import com.bld.korean.VerbConjugator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class VerbConjugatorPastTenseTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { "사다", "샀어요"}, // to buy
            { "오다", "왔어요"}, // to come
            { "적다", "적었어요"}, // to write
            { "하다", "했어요"}, // to do
            { "있다", "있었어요"}, // to be
            { "보다", "봤어요"}, // to see
        });
    }

    private String infinitiveVerb;
    private String expectedConjugatedVerb;

    public VerbConjugatorPastTenseTest(String infinitiveVerb, String conjugationVerb) {
        this.infinitiveVerb = infinitiveVerb;
        this.expectedConjugatedVerb = conjugationVerb;
    }

    private VerbConjugator verbConjugator;

    @Before
    public void setup() {
        verbConjugator = new VerbConjugator();
    }

    @Test
    public void test() {
        String actualConjugatedVeb = verbConjugator.conjugatePastTense(infinitiveVerb);
        assertThat(actualConjugatedVeb, is(expectedConjugatedVerb));
    }

}