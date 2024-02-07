package dev.major.pressHub.db

import androidx.room.TypeConverter
import dev.major.pressHub.modal.Source

class Converts {


    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name,name)
    }
}