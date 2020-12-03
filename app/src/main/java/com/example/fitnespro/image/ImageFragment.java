package com.example.fitnespro.image;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.fitnespro.R;
import com.example.fitnespro.databinding.FragmentGetImageBinding;

public class ImageFragment extends Fragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageViewModel viewModel = getViewModel();
        viewModel.getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentGetImageBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_image, container, false);
        binding.setLifecycleOwner(this);

        ImageViewModel viewModel = getViewModel();
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    private ImageViewModel getViewModel() {
        return ViewModelProviders.of(this).get(ImageViewModel.class);
    }

}