package edu.cascadia.mobas.photopoints.ui.scan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.ui.login.SignInFragment;

public class ScanFragment extends Fragment {

    //This will have to change to an authentication model. But for the primary prototyping purposes, this is semi-hardcoded.
    private boolean mRegistered;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_scan, container, false);

        Bundle args = getArguments();
        if (args != null){
            mRegistered = args.getBoolean("Registered");
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(!mRegistered){
            NavController nav = Navigation.findNavController(view);
            nav.navigate(R.id.action_navigation_scan_to_navigation_signin2);
        }
    }
}