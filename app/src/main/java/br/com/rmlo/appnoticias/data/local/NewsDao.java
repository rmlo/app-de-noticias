package br.com.rmlo.appnoticias.data.local;

import androidx.room.Dao;
import androidx.room.Query;
import br.com.rmlo.appnoticias.domain.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news WHERE favorite = :favorite")
    List<News> loadFavoriteNews(boolean favorite);
}
