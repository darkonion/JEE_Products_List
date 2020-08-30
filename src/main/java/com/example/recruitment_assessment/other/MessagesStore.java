package com.example.recruitment_assessment.other;

import lombok.Getter;

@Getter
public enum MessagesStore {
    UP_SUCCESS("Przesunięto do góry"),
    UP_FAIL("Wybrany wiersz jest już na górze"),
    DOWN_SUCCESS("Przesunięto do dołu"),
    DOWN_FAIL("Wybrany wiersz jest już na dole"),
    SUCCESS("success"),
    UNKNOWN_ERROR("Coś poszło nie tak, spróbuj ponownie"),
    DELETE_CONF("Produkt został usunięty z listy"),
    FAIL("fail");

    private final String message;

    MessagesStore(String message) {
        this.message = message;
    }
}
