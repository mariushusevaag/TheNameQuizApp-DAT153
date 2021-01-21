package com.example.thenamequizapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.thenamequizapp.R;
import com.example.thenamequizapp.classes.Person;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
    ArrayList<Person> persons;
    Context mContext;

    public CustomAdapter(Context context, ArrayList<Person> persons) {
        super(context, R.layout.listview_item);
        this.persons = persons;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ViewHolder mViewHolder = new ViewHolder();
        Person person = persons.get(position);
        String name = person.getName();

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_item, parent, false);
            //mViewHolder.mImage = (ImageView) convertView.findViewById(R.id.imageView);
            //mViewHolder.
            //TextView mName = (TextView) convertView.findViewById(R.id.person_name_text);
            ImageView mImage = (ImageView) convertView.findViewById(R.id.imageView);
            TextView nameField = (TextView) convertView.findViewById(R.id.person_name_text);
            //convertView.setTag(mViewHolder);
        /*} else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }*/

            //mViewHolder.
            nameField.setText(name);

            if (person.getIntImg() != 0) {
                //mViewHolder.
                mImage.setImageResource(person.getIntImg());
            } else if (person.getUri() != null) {
                //mViewHolder.
                mImage.setImageURI(person.getUri());
            }
        }
        return convertView;
    }
/*
    static class ViewHolder {
        ImageView mImage;
        TextView mName;
    }*/
}
