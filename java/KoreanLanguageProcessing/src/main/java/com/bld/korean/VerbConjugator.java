package com.bld.korean;

public class VerbConjugator {

    public String conjugatePresentTense(String infinitiveVerb) {
        String verbStem = getVerbStem(infinitiveVerb);
        String conjugationEnding = getPresentTenseConjugationEnding(verbStem);
        return conjugateVerbStemAndVerbEnding(verbStem, conjugationEnding);
    }

    public String conjugatePastTense(String infinitiveVerb) {
        String verbStem = getVerbStem(infinitiveVerb);
        String conjugationEnding = getPastTenseConjugationEnding(verbStem);
        return conjugateVerbStemAndVerbEnding(verbStem, conjugationEnding);
    }

    private String getVerbStem(String infinitiveVerb) {
        return infinitiveVerb.substring(0, infinitiveVerb.length() - 1);
    }

    private String getPresentTenseConjugationEnding(String verbStem) {
        char finalChar = getFinalCharacter(verbStem);
        if (finalChar == '하') {
            return "여요";
        }

        Vowel finalVowel = getVerbStemFinalVowel(verbStem);
        if (finalVowel == Vowel.ᅡ || finalVowel == Vowel.ᅩ){
            return "아요";
        } else {
            return "어요";
        }
    }

    private String getPastTenseConjugationEnding(String verbStem) {
        char finalChar = getFinalCharacter(verbStem);
        if (finalChar == '하') {
            return "였어요";
        }

        Vowel finalVowel = getVerbStemFinalVowel(verbStem);
        if (finalVowel == Vowel.ᅡ || finalVowel == Vowel.ᅩ){
            return "았어요";
        } else {
            return "었어요";
        }
    }

    private char getFinalCharacter(String verbStem) {
        return verbStem.charAt(verbStem.length() - 1);
    }

    private Vowel getVerbStemFinalVowel(String verbStem) {
        Jamo verbStemFinalJamo = getVerbStemFinalJamo(verbStem);
        return Vowel.getVowelFromValue(verbStemFinalJamo.getVowel());
    }

    private Jamo getVerbStemFinalJamo(String verbStem) {
        char finalChar = getFinalCharacter(verbStem);
        return new Jamo(finalChar);
    }

    private String conjugateVerbStemAndVerbEnding(String verbStem, String conjugationEnding) {

        Jamo verbStemFinalJamo = getVerbStemFinalJamo(verbStem);

        if (verbStemFinalJamo.has받침()) {
            return verbStem + conjugationEnding;
        }

        return applyContractionRules(verbStem, conjugationEnding);
    }

    private String applyContractionRules(String verbStem, String conjugationEnding) {

        Jamo conjugationEndingFirstJamo = new Jamo(conjugationEnding.charAt(0));
        Vowel endingVowel = Vowel.getVowelFromValue(conjugationEndingFirstJamo.getVowel());

        switch (endingVowel) {
            case ᅡ: return applyᅡContractionRules(verbStem, conjugationEnding);
            case ᅥ: return applyᅥContractionRules(verbStem, conjugationEnding);
            case ᅧ: return applyᅧContractionRules(verbStem, conjugationEnding);
            default: return verbStem + conjugationEnding;
        }
    }

    private String applyᅡContractionRules(String verbStem, String conjugationEnding) {
        Vowel stemFinalVowel = getVerbStemFinalVowel(verbStem);
        switch (stemFinalVowel){
            case ᅡ: return applyVerbStemVowelChange(verbStem, conjugationEnding, Vowel.ᅡ);
            case ᅩ: return applyVerbStemVowelChange(verbStem, conjugationEnding, Vowel.ᅪ);
            default: return verbStem + conjugationEnding;
        }
    }

    private String applyᅥContractionRules(String verbStem, String conjugationEnding) {
        Vowel stemFinalVowel = getVerbStemFinalVowel(verbStem);
        switch (stemFinalVowel) {
            case ᅥ: return verbStem + conjugationEnding.substring(1);
            case ᅮ: return applyVerbStemVowelChange(verbStem, conjugationEnding, Vowel.ᅯ);
            case ᅳ: return applyVerbStemVowelChange(verbStem, conjugationEnding, Vowel.ᅥ);
            case ᅵ: return applyVerbStemVowelChange(verbStem, conjugationEnding, Vowel.ᅧ);
            case ᅢ: return applyVerbStemVowelChange(verbStem, conjugationEnding, Vowel.ᅢ);
            case ᅦ: return applyVerbStemVowelChange(verbStem, conjugationEnding, Vowel.ᅦ);
            case ᅬ: return applyVerbStemVowelChange(verbStem, conjugationEnding, Vowel.ᅫ);
            default: return verbStem + conjugationEnding;
        }
    }

    private String applyᅧContractionRules(String verbStem, String conjugationEnding) {

        Jamo verbStemFinalJamo = getVerbStemFinalJamo(verbStem);
        Vowel stemFinalVowel = Vowel.getVowelFromValue(verbStemFinalJamo.getVowel());

        if (verbStemFinalJamo.getLead() == 19 && stemFinalVowel == Vowel.ᅡ) {
            return applyVerbStemVowelChange(verbStem, conjugationEnding, Vowel.ᅢ);
        }
        return verbStem + conjugationEnding;
    }

    private String applyVerbStemVowelChange(String verbStem, String conjugationEnding, Vowel newVerbStemFinalVowel) {

        Jamo verbStemFinalJamo = getVerbStemFinalJamo(verbStem);

        int tailValue = new Jamo(conjugationEnding.charAt(0)).getTail();
        Jamo newFinalJamo = new Jamo(verbStemFinalJamo.getLead(), newVerbStemFinalVowel.getValue(), tailValue);
        String newVerbStem = verbStem.substring(0, verbStem.length() - 1) + newFinalJamo.getCharacter();
        return newVerbStem + conjugationEnding.substring(1);
    }

}
