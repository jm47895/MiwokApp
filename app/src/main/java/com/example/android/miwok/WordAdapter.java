package com.example.android.miwok;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jordan on 11/17/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int colorResID;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResID){
        super(context,0,words);
        this.colorResID = colorResID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }

        Word currentWord = getItem(position);

        TextView englishTextView = (TextView) listItemView.findViewById(R.id.oneText);
        englishTextView.setText(currentWord.getEnglishWord());

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.luttiText);
        miwokTextView.setText(currentWord.getMiwokWord());

        ImageView images = (ImageView) listItemView.findViewById(R.id.basketImg);

        if(currentWord.hasImage()){
            images.setImageResource(currentWord.getImageResID());

            images.setVisibility(View.VISIBLE);
        }else{
            images.setVisibility(View.GONE);
        }


        final View textContainer = listItemView.findViewById(R.id.textContainer);
        int color = ContextCompat.getColor(getContext(), colorResID);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }





}
