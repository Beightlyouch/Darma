package com.blindlyouch.darma;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Darma extends RealmObject {

    @PrimaryKey
    int darma_id = 0;
    String darma_wish = "";
    int darma_eye_num = 0;
    boolean isClicked = false;

    public int getDarma_id() {
        return darma_id;
    }

    public void setDarma_id(int darma_id) {
        this.darma_id = darma_id;
    }

    public String getDarma_wish() {
        return darma_wish;
    }

    public void setDarma_wish(String darma_wish) {
        this.darma_wish = darma_wish;
    }

    public int getDarma_eye_num() {
        return darma_eye_num;
    }

    public void setDarma_eye(int darma_eye) { this.darma_eye_num = darma_eye; }

    public void setDarma_eye_num(int darma_eye_num) { this.darma_eye_num = darma_eye_num; }

    public boolean isClicked() { return isClicked; }

    public void setClicked(boolean clicked) { isClicked = clicked; }
}
