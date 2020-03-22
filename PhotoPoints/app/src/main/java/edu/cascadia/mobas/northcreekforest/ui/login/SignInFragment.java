package edu.cascadia.mobas.northcreekforest.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import edu.cascadia.mobas.northcreekforest.R;

public class SignInFragment extends Fragment {

    private TextInputEditText mEditText_email;
    private TextInputEditText mEditText_password;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sign_in, container, false);

        mEditText_email = root.findViewById(R.id.text_email);
        mEditText_password = root.findViewById(R.id.text_password);

        root.findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signIn(v);
            }
        });

        root.findViewById(R.id.text_signup).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });

        return root;
    }

    private void signIn(View v){

        //For now, we hardcode a continue.
        String email = mEditText_email.getText().toString();
        String password = mEditText_password.getText().toString();

        NavController nav = Navigation.findNavController(v);
        Bundle bundle = new Bundle();
        bundle.putBoolean("Registered", true);

        nav.navigate(R.id.navigation_scan, bundle);
    }

    private void signUp(View v){
        NavController nav = Navigation.findNavController(v);
        nav.navigate(R.id.navigation_signup);
    }
}
