package edu.cascadia.mobas.photopoints.ui.photopoints;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.repo.PhotoPointsRepository;
import edu.cascadia.mobas.photopoints.repo.PlantRepository;

public class PhotoPointsFragment extends Fragment {

    PhotoPointsRepository photoPointsRepo = new PhotoPointsRepository();
    PlantRepository plantRepo = new PlantRepository();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_photopoints, container, false);

        PhotoPointsAdapter adapter = new PhotoPointsAdapter(photoPointsRepo, plantRepo);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.cardview_photopoints);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),2));

        return root;
    }

}
