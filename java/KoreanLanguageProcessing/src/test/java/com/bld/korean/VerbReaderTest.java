package com.bld.korean;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class VerbReaderTest {

    private VerbReader verbReader;

    @Before
    public void setup() {
        verbReader = new VerbReader();
    }

    @Test
    public void readAll() throws IOException {
        List<Verb> verbs = verbReader.readAll();

        assertThat(verbs.size(), is(6));

        verbs.stream()
                .map(Verb::getKorean)
                .forEach(verb -> {
                    assertThat(verb.charAt(verb.length() -1), is('다'));
                });
    }
}