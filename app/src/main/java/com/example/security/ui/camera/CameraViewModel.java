package com.example.security.ui.camera;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CameraViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CameraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Camera Page" + "\n" + "Use this camera to take pictures of your hostile threats");
    }

    public LiveData<String> getText() {
        return mText;
    }
}