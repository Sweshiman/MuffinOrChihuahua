package com.ixdproject.madam.backend;

import java.util.HashMap;

public class ImageValues {
    // 0 - 10
    private HashMap<ImageManager.Img, MufChiValues> imageValues = new HashMap<>();

    public ImageValues() {
        imageValues.put(ImageManager.Img.CHIHUAHUA1, new MufChiValues(3, 8, 9));
        imageValues.put(ImageManager.Img.CHIHUAHUA2, new MufChiValues(8, 1, 9));
        imageValues.put(ImageManager.Img.CHIHUAHUA3, new MufChiValues(8, 7, 9));
        imageValues.put(ImageManager.Img.CHIHUAHUA4, new MufChiValues(9, 4, 3));
        imageValues.put(ImageManager.Img.MUFFIN1, new MufChiValues(2, 1, 9));
        imageValues.put(ImageManager.Img.MUFFIN2, new MufChiValues(3, 9, 2));
        imageValues.put(ImageManager.Img.MUFFIN3, new MufChiValues(5, 2, 3));
        imageValues.put(ImageManager.Img.MUFFIN4, new MufChiValues(5, 3, 4));
    }

    public MufChiValues getImageValues(ImageManager.Img img) {
        return imageValues.get(img);
    }
}