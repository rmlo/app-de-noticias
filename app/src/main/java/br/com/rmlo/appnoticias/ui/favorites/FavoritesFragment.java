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
    private FavoritesViewModel favoritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        loadFavoriteNews();

        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        favoritesViewModel.loadFavoriteNews().observe(getViewLifecycleOwner(), localNews ->{
            binding.rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvFavorite.setAdapter(new NewsAdapter(localNews, updatedNews ->{
                favoritesViewModel.saveNews(updatedNews);
                loadFavoriteNews();
            }));
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}