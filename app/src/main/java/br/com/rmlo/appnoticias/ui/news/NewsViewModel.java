package br.com.rmlo.appnoticias.ui.news;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import br.com.rmlo.appnoticias.data.local.AppDataBase;

import java.util.List;

import br.com.rmlo.appnoticias.data.remote.NewsApi;
import br.com.rmlo.appnoticias.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final NewsApi api;


    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rmlo.github.io/app-de-noticias-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(NewsApi.class);
        this.findNews();

    }

    private void findNews() {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()){
                    news.setValue(response.body());
                } else {
                    //TODO pensar em um tratamento de erros.
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //TODO pensar em um tratamento de erros.
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}