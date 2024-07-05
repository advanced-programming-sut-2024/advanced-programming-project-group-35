package com.example.view.menuControllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class LoadingPage {
    private final String[] backgroundImages = {
            "/images/loadings/loading1.jpg",
            "/images/loadings/loading2.jpg",
            "/images/loadings/loading3.jpg",
            "/images/loadings/loading4.jpg",
            "/images/loadings/loading5.jpg",
            "/images/loadings/loading6.jpg",
            "/images/loadings/loading7.jpg",
            "/images/loadings/loading8.jpg",
            "/images/loadings/loading9.jpg",
            "/images/loadings/loading10.jpg",
            "/images/loadings/loading11.jpg",
            "/images/loadings/loading12.jpg",
    };
    public ImageView backgroundImageView;

    public void initialize() {
        String randomBackgroundImage = backgroundImages[new Random().nextInt(backgroundImages.length)];
        Image image = new Image(getClass().getResourceAsStream(randomBackgroundImage));
        backgroundImageView.setImage(image);
    }
}
