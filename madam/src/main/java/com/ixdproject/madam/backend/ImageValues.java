package com.ixdproject.madam.backend;

import java.util.HashMap;

public class ImageValues {
    // 0 - 1024
    private HashMap<ImageManager.Img, MufChiValues> imageValues = new HashMap<>();

    public ImageValues() {
        imageValues.put(ImageManager.Img.CHIHUAHUA1, new MufChiValues(23, 42, 12));
        imageValues.put(ImageManager.Img.CHIHUAHUA2, new MufChiValues(432, 114, 42));
        imageValues.put(ImageManager.Img.CHIHUAHUA3, new MufChiValues(643, 34, 12));
        imageValues.put(ImageManager.Img.CHIHUAHUA4, new MufChiValues(91, 426, 512));
        imageValues.put(ImageManager.Img.CHIHUAHUA5, new MufChiValues(232, 132, 102));
        imageValues.put(ImageManager.Img.MUFFIN1, new MufChiValues(21, 12, 12));
        imageValues.put(ImageManager.Img.MUFFIN2, new MufChiValues(21, 12, 12));
        imageValues.put(ImageManager.Img.MUFFIN3, new MufChiValues(21, 12, 12));
        imageValues.put(ImageManager.Img.MUFFIN4, new MufChiValues(21, 12, 12));
        imageValues.put(ImageManager.Img.MUFFIN5, new MufChiValues(21, 12, 12));
    }

    public MufChiValues getImageValues(ImageManager.Img img) {
        return imageValues.get(img);
    }
}