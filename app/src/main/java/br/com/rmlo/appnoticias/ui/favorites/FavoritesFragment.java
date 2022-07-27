package br.com.rmlo.appnoticias.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.rmlo.appnoticias.MainActivity;
import br.com.rmlo.appnoticias.ui.adapter.NewsAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import br.com.rmlo.appnoticias.databinding.FragmentFavoritesBinding;
import br.com.rmlo.appnoticias.domain.News;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        loadFavoriteNews();
        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        MainActivity activity = (MainActivity) getActivity();
        List<News> favoriteNews = null;
        if (activity != null) {
            favoriteNews = activity.getDb().newsDao().loadFavoriteNews(false);
        }
        binding.rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvFavorite.setAdapter(new NewsAdapter(favoriteNews, updatedNews ->{
            activity.getDb().newsDao().save(updatedNews);
            loadFavoriteNews();
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}