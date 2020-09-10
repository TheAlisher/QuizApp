package com.example.quiz.db.converter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

public class DataConverter {

    @TypeConverter
    public static Long toRaw(@Nullable Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date fromRaw(@Nullable Long timestamp) {
        if (timestamp == null) return null;

        return new Date(timestamp);
    }
}
