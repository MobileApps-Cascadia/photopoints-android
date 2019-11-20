package edu.cascadia.mobas.photopoints.ui.scan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.cascadia.mobas.photopoints.R;

public class ScanFragment extends Fragment {

    private ScanViewModel mScanViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mScanViewModel =
                ViewModelProviders.of(this).get(ScanViewModel.class);

        View root = inflater.inflate(R.layout.fragment_scan, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        mScanViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}