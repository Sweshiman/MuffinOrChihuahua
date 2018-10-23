package com.ixdproject.madam.backend;

import com.vaadin.flow.component.html.Image;

import java.util.*;

public class ImageManager {

    private HashMap<Img, Boolean> tuningGameMap = new HashMap<>();
    private Img currentImg = Img.CHIHUAHUA1;
    private String currentGuessingGameImage;
    private List<String> guessingGameImages = new ArrayList<>();
    private Random random = new Random();
    private int guessingGameScore = 0;

    public ImageManager() {
        initTuningGame();
        initGuessingGameImages();
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
        tuningGameMap.put(img, isCorrect);
    }

    public Boolean isCorrect(Img img) {
        return tuningGameMap.get(img);
    }

    public Image getCurrentImage() {
        return getImage(currentImg);
    }

    public Image getFirstImage() {
        return getImage(Img.CHIHUAHUA1);
    }

    public Img getCurrentImg() {
        return currentImg;
    }

    public boolean isLastGuessingGameImage() {
        return guessingGameImages.isEmpty();
    }

    public int getFinalGuessingGameScore() {
        return guessingGameScore;
    }

    public boolean isCorrectGuessingGameGuess(String tag) {
        if (tag.equals(getCurrentGuessingGameImgTag())) {
            guessingGameScore += 1;
            return true;
        }
        return false;
    }

    private void initTuningGame() {
        tuningGameMap.put(Img.MUFFIN1, false);
        tuningGameMap.put(Img.MUFFIN2, false);
        tuningGameMap.put(Img.MUFFIN3, false);
        tuningGameMap.put(Img.MUFFIN4, false);
        tuningGameMap.put(Img.MUFFIN5, false);
        tuningGameMap.put(Img.CHIHUAHUA1, false);
        tuningGameMap.put(Img.CHIHUAHUA2, false);
        tuningGameMap.put(Img.CHIHUAHUA3, false);
        tuningGameMap.put(Img.CHIHUAHUA4, false);
        tuningGameMap.put(Img.CHIHUAHUA5, false);
    }

    private void initGuessingGameImages() {
        guessingGameImages.add("frontend/img/guessing_game/muffin1.png");
        guessingGameImages.add("frontend/img/guessing_game/muffin2.png");
        guessingGameImages.add("frontend/img/guessing_game/muffin3.png");
        guessingGameImages.add("frontend/img/guessing_game/muffin4.png");
        guessingGameImages.add("frontend/img/guessing_game/muffin5.png");
        guessingGameImages.add("frontend/img/guessing_game/chi1.jpg");
        guessingGameImages.add("frontend/img/guessing_game/chi2.jpg");
        guessingGameImages.add("frontend/img/guessing_game/chi3.jpg");
        guessingGameImages.add("frontend/img/guessing_game/chi4.jpg");
        guessingGameImages.add("frontend/img/guessing_game/chi5.jpg");
    }

    public String getRandomImage() {
        int randomIndex = Math.abs(random.nextInt()) % guessingGameImages.size();
        String s = guessingGameImages.get(randomIndex);
        guessingGameImages.remove(randomIndex);
        currentGuessingGameImage = s;
        return s;
    }

    public String getPreviousImageSrc() {
        switch (currentImg) {
            case CHIHUAHUA1:
                currentImg = Img.MUFFIN5;
                return "frontend/img/guessing_game/muffin5.png";
            case CHIHUAHUA2:
                currentImg = Img.CHIHUAHUA1;
                return "frontend/img/guessing_game/chi1.jpg";
            case CHIHUAHUA3:
                currentImg = Img.CHIHUAHUA2;
                return "frontend/img/guessing_game/chi2.jpg";
            case CHIHUAHUA4:
                currentImg = Img.CHIHUAHUA3;
                return "frontend/img/guessing_game/chi3.jpg";
            case CHIHUAHUA5:
                currentImg = Img.CHIHUAHUA4;
                return "frontend/img/guessing_game/chi4.jpg";
            case MUFFIN1:
                currentImg = Img.CHIHUAHUA5;
                return "frontend/img/guessing_game/chi5.jpg";
            case MUFFIN2:
                currentImg = Img.MUFFIN1;
                return "frontend/img/guessing_game/muffin1.png";
            case MUFFIN3:
                currentImg = Img.MUFFIN2;
                return "frontend/img/guessing_game/muffin2.png";
            case MUFFIN4:
                currentImg = Img.MUFFIN3;
                return "frontend/img/guessing_game/muffin3.png";
            case MUFFIN5:
                currentImg = Img.MUFFIN4;
                return "frontend/img/guessing_game/muffin4.png";
            default:
                currentImg = Img.CHIHUAHUA1;
                return "frontend/img/guessing_game/chi1.jpg";
        }
    }

    public String getNextImageSrc() {
        switch (currentImg) {
            case CHIHUAHUA1:
                currentImg = Img.CHIHUAHUA2;
                return "frontend/img/guessing_game/chi2.jpg";
            case CHIHUAHUA2:
                currentImg = Img.CHIHUAHUA3;
                return "frontend/img/guessing_game/chi3.jpg";
            case CHIHUAHUA3:
                currentImg = Img.CHIHUAHUA4;
                return "frontend/img/guessing_game/chi4.jpg";
            case CHIHUAHUA4:
                currentImg = Img.CHIHUAHUA5;
                return "frontend/img/guessing_game/chi5.jpg";
            case CHIHUAHUA5:
                currentImg = Img.MUFFIN1;
                return "frontend/img/guessing_game/muffin1.png";
            case MUFFIN1:
                currentImg = Img.MUFFIN2;
                return "frontend/img/guessing_game/muffin2.png";
            case MUFFIN2:
                currentImg = Img.MUFFIN3;
                return "frontend/img/guessing_game/muffin3.png";
            case MUFFIN3:
                currentImg = Img.MUFFIN4;
                return "frontend/img/guessing_game/muffin4.png";
            case MUFFIN4:
                currentImg = Img.MUFFIN5;
                return "frontend/img/guessing_game/muffin5.png";
            default:
                currentImg = Img.CHIHUAHUA1;
                return "frontend/img/guessing_game/chi1.jpg";
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
                return new Image("frontend/img/guessing_game/chi1.jpg", "");
            case CHIHUAHUA2:
                return new Image("frontend/img/guessing_game/chi2.jpg", "");
            case CHIHUAHUA3:
                return new Image("frontend/img/guessing_game/chi3.jpg", "");
            case CHIHUAHUA4:
                return new Image("frontend/img/guessing_game/chi4.jpg", "");
            case CHIHUAHUA5:
                return new Image("frontend/img/guessing_game/chi5.jpg", "");
            case MUFFIN1:
                return new Image("frontend/img/guessing_game/muffin1.png", "");
            case MUFFIN2:
                return new Image("frontend/img/guessing_game/muffin2.png", "");
            case MUFFIN3:
                return new Image("frontend/img/guessing_game/muffin3.png", "");
            case MUFFIN4:
                return new Image("frontend/img/guessing_game/muffin4.png", "");
            case MUFFIN5:
                return new Image("frontend/img/guessing_game/muffin5.png", "");
            default:
                return new Image();
        }
    }

    public Img getImg(String imageSrc) {
        switch (imageSrc) {
            case "frontend/img/guessing_game/chi1.jpg":
                return Img.CHIHUAHUA1;
            case "frontend/img/guessing_game/chi2.jpg":
                return Img.CHIHUAHUA2;
            case "frontend/img/guessing_game/chi3.jpg":
                return Img.CHIHUAHUA3;
            case "frontend/img/guessing_game/chi4.jpg":
                return Img.CHIHUAHUA4;
            case "frontend/img/guessing_game/chi5.jpg":
                return Img.CHIHUAHUA5;
            case "frontend/img/guessing_game/muffin1.png":
                return Img.MUFFIN1;
            case "frontend/img/guessing_game/muffin2.png":
                return Img.MUFFIN2;
            case "frontend/img/guessing_game/muffin3.png":
                return Img.MUFFIN3;
            case "frontend/img/guessing_game/muffin4.png":
                return Img.MUFFIN4;
            default:
                return Img.MUFFIN5;
        }
    }

    private String getCurrentGuessingGameImgTag() {
        if (currentGuessingGameImage.equals("frontend/img/guessing_game/chi1.jpg")
                || currentGuessingGameImage.equals("frontend/img/guessing_game/chi2.jpg")
                || currentGuessingGameImage.equals("frontend/img/guessing_game/chi3.jpg")
                || currentGuessingGameImage.equals("frontend/img/guessing_game/chi4.jpg")
                || currentGuessingGameImage.equals("frontend/img/guessing_game/chi5.jpg")) {
            return Tag.CHIHUAHUA;
        } else {
            return Tag.MUFFIN;
        }
    }

}
