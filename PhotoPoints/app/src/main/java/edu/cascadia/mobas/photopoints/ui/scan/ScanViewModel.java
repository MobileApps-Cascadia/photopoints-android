package edu.cascadia.mobas.photopoints.ui.scan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScanViewModel extends ViewModel {

    private MutableLiveData<String> scannedValue;

    public MutableLiveData<String> getScannedValue() {
        if (scannedValue == null) {
            scannedValue = new MutableLiveData<String>();
        }
        return scannedValue;
    }

    public LiveData<String> getText() {
        return scannedValue;
    }
}