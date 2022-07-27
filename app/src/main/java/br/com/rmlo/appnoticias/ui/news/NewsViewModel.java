package br.com.rmlo.appnoticias.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.rmlo.appnoticias.data.remote.NewsApi;
import br.com.rmlo.appnoticias.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR;
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<State>();
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
        state.setValue(State.DOING);
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()){
                    state.setValue(State.DONE);
                    news.setValue(response.body());
                } else {
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                state.setValue(State.ERROR);
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
    public LiveData<State> getState() {
        return this.state;
    }
}