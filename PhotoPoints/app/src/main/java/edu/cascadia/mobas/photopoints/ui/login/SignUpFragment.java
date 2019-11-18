package edu.cascadia.mobas.photopoints.ui.login;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

import edu.cascadia.mobas.photopoints.R;

public class SignUpFragment extends Fragment {

    private TextView mTextView;
    private int mYear;
    private int mMonth;
    private int mDayOfMonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        mTextView = root.findViewById(R.id.text_dateOfBirth);
        mTextView.setText(mMonth + "-" + mDayOfMonth + "-" + mYear);

        root.findViewById(R.id.button_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPicker(v);
            }
        });

        root.findViewById(R.id.button_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });

        return root;
    }

    private void openPicker(View v){
        new DatePickerDialog(getContext(), timePickerListener, mYear, mMonth, mDayOfMonth).show();
    }

    private DatePickerDialog.OnDateSetListener timePickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mTextView.setText(month + "-" + dayOfMonth + "-" + year);
        }

    };

    private void signUp(View v){
        //Hard-coded for now to go to Scan page.
        NavController nav = Navigation.findNavController(v);
        Bundle bundle = new Bundle();
        bundle.putBoolean("Registered", true);

        nav.navigate(R.id.navigation_scan, bundle);
    }
}
