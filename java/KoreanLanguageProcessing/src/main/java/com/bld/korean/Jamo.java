package com.bld.korean;

public class Jamo {

    private char character;
    private int lead;
    private int vowel;
    private int tail;

    public Jamo(char character) {
        this.character = character;

        int codePoint = (int)character;
        tail = ((codePoint - 44032) % 28);
        vowel = 1 + ((codePoint - 44032 - tail) % 588) / 28;
        lead = 1 + ((codePoint - 44032) / 588);
    }

    public Jamo(int lead, int vowel, int tail) {
        this.lead = lead;
        this.vowel = vowel;
        this.tail = tail;
        this.character = (char) (tail + ((vowel - 1) * 28) + ((lead - 1) * 588) + 44032);
    }

    public char getCharacter() {
        return character;
    }

    public int getLead() {
        return lead;
    }

    public int getVowel() {
        return vowel;
    }

    public int getTail() {
        return tail;
    }

    public boolean has받침() {
        return tail != 0;
    }

}
