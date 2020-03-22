package edu.cascadia.mobas.northcreekforest.ui;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private int qrScanItemId;
    private int detailsItemId;


    public SharedViewModel() {
        // viewmodel initialization
    }


    public int getDetailsItemId() {
        return detailsItemId;
    }

    public void setDetailsItemId(int id) {
        detailsItemId = id;
    }

    public int getQrScalItemId() {
        return qrScanItemId;
    }

    public void setQrScanItemId(int id)  {
        qrScanItemId = id;
    }

}