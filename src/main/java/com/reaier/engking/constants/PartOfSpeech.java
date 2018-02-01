package com.reaier.engking.constants;

public enum PartOfSpeech {
    PREP("preposition"),
    PRON("pronoun"),
    N("noun"),
    V("verb"),
    CONJ("conjunction"),
    S("subject"),
    sc("sc"),
    O("o"),
    OC("oc"),
    VI("intransitive verb"),
    Vt("transitive verb"),
    AUX("auxiliary"),
    ADJ("adjective"),
    ADV("adjective"),
    ART("article"),
    NUM("numeral"),
    INT("interjection"),
    U("uncountable noun"),
    C("countable noun"),
    PL("plural");

    private String type;

    private PartOfSpeech(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
