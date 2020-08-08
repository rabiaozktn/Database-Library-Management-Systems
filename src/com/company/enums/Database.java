package com.company.enums;

public enum Database {

    DIL(1),
    KATEGORI(2),
    YAYIEVI(3),
    KITAP(3);

    private int value;

    Database(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
