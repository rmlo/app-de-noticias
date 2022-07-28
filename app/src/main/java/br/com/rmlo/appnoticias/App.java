package br.com.rmlo.appnoticias;

import android.app.Application;

/**
 * Centralizamos uma instancia do contexto em nosso {@link App} (Mesmo sendo um "anti-pattern") para extrairmos o máximo dos nossos
 * ViewModel e camada de acesso de dados. Entretanto, apesar das nossas camadas ficarem mais coesas, o ideal seria usar uma solução
 * de injeção de dependencias (como dagger ou Hilt).
 */
public class App extends Application {

    private static App instance;

    public static App getInstance(){ return instance;}

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
