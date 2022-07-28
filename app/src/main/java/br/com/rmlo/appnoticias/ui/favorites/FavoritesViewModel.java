package br.com.rmlo.appnoticias.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import br.com.rmlo.appnoticias.data.AppNoticiasRepository;

import java.util.List;

import br.com.rmlo.appnoticias.domain.News;

public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel() {
    }

    public LiveData<List<News>> loadFavoriteNews() {
        return AppNoticiasRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews();
    }

    public void saveNews(News news){
        AsyncTask.execute(()->AppNoticiasRepository.getInstance().getLocalDb().newsDao().save(news));
    }

}