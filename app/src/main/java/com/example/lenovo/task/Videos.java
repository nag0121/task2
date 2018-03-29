package com.example.lenovo.task;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by LENOVO on 21-11-2017.
 */

public class Videos extends Fragment{

    View v;

    VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.videos,container,false);

        videoView=(VideoView)v.findViewById(R.id.vv_videos_Videos);

        Button click = (Button)v.findViewById(R.id.start_video);



        Uri uri = Uri.parse("http://abhiandroid-8fb4.kxcdn.com/ui/wp-content/uploads/2016/04/videoviewtestingvideo.mp4");

        videoView.setVideoURI(uri);

        //videoView.setVideoURI(Uri.parse("android.resource://" + getActivity() + "/" + R.raw.video));



       MediaController mediaController = new MediaController(getActivity());
       videoView.setMediaController(mediaController);

      // mediaController.setAnchorView(videoView);

       click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                videoView.start();
            }
        });




        return v;
    }
}
