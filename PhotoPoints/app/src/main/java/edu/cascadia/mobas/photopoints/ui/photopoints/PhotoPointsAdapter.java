package edu.cascadia.mobas.photopoints.ui.photopoints;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.PlantItem;
import edu.cascadia.mobas.photopoints.repo.Repository;
import edu.cascadia.mobas.photopoints.ui.details.DetailsFragment;
import edu.cascadia.mobas.photopoints.ui.details.DetailsViewModel;


public class PhotoPointsAdapter extends RecyclerView.Adapter<PhotoPointsAdapter.PhotoPointsViewHolder>{

    private LiveData<List<PlantItem>> mAllPlants;
    private FragmentManager mFragmentManager;
    private Context mContext;
    private DetailsViewModel model;

    // constructor
    public PhotoPointsAdapter(Context context, @NonNull Repository repo, FragmentManager fragmentManager){
        mContext = context;
        mFragmentManager = fragmentManager;
        mAllPlants = Repository.getInstance(context).getPlants();
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
            }
        }

        @NonNull
        @Override
        public PhotoPointsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_photopoint, parent, false);


            return new PhotoPointsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final PhotoPointsViewHolder viewHolder, int position) {

            PlantItem plant = mAllPlants.getValue().get(position);
            final int id = plant.plant.getId();
            final String st1 = plant.plant.getCommonNames().get(0);
            final String st2 = plant.plant.getSpecies();
            final String st3 = plant.plant.getDescription();

            viewHolder.image_photopoint_displayphoto.setImageBitmap(Repository.getInstance(mContext)
                    .getImage(id));
            viewHolder.text_photopoint_displaytext.setText(st1);
            viewHolder.text_photopoint_subtext.setText(st2);
            //Click listener for recycler view items
            viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View itemView){
                    Bundle bundle = getText(id, st1,st2,st3);
                    mFragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment,DetailsFragment.setInstance(bundle))
                            .addToBackStack(null)
                            .commit();
                }
            });
        }

    @Override
    public int getItemCount() {
        return mAllPlants.getValue().size();
    }

    //Bundle for transferring
    public Bundle getText(int id, String one, String two, String three){

        Bundle textBundle = new Bundle();
        textBundle.putInt("id", id);
        textBundle.putString("common", one);
        textBundle.putString("species", two);
        textBundle.putString("desc", three);
        return textBundle;

}

}
