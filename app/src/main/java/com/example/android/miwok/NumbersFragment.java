package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMedia();
        }
    };
    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusState) {
            switch (focusState){
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMedia();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    mediaPlayer.start();
                    break;
            }
        }
    };

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> numberWords = new ArrayList();

        numberWords.add(new Word("one","lutti", R.drawable.number_one, R.raw.number_one));
        numberWords.add(new Word("two","otiiko", R.drawable.number_two, R.raw.number_two));
        numberWords.add(new Word("three","tolookosu", R.drawable.number_three, R.raw.number_three));
        numberWords.add(new Word("four","oyyisa", R.drawable.number_four, R.raw.number_four));
        numberWords.add(new Word("five","massokka", R.drawable.number_five, R.raw.number_five));
        numberWords.add(new Word("six","temmokka", R.drawable.number_six, R.raw.number_six));
        numberWords.add(new Word("seven","kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numberWords.add(new Word("eight","kawinta", R.drawable.number_eight, R.raw.number_eight));
        numberWords.add(new Word("nine","wo'e", R.drawable.number_nine, R.raw.number_nine));
        numberWords.add(new Word("ten","na'aacha", R.drawable.number_ten, R.raw.number_ten));



        WordAdapter adapter = new WordAdapter(getActivity(), numberWords, R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.list);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                releaseMedia();

                Word word = numberWords.get(position);

                int request = audioManager.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);


                if(request == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getMediaID());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMedia();
    }

    private void releaseMedia(){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

        audioManager.abandonAudioFocus(afChangeListener);
    }

}
