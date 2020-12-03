package com.example.fitnespro.image;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.example.fitnespro.bmi.data.ApiRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class ImageViewModel extends ViewModel {

    private final ImageController controller = new ImageController();
    private LiveData<List<String>> liveData;

    public LiveData<List<String>> getData() {
        controller.start();

        liveData = controller.getData();

        return liveData;
    }

    public LiveData<List<String>> getLiveData() {
        return liveData;
    }

    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }
}
