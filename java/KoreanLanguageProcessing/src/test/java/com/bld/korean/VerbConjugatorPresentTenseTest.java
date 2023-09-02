package com.bld.korean;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class VerbConjugatorPresentTenseTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "가다", "가요"}, // to go
                { "먹다", "먹어요"}, // to eat
                { "자다", "자요"}, // to sleep
                { "서다", "서요"}, // to stop
                { "오다", "와요"}, // to come
                { "주다", "줘요"}, // to give
                { "쓰다", "써요"}, // to write
                { "가지다", "가져요"}, // to have
                { "꺼내다", "꺼내요" }, // to take out
                { "세다", "세요" }, // to count
                { "되다", "돼요" }, // to become
                { "하다", "해요" } // to do
        });
    }

    private String infinitiveVerb;
    private String expectedConjugatedVerb;

    public VerbConjugatorPresentTenseTest(String infinitiveVerb, String conjugationVerb) {
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
        String actualConjugatedVeb = verbConjugator.conjugatePresentTense(infinitiveVerb);
        assertThat(actualConjugatedVeb, is(expectedConjugatedVerb));
    }

}