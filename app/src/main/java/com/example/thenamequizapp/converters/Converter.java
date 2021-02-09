package com.example.thenamequizapp.converters;


import android.net.Uri;

import androidx.room.TypeConverter;

public class Converter {

    @TypeConverter
    public static Uri fromString(String string) {
        if (string == null) {
            return null;
        } else {
            return Uri.parse(string);
        }
    }

    @TypeConverter
    public static String fromUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }
}
