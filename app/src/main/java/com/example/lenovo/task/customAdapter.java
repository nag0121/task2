package com.example.lenovo.task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by LENOVO on 16-11-2017.
 */

public class customAdapter extends BaseAdapter {

    Context context;
    String[] names;
    int[] images;

    LayoutInflater layoutInflater;

    public customAdapter(Context context, int[] images, String[] names) {
        this.images=images;
        this.names=names;
        this.context=context;

        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View convertview=view;

        ViewHolder viewHolder = new ViewHolder();

        if(convertview==null){

            convertview=layoutInflater.inflate(R.layout.customadapter,null);


            viewHolder.icons=(ImageView)convertview.findViewById(R.id.iv_gallery_custom);

            viewHolder.iconnames=(TextView)convertview.findViewById(R.id.tv_gallery_custom);

            convertview.setTag(viewHolder);
        }
        else {

            viewHolder=(ViewHolder)convertview.getTag();
        }

        viewHolder.icons.setImageResource(images[position]);
        viewHolder.iconnames.setText(names[position]);

        return convertview;
    }

    public class ViewHolder{

        ImageView icons;
        TextView iconnames;

    }

}
