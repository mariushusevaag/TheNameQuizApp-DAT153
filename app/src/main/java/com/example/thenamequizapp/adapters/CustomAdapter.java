package com.example.thenamequizapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.thenamequizapp.DatabaseActivity;
import com.example.thenamequizapp.R;
import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    ArrayList<Person> persons;
    Context mContext;
    AppDatabase appDb;

    public CustomAdapter(Context context, List<Person> persons) {
        super(context, R.layout.listview_item);
        this.persons = new ArrayList<>(persons);
        this.mContext = context;
        this.appDb = AppDatabase.getInstance(context);
    }

    // Removes a selected person. Used when clicking on the delete icon
    public void remove(int position) {
        Person p = persons.get(position);
        persons.remove(position);
        appDb.personDao().removePerson(p);
        notifyDataSetChanged();
        Toast.makeText(mContext.getApplicationContext(), "You successfully removed " + p.getName(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(mContext, DatabaseActivity.class);
        mContext.startActivity(i);
    }

    @Override
    public int getCount() {
        //Return array size
        return persons.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Make variables for use later on
        ViewHolder mViewHolder = new ViewHolder();
        Person person = persons.get(position);
        String name = person.getName();

        //Check if convertView equals null
        if (convertView == null) {
            //Inflate the view/layout
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_item, parent, false);

            //connect image and text from view to variable
            mViewHolder.mImage = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.person_name_text);
            mViewHolder.deleteBtn = (ImageButton) convertView.findViewById(R.id.deleteBtn);

            //set tag for convertView
            convertView.setTag(mViewHolder);

            //onClick listener
            mViewHolder.deleteBtn.setOnClickListener(v -> remove(position));
        } else {
            //set holder variable to existing view
            mViewHolder = (ViewHolder) convertView.getTag();
        }

            //change the text in the name field to the persons name
            mViewHolder.mName.setText(name);

            //change the empty imageView to the image connected to the person
            if (person.getUri() != null) {
                mViewHolder.mImage.setImageURI(person.getUri());
            }

        return convertView;
    }

    //ViewHolder class
    static class ViewHolder {
        ImageView mImage;
        TextView mName;
        ImageButton deleteBtn;
    }
}
