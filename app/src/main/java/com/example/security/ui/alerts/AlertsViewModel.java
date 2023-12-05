package com.example.security.ui.alerts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlertsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AlertsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Alerts Page" + "\n" + "Play a sound to scare off hostile threats");
    }

    public LiveData<String> getText() {
        return mText;
    }
}