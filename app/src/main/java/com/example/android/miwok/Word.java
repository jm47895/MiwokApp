package com.example.android.miwok;

import android.media.MediaPlayer;

/**
 * Created by Jordan on 11/17/17. Word represents a word to be translated into a miwok
 * word
 */

public class Word {

    private String englishWord;
    private String miwokWord;
    private int mediaID = 0;
    private int imageResID = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;


    /**
     *
     * @param englishWord is the word the an english person would use
     * @param miwokWord is the translation of the english into a miwokword
     * @param mediaID is the resource id the for the sound file we want to associate with the word
     */
    public Word(String englishWord, String miwokWord, int mediaID) {
        this.englishWord = englishWord;
        this.miwokWord = miwokWord;
        this.mediaID = mediaID;
    }

    /**
     *
     * @param englishWord is the word the an english person would use
     * @param miwokWord is the translation of the english into a miwokword
     * @param imageResID is the resource id the for the image we want to associate with the word
     * @param mediaID is the resource id the for the sound file we want to associate with the word
     */
    public Word(String englishWord, String miwokWord, int imageResID, int mediaID) {
        this.englishWord = englishWord;
        this.miwokWord = miwokWord;
        this.imageResID = imageResID;
        this.mediaID = mediaID;
    }

    public String getEnglishWord() {return englishWord;}
    public String getMiwokWord() {
        return miwokWord;
    }
    public int getImageResID() {return imageResID;}
    public int getMediaID(){return mediaID;}


    public boolean hasImage(){
        return imageResID != NO_IMAGE_PROVIDED;
    }




}
