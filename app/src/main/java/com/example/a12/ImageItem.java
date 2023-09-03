package com.example.a12;

public class ImageItem {
    private int imageResource; // ID resursa slike
    private String title;      // Naslov slike

    public ImageItem(int imageResource, String title) {
        this.imageResource = imageResource;
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }
}
