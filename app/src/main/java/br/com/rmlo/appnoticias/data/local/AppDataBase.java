package br.com.rmlo.appnoticias.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.rmlo.appnoticias.domain.News;

@Database(entities = {News.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
        public abstract NewsDao newsDao();
}



