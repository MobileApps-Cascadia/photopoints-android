package edu.cascadia.mobas.photopoints.ui.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> Details_Name;
    private MutableLiveData<String> Details_Sci_Name;
    private MutableLiveData<String> Details_Desc;


    public DetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("かみよ、なぜわたしをみすてられたんだ？");
    }

    public LiveData<String> getDetail_sName(){
        if (Details_Name == null){
            Details_Name = new MutableLiveData<String>();

        }
        return Details_Name;
    }
    public LiveData<String> getDetails_Sci_Name(){
        if (Details_Sci_Name == null){
            Details_Sci_Name = new MutableLiveData<String>();
        }
        return Details_Sci_Name;
    }
    public LiveData<String> getDetails_Desc(){
        if (Details_Desc == null){
            Details_Sci_Name = new MutableLiveData<String>();
        }
        return Details_Desc;
    }
}
