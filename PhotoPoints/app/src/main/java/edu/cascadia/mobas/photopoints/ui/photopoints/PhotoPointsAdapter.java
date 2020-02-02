package edu.cascadia.mobas.photopoints.ui.photopoints;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.PhotoPoint;
import edu.cascadia.mobas.photopoints.model.Plant;
import edu.cascadia.mobas.photopoints.repo.PhotoPointsRepository;
import edu.cascadia.mobas.photopoints.repo.PlantRepository;
import edu.cascadia.mobas.photopoints.ui.details.DetailsFragment;


public class PhotoPointsAdapter extends RecyclerView.Adapter<PhotoPointsAdapter.PhotoPointsViewHolder>{
    FragmentManager fragmentManager;
    private ArrayList<PhotoPoint> mPhotoPointRepo;
    private PlantRepository mPlantRepo;
    private Context mContext;
    private FragmentManager mFragmentManager;



    // constructor
    public PhotoPointsAdapter(Context context, PlantRepository plantRepo, FragmentManager fragmentManager){
        mContext = context;
        mFragmentManager = fragmentManager;
        mPhotoPointRepo = new ArrayList<PhotoPoint>();
        mPlantRepo = plantRepo;
        mPhotoPointRepo.add(new PhotoPoint(1, 47.776013, -122.192043, "https://www.plantsmap.com/organizations/24477/plants/28097", PhotoPoint.PhotoPointType.Plant, 1));
        mPhotoPointRepo.add(new PhotoPoint(2, 47.775886, -122.192635, "https://www.plantsmap.com/organizations/24477/plants/28069", PhotoPoint.PhotoPointType.Plant, 2));
        mPhotoPointRepo.add(new PhotoPoint(3, 47.776013, -122.193909, "https://www.plantsmap.com/organizations/24477/plants/28092", PhotoPoint.PhotoPointType.Plant,3 ));
        mPhotoPointRepo.add(new PhotoPoint(4, 47.775241, -122.195866, "https://www.plantsmap.com/organizations/24477/plants/28061", PhotoPoint.PhotoPointType.Plant,4 ));
        mPhotoPointRepo.add(new PhotoPoint(5, 47.774999, -122.195243, "https://www.plantsmap.com/organizations/24477/plants/28074", PhotoPoint.PhotoPointType.Plant,5 ));
        mPhotoPointRepo.add(new PhotoPoint(6, 47.774484, -122.195694, "https://www.plantsmap.com/organizations/24477/plants/28070", PhotoPoint.PhotoPointType.Plant,6 ));
        mPhotoPointRepo.add(new PhotoPoint(7, 47.773701, -122.194359, "https://www.plantsmap.com/plants/28068", PhotoPoint.PhotoPointType.Plant,7 ));
        mPhotoPointRepo.add(new PhotoPoint(8, 47.774150, -122.192456, "https://www.plantsmap.com/organizations/24477/plants/28094", PhotoPoint.PhotoPointType.Plant,8 ));
    }

    class PhotoPointsViewHolder extends RecyclerView.ViewHolder{
        ImageView image_photopoint_displayphoto;
        TextView text_photopoint_displaytext;
        TextView text_photopoint_subtext;


public ClassLoader CL;

        public PhotoPointsViewHolder(@NonNull View itemView) {
            super(itemView);
            image_photopoint_displayphoto = (ImageView) itemView.findViewById(R.id.image_photopoint_displayphoto);
            text_photopoint_displaytext = (TextView) itemView.findViewById(R.id.text_photopoint_displaytext);
            text_photopoint_subtext = (TextView) itemView.findViewById(R.id.text_photopoint_subtext);
            //Click listener for recycler view items
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View itemView){
                mFragmentManager.beginTransaction()
                        .add(R.id.nav_host_fragment, new DetailsFragment())
                        .commit();




                }
            });
        }
    }


    @NonNull
    @Override
    public PhotoPointsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_photopoint, parent, false);
        return new PhotoPointsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoPointsViewHolder viewHolder, int position) {

        PhotoPoint photoPoint = mPhotoPointRepo.get(position);
        Plant plant = mPlantRepo.getById(photoPoint.getItemID());
        viewHolder.image_photopoint_displayphoto.setImageResource(R.drawable.default_plant_photo_small);
        viewHolder.text_photopoint_displaytext.setText(plant.getCommonName());
        viewHolder.text_photopoint_subtext.setText(plant.getSpecies());

    }

    @Override
    public int getItemCount() {
        return mPhotoPointRepo.size();
    }



}
