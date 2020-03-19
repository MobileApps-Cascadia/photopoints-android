package edu.cascadia.mobas.photopoints.ui.photopoints;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.repo.Repository;
import edu.cascadia.mobas.photopoints.ui.details.DetailsViewModel;

public class PhotoPointsFragment extends Fragment {

    Repository repo;

    private DetailsViewModel viewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //ViewModel set
        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_photopoints, container, false);

        PhotoPointsAdapter adapter = new PhotoPointsAdapter(getContext(),repo,getFragmentManager());

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.cardview_photopoints);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),2));

        return root;
    }

    FragmentManager fragmentManager = new FragmentManager() {

    };

    private Context context;

    public void onClick(View v){
        fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
    }


}
