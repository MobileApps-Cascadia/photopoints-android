package edu.cascadia.mobas.photopoints.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Information class for a species of plant

public class Plant {
    private int mPlantId;           // Identifier for plant species
    private String mSpecies;           // Name of species (ex Mangocatus longii)
    private String mDescription;       // Full description of species
    private List<String> mCommonNames; // List of common names for plant.  First added is main.
    private List<String> mImageURIs;   // URI list of available images for plant

    // Constructor chain

    // prevent instantiation with empty constructor
    private Plant() {}

    // Minimum constructor requires plantId and species
    public Plant(@NonNull int plantId, @NonNull String species) {
        mPlantId = plantId;
        mSpecies = species;
        mDescription = "";   // null safety
        mCommonNames = new ArrayList<String>();
        mImageURIs = new ArrayList<String>();
    }

    public Plant(@NonNull int plantId, @NonNull String species, String commonName) {
        this(plantId, species);
        this.addCommonName(commonName);
    }

    public Plant(@NonNull int plantId, @NonNull String species, String commonName, String description) {
        this(plantId, species, commonName);
        mDescription = description;
    }

    //<editor-fold desc="Getters and Setters">
    // Unique identifier for this plant
    public int getPlantId() {
        return mPlantId;
    }

    // Species name of this plant
    public String getSpecies() {
        return mSpecies;
    }

    // Common name returned is the first on the list of common names, or species if nonexistent
    public String getCommonName() {
        if (mCommonNames.size() > 0) {
            return mCommonNames.get(0);
        } else {
            return mSpecies;
        }
    }

    // The full list of common names for the species. For main item only, use getCommonName()
    public List<String> getCommonNameList() {
        return mCommonNames;
    }

    // Return the main URI, the first on the list for this species.
    public String getImageUri() {
        if (mImageURIs.size() > 0) {
            return mImageURIs.get(0);
        } else {
            return "";        // returns empty string if no images are added
        }
    }

    // Return the list of imageURIs for this species. For main display image only, use getImageURI()
    public List<String> getImageUriList() {
        return mImageURIs;
    }

    // Add a name to the list of common names, if it doesn't already exist
    public void addCommonName(@NonNull String nameString) {
        if (nameString.trim().equals("")) return;
        List<String> names = new ArrayList<>(Arrays.asList(nameString.split(";")));
        for (String name: names){
            name = name.trim();
            if (!(name.equals("") || mCommonNames.contains(nameString))){
                mCommonNames.add(name);
            }
        }
    }

    // Return the description for the species
    public String getDescription() {
        return mDescription;
    }

    // Set the description for the species
    public void setDescription(@NonNull String description) {
        mDescription = description;
    }

    // Add a URI to the end of the list of ImageURIs, if it doesn't already exist
    public void addImageURI(@NonNull String uri) {
        if (!uri.equals("") && !mImageURIs.contains(uri)) {
            mImageURIs.add(uri);
        }
    }

    // Remove a URI from the list of ImageURIs, if it exists
    void removeImageURI(@NonNull String uri) {
        if (!uri.equals("") && mImageURIs.contains(uri)) {
            mImageURIs.remove(uri);
        }
    }

    // Set a URI to be the main ImageURI, adding it to the list if necessary
    public void setImageURI(@NonNull String uri) {
        if (uri.equals("") || mImageURIs.get(0) == uri) {
            return;
        } else if (mImageURIs.contains(uri)) {
            String temp = mImageURIs.get(0);
            mImageURIs.set(0, uri);
            mImageURIs.add(temp);
        } else {
            mImageURIs.add(0, uri);
        }
    }
    //</editor-fold>
}
