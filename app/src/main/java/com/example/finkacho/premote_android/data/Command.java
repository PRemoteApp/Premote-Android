package com.example.finkacho.premote_android.data;

import com.google.gson.annotations.SerializedName;

public class Command {

    @SerializedName("title")
    private String title;

    @SerializedName("descripition")
    private String descripition;

    public Command(String title, String descripition) {
        this.title = title;
        this.descripition = descripition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripition() {
        return descripition;
    }

    public void setDescripition(String descripition) {
        this.descripition = descripition;
    }
}
