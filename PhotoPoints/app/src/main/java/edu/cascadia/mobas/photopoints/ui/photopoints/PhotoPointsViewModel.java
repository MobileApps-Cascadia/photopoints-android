package edu.cascadia.mobas.photopoints.ui.photopoints;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PhotoPointsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PhotoPointsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a PhotoPoints fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
