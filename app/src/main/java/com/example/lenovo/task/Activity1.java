package com.example.lenovo.task;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LENOVO on 15-11-2017.
 */

public class Activity1 extends AppCompatActivity {

    ImageView menu,update,maps;
    TextView homepage;
    ListView listView;
    DrawerLayout drawerLayout;
    Fragment fragment;
    FragmentManager fm;
    SharedPreferences sharedPreferences;

    int[] images1={R.drawable.gallery,R.drawable.videos,R.drawable.cloud,R.drawable.analytics,R.drawable.maps};
    String[] names1={"audio","video","cloud","analytics","json"};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        homepage=(TextView)findViewById(R.id.tv_home_header);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_view_activity);
        menu = (ImageView) findViewById(R.id.iv_menu_header);
        update = (ImageView) findViewById(R.id.iv_update_header);
        maps = (ImageView) findViewById(R.id.iv_settings_header);
        listView = (ListView) findViewById(R.id.lv_frags_activity);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_frag_activity);

        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);


        LayoutInflater inflater = getLayoutInflater();
        final View listHeaderView = inflater.inflate(R.layout.drawerheader, null, false);

        listView.addHeaderView(listHeaderView);


        customAdapter customData = new customAdapter(getApplicationContext(), images1, names1);
        listView.setAdapter(customData);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(listView)) {

                    drawerLayout.closeDrawer(listView);
                } else {

                    drawerLayout.openDrawer(listView);
                }

            }
        });
//implicit intent
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.google.co.in/webhp?ei=1e8MWp6FHIbwvgTs1JH4CA&yv=2&rct=j";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new Navigator());

        Toast.makeText(getApplicationContext(),"oncreate",Toast.LENGTH_SHORT).show();

        maps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


//                sharedPreferences.edit().remove("_names").apply();
//                sharedPreferences.edit().remove("_passwords").apply();
//                sharedPreferences.contains("_names");
                sharedPreferences.edit().clear().commit();

                Intent intent=new Intent(Activity1.this,MainActivity.class);
                startActivity(intent);



            }
        });

    }


    private class Navigator implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            selectItem(position);

        }

        private void selectItem(int position) {

            switch (position){

                case 1:

                    fragment = new Gallery();
                    break;

                case 2:

                    fragment = new Videos();
                    break;

                case 3:

                    fragment = new Cloud();
                    break;

                case 4:

                    fragment = new Analytics();
                    break;

                case 5:

                    fragment = new Maps();

            }
            if(fragment != null){

                fm=getSupportFragmentManager();

                fm.beginTransaction().replace(R.id.fl_frag_activity,fragment).commit();

                drawerLayout.closeDrawer(listView);


            }


        }


    }


    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(),"on start",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();


        Toast.makeText(getApplicationContext(),"on Pause",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(),"onResume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(),"onStope",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(),"destroyed",Toast.LENGTH_SHORT).show();
    }
}

