package br.com.rmlo.appnoticias.data;

import androidx.room.Room;
import br.com.rmlo.appnoticias.App;
import br.com.rmlo.appnoticias.data.remote.NewsApi;
import br.com.rmlo.appnoticias.data.local.AppDataBase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppNoticiasRepository {

    private static final String REMOTE_API_URL = "https://rmlo.github.io/app-de-noticias-api/";
    private static final String LOCAL_DB_NAME = "App-Noticias";

    private NewsApi remoteApi;
    private AppDataBase localDb;

    public NewsApi getRemoteApi() {return remoteApi;}

    public AppDataBase getLocalDb() {return localDb;}

//garante uma unica instancia dos atributos relacionados ao room e retrofit
    private AppNoticiasRepository(){
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi.class);

        localDb = Room.databaseBuilder(App.getInstance(),AppDataBase.class,LOCAL_DB_NAME).build();
    }

    public static AppNoticiasRepository getInstance(){return LazyHolder.INSTANCE;}

    public static class LazyHolder {
        private static final AppNoticiasRepository INSTANCE = new AppNoticiasRepository();
    }
}
