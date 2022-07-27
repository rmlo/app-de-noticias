package br.com.rmlo.appnoticias.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.com.rmlo.appnoticias.domain.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(News news);

    @Query("SELECT * FROM news WHERE favorite = 1")
    List<News> loadFavoriteNews(boolean favorite);
}
