package dev.major.pressHub.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.major.pressHub.modal.Article


@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converts::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
    companion object{
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,ArticleDatabase::class.java,"article_db")
                .build()
    }

}