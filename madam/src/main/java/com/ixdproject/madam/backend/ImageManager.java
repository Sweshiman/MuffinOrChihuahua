package com.ixdproject.madam.backend;

import com.vaadin.flow.component.html.Image;

import java.util.*;

public class ImageManager {

    private String currentGuessingGameImage;
    private List<String> guessingGameImages = new ArrayList<>();
    private Random random = new Random();
    private int guessingGameScore = 0;

    public ImageManager() {
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
        MUFFIN5,
        TUNING_GAME_1,
        TUNING_GAME_2,
        TUNING_GAME_3,
        TUNING_GAME_4,
        TUNING_GAME_5,
        TUNING_GAME_6,
        TUNING_GAME_7,
        TUNING_GAME_8
    }

    public Image getFirstTuningGameImage() {
        return getImage(Img.TUNING_GAME_1);
    }

    public List<Image> getTuningGameImages() {
        List<Image> tuningImages = new ArrayList<>();

        tuningImages.add(getImage(Img.TUNING_GAME_1));
        tuningImages.add(getImage(Img.TUNING_GAME_2));
        tuningImages.add(getImage(Img.TUNING_GAME_3));
        tuningImages.add(getImage(Img.TUNING_GAME_4));
        tuningImages.add(getImage(Img.TUNING_GAME_5));
        tuningImages.add(getImage(Img.TUNING_GAME_6));
        tuningImages.add(getImage(Img.TUNING_GAME_7));
        tuningImages.add(getImage(Img.TUNING_GAME_8));

        return tuningImages;
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
            case TUNING_GAME_1:
                return new Image("frontend/img/tuning_game/chi1.jpg", "");
            case TUNING_GAME_2:
                return new Image("frontend/img/tuning_game/muffin1.jpg", "");
            case TUNING_GAME_3:
                return new Image("frontend/img/tuning_game/chi2.jpg", "");
            case TUNING_GAME_4:
                return new Image("frontend/img/tuning_game/muffin2.jpg", "");
            case TUNING_GAME_5:
                return new Image("frontend/img/tuning_game/chi3.jpg", "");
            case TUNING_GAME_6:
                return new Image("frontend/img/tuning_game/muffin3.jpg", "");
            case TUNING_GAME_7:
                return new Image("frontend/img/tuning_game/chi4.jpg", "");
            default:
                return new Image("frontend/img/tuning_game/muffin4.jpg", "");
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
            case "frontend/img/guessing_game/muffin5.png":
                return Img.MUFFIN5;
            case "frontend/img/tuning_game/chi1.jpg":
                return Img.TUNING_GAME_1;
            case "frontend/img/tuning_game/muffin1.jpg":
                return Img.TUNING_GAME_2;
            case "frontend/img/tuning_game/chi2.jpg":
                return Img.TUNING_GAME_3;
            case "frontend/img/tuning_game/muffin2.jpg":
                return Img.TUNING_GAME_4;
            case "frontend/img/tuning_game/chi3.jpg":
                return Img.TUNING_GAME_5;
            case "frontend/img/tuning_game/muffin3.jpg":
                return Img.TUNING_GAME_6;
            case "frontend/img/tuning_game/chi4.jpg":
                return Img.TUNING_GAME_7;
            default:
                return Img.TUNING_GAME_8;
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
