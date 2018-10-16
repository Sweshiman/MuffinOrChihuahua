package com.ixdproject.madam.backend;

import com.vaadin.flow.component.html.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ixdproject.madam.backend.Tag.CHIHUAHUA;
import static com.ixdproject.madam.backend.Tag.MUFFIN;

public class ImageManager {

    private HashMap<Img, Boolean> imgMap = new HashMap<>();
    private Img currentImg = Img.CHIHUAHUA1;

    public ImageManager() {
        imgMap.put(Img.MUFFIN1, false);
        imgMap.put(Img.MUFFIN2, false);
        imgMap.put(Img.MUFFIN3, false);
        imgMap.put(Img.MUFFIN4, false);
        imgMap.put(Img.MUFFIN5, false);
        imgMap.put(Img.CHIHUAHUA1, false);
        imgMap.put(Img.CHIHUAHUA2, false);
        imgMap.put(Img.CHIHUAHUA3, false);
        imgMap.put(Img.CHIHUAHUA4, false);
        imgMap.put(Img.CHIHUAHUA5, false);
    }

    public enum Img {
        CHIHUAHUA1,
        CHIHUAHUA2,
        CHIHUAHUA3,
        CHIHUAHUA4,
        CHIHUAHUA5,
        MUFFIN1,
        MUFFIN2,
        MUFFIN3,
        MUFFIN4,
        MUFFIN5
    }

    public void setIsCorrect(Img img, Boolean isCorrect) {
        imgMap.put(img, isCorrect);
    }

    public Boolean isCorrect(Img img) {
        return imgMap.get(img);
    }

    public Image getCurrentImage() {
        return getImage(currentImg);
    }

    public Img getCurrentImg() {
        return currentImg;
    }

    public boolean isLastImage() {
        return currentImg == Img.MUFFIN5;
    }

    public int getFinalScore() {
        int score = 0;
        for (Map.Entry<Img, Boolean> entry : imgMap.entrySet()) {
            if (entry.getValue()) {
                score += 1;
            }
        }
        return score;
    }

    public boolean isCorrectGuess(String tag) {
        switch (tag) {
            case CHIHUAHUA:
                return (currentImg == Img.CHIHUAHUA1 ||
                        currentImg == Img.CHIHUAHUA2 ||
                        currentImg == Img.CHIHUAHUA3 ||
                        currentImg == Img.CHIHUAHUA4 ||
                        currentImg == Img.CHIHUAHUA5);
            case MUFFIN:
                return (currentImg == Img.MUFFIN1 ||
                        currentImg == Img.MUFFIN2 ||
                        currentImg == Img.MUFFIN3 ||
                        currentImg == Img.MUFFIN4 ||
                        currentImg == Img.MUFFIN5);
            default:
                return false;
        }
    }

    public String getPreviousImageSrc() {
        switch (currentImg) {
            case CHIHUAHUA1:
                currentImg = Img.MUFFIN5;
                return "frontend/img/muffin2.png";
            case CHIHUAHUA2:
                currentImg = Img.CHIHUAHUA1;
                return "frontend/img/chi1.jpg";
            case CHIHUAHUA3:
                currentImg = Img.CHIHUAHUA2;
                return "frontend/img/chi2.jpg";
            case CHIHUAHUA4:
                currentImg = Img.CHIHUAHUA3;
                return "frontend/img/chi3.jpg";
            case CHIHUAHUA5:
                currentImg = Img.CHIHUAHUA4;
                return "frontend/img/chi4.jpg";
            case MUFFIN1:
                currentImg = Img.CHIHUAHUA5;
                return "frontend/img/chi5.jpg";
            case MUFFIN2:
                currentImg = Img.MUFFIN1;
                return "frontend/img/muffin1.jpg";
            case MUFFIN3:
                currentImg = Img.MUFFIN2;
                return "frontend/img/muffin2.png";
            case MUFFIN4:
                currentImg = Img.MUFFIN3;
                return "frontend/img/muffin2.png";
            case MUFFIN5:
                currentImg = Img.MUFFIN4;
                return "frontend/img/muffin2.png";
            default:
                currentImg = Img.CHIHUAHUA1;
                return "frontend/img/chi1.jpg";
        }
    }

    public String getNextImageSrc() {
        switch (currentImg) {
            case CHIHUAHUA1:
                currentImg = Img.CHIHUAHUA2;
                return "frontend/img/chi2.jpg";
            case CHIHUAHUA2:
                currentImg = Img.CHIHUAHUA3;
                return "frontend/img/chi3.jpg";
            case CHIHUAHUA3:
                currentImg = Img.CHIHUAHUA4;
                return "frontend/img/chi4.jpg";
            case CHIHUAHUA4:
                currentImg = Img.CHIHUAHUA5;
                return "frontend/img/chi5.jpg";
            case CHIHUAHUA5:
                currentImg = Img.MUFFIN1;
                return "frontend/img/muffin1.jpg";
            case MUFFIN1:
                currentImg = Img.MUFFIN2;
                return "frontend/img/muffin2.png";
            case MUFFIN2:
                currentImg = Img.MUFFIN3;
                return "frontend/img/muffin2.png";
            case MUFFIN3:
                currentImg = Img.MUFFIN4;
                return "frontend/img/muffin2.png";
            case MUFFIN4:
                currentImg = Img.MUFFIN5;
                return "frontend/img/muffin2.png";
            case MUFFIN5:
                currentImg = Img.CHIHUAHUA1;
                return "frontend/img/chi1.jpg";
            default:
                currentImg = Img.CHIHUAHUA1;
                return "frontend/img/chi1.jpg";
        }
    }

    public List<Image> getAllImages() {
        List<Image> images = new ArrayList<>();
        for (Img img : Img.values()) {
            images.add(getImage(img));
        }
        return images;
    }

    private Image getImage(Img img) {
        switch (img) {
            case CHIHUAHUA1:
                return new Image("frontend/img/chi1.jpg", "");
            case CHIHUAHUA2:
                return new Image("frontend/img/chi2.jpg", "");
            case CHIHUAHUA3:
                return new Image("frontend/img/chi3.jpg", "");
            case CHIHUAHUA4:
                return new Image("frontend/img/chi4.jpg", "");
            case CHIHUAHUA5:
                return new Image("frontend/img/chi5.jpg", "");
            case MUFFIN1:
                return new Image("frontend/img/muffin1.jpg", "");
            case MUFFIN2:
                return new Image("frontend/img/muffin2.png", "");
            case MUFFIN3:
                return new Image("frontend/img/muffin2.png", "");
            case MUFFIN4:
                return new Image("frontend/img/muffin2.png", "");
            case MUFFIN5:
                return new Image("frontend/img/muffin2.png", "");
            default:
                return new Image();
        }
    }

}
