package com.example.thenamequizapp.adapters;
//
//import android.content.Context;
//import android.database.DataSetObserver;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.ListAdapter;
//import android.widget.TextView;
//
//import com.example.thenamequizapp.DatabaseActivity;
//import com.example.thenamequizapp.R;
//import com.example.thenamequizapp.classes.Person;
//
//import java.util.ArrayList;
//
//public class CustomAdapter implements ListAdapter {
//    ArrayList<Person> arrayList;
//    Context context;
//
//    public CustomAdapter(Context context, ArrayList<Person> arrayList) {
//        this.arrayList = arrayList;
//        this.context = context;
//    }
//
//    @Override
//    public boolean areAllItemsEnabled() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled(int position) {
//        return true;
//    }
//
//    @Override
//    public void registerDataSetObserver(DataSetObserver observer) {
//
//    }
//
//    @Override
//    public void unregisterDataSetObserver(DataSetObserver observer) {
//
//    }
//
//    @Override
//    public int getCount() {
//        return arrayList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Person person = arrayList.get(position);
//        if(convertView == null) {
//            LayoutInflater layoutInflater = LayoutInflater.from(context);
//            convertView = layoutInflater.inflate(R.layout.list_row,null);
//            convertView.setOnClickListener(v -> {
//            });
//            TextView tittle = convertView.findViewById(R.id.title);
//            ImageView img = convertView.findViewById(R.id.list_image);
//            tittle.setText(person.getName());
//            if(person.getUri() != null) {
//                img.setImageURI(person.getUri());
//            } else if (person.getIntImg() != 0) {
//                //Correct????
//                img.setImageResource(person.getIntImg());
//            }
//        }
//        return convertView;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return arrayList.size();
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//}

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
    private ArrayList<Person> persons;

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
        ViewHolder mViewHolder = new ViewHolder();

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_item, parent, false);
            mViewHolder.mImage = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.nameView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = persons.get(position);
        String name = person.getName();

        if (person.getIntImg() != 0) {
            mViewHolder.mImage.setImageResource(person.getIntImg());
            mViewHolder.mName.setText(name);
        } else if (person.getUri() != null) {
            mViewHolder.mImage.setImageURI(person.getUri());
            mViewHolder.mName.setText(name);
        }



        return convertView;

    }

    static class ViewHolder {
        ImageView mImage;
        TextView mName;
    }
}
