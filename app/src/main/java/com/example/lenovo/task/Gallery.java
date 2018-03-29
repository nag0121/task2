package com.example.lenovo.task;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by LENOVO on 21-11-2017.
 */

public class Gallery extends Fragment{

    View v;

    Button start;

    MediaPlayer mp3;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.gallery,container,false);

        start=(Button)v.findViewById(R.id.start_audio);


        mp3=MediaPlayer.create(getActivity(),R.raw.songa);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp3.start();

                start.setText("stop");

                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mp3.stop();

                        start.setText("start");


                    }
                });
            }


        });

        return v;
    }
}
