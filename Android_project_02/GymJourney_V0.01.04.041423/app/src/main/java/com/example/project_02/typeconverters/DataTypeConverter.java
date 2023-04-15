/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */

/** VERSIONS
 * 0.01.00.41023: updated build.gradle:module:app create packages, and db files, java files and xml files; get enough xml layouts for part 02;
 *
 */
package com.example.project_02.typeconverters;

import androidx.room.TypeConverter;
import java.util.Date;

public class DataTypeConverter {
    /**
     * 0.01.00.41023: copied from GymLog, to convert dates. look into other type conversions wanted
     */

    @TypeConverter
    public long dateToLong(Date date) {
        return date.getTime();
    }


    @TypeConverter
    public Date convertLongToDate( long time){
        return  new Date(time);
    }

}
