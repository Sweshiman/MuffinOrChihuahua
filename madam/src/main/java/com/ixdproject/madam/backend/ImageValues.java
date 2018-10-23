package com.ixdproject.madam.backend;

import java.util.HashMap;

public class ImageValues {
    private HashMap<ImageManager.Img, MufChiValues> imageValues = new HashMap<>();

    public ImageValues() {
        imageValues.put(ImageManager.Img.TUNING_GAME_1, new MufChiValues(3, 8, 9));
        imageValues.put(ImageManager.Img.TUNING_GAME_2, new MufChiValues(2, 1, 9));
        imageValues.put(ImageManager.Img.TUNING_GAME_3, new MufChiValues(8, 1, 9));
        imageValues.put(ImageManager.Img.TUNING_GAME_4, new MufChiValues(3, 9, 2));
        imageValues.put(ImageManager.Img.TUNING_GAME_5, new MufChiValues(8, 7, 9));
        imageValues.put(ImageManager.Img.TUNING_GAME_6, new MufChiValues(5, 2, 3));
        imageValues.put(ImageManager.Img.TUNING_GAME_7, new MufChiValues(9, 4, 3));
        imageValues.put(ImageManager.Img.TUNING_GAME_8, new MufChiValues(5, 3, 4));
    }

    public MufChiValues getImageValues(ImageManager.Img img) {
        return imageValues.get(img);
    }
}