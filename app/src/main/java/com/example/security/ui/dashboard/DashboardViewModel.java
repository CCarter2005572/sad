package com.example.security.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Dashboard Page" + "\n" + "Upload your photos of threats here or enter a phone number on the line for an emergency call");
    }

    public LiveData<String> getText() {
        return mText;
    }
}