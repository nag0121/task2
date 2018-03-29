package com.example.lenovo.task;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by LENOVO on 21-11-2017.
 */

public class Analytics extends Fragment {

    TextView read;
    Button click;

    MydataBase mydataBase;

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.analytics,container,false);

         read =(TextView)v.findViewById(R.id.tv_read_analytics);
         click=(Button)v.findViewById(R.id.btn_click_analytics);

         mydataBase=new MydataBase(getActivity());

    click.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {



                Cursor res = mydataBase.getData();

                StringBuffer stringBuffer=new StringBuffer();

                if(res!=null && res.getCount()>0){

                    while (res.moveToNext()){
                        stringBuffer.append("ID: "+res.getString(0)+"\n");
                        stringBuffer.append("FIRST_NAME: "+res.getString(1)+"\n");
                        stringBuffer.append("LAST_NAME: "+res.getString(2)+"\n");
                        stringBuffer.append("PHONE: "+res.getString(3)+"\n");
                        stringBuffer.append("password: "+res.getString(4)+"\n"+"\n");
                    }

                    read.setText(stringBuffer.toString());

                }
                else {
                    Toast.makeText(getActivity(),"data not found",Toast.LENGTH_LONG).show();
                }
                 String[] names={String.valueOf(stringBuffer)};

            }
            catch (Exception e){
                e.printStackTrace();
            }

            click.setText("click to close");

            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    read.setText("CLOSE" +
                            "D");
                }
            });
        }
    });




        return v;
    }
}
