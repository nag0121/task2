package com.example.lenovo.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LENOVO on 21-11-2017.
 */

public class Maps extends Fragment {

    View v;

    Button click;
    TextView textView;

    ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.maps,container,false);

        click=(Button)v.findViewById(R.id.btn_login_main);

        textView=(TextView)v.findViewById(R.id.tv_maps);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebServices();
            }
        });




        return v;
    }
    public void WebServices(){

        new MyWebServices("http://api.androidhive.info/contacts/").execute();


    }

    public class MyWebServices extends AsyncTask<String,Void, String>{

        String urla;


    public MyWebServices(String url) {

        this.urla=url;

    }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setTitle("Loading.....");
            dialog.setCancelable(false);
            dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder builder_output=new StringBuilder();

            try
            {

                URL url=new URL(urla);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(1500);



                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line="";
                StringBuilder builder_temp=new StringBuilder();
                while((line=br.readLine())!=null)
                {
                    builder_temp.append(line);
                }

                builder_output.append(builder_temp.toString());

            }
            catch (Exception e)
            {

                e.printStackTrace();

            }

            return builder_output.toString();
        }



        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if(dialog.isShowing())
                dialog.dismiss();
            Log.e("result",response);


            try
            {
                JSONObject jsonObject_root=new JSONObject(response);

                JSONArray contacts=jsonObject_root.getJSONArray("contacts");
                for(int i=0;i<contacts.length();i++)
                {

                    JSONObject jsonObject_array=contacts.getJSONObject(i);

                    String id=jsonObject_array.getString("id");
                    String name=jsonObject_array.getString("name");
                    String email=jsonObject_array.getString("email");

                    JSONObject jsonObject_phone=jsonObject_array.getJSONObject("phone");

                    String mobile=jsonObject_phone.getString("mobile");

                  /*  Log.e("id ",id);
                    Log.e("name ",name);
                    Log.e("email ",email);
                    Log.e("phone ",mobile);*/





                }

            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
