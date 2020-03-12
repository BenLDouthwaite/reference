package com.bld.korean;

import java.util.HashMap;
import java.util.Map;

public enum Vowel {

    ᅡ(1),
    ᅢ(2),
    ᅣ(3),
    ᅤ(4),
    ᅥ(5),
    ᅦ(6),
    ᅧ(7),
    ᅩ(9),
    ᅪ(10),
    ᅫ(11),
    ᅬ(12),
    ᅮ(14),
    ᅯ(15),
    ᅳ(19),
    ᅵ(21);

    private static Map<Integer, Vowel> vowels = new HashMap<>();
    static {
        for(Vowel vowel: values()){
            vowels.put(vowel.getValue(), vowel);
        }
    }

    private int value;

    Vowel(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Vowel getVowelFromValue(int value) {
        return vowels.get(value);
    }
}
