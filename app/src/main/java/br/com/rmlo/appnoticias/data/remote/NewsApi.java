package br.com.rmlo.appnoticias.data.remote;

import java.util.List;
import br.com.rmlo.appnoticias.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {
    @GET("news.json")
    Call<List<News>> getNews();
}
