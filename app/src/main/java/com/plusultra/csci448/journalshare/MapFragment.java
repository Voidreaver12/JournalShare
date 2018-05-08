package com.plusultra.csci448.journalshare;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * MapFragment is where the user can browse on a Google Map
 * and look for anonymous journal entries from the remote
 * database. Tapping on a pin will bring up the title of that
 * journal entry, and tapping on the title will download that
 * entry into your local database.
 *
 * Created by ndeibert on 3/16/2018.
 */

public class MapFragment extends SupportMapFragment {

    private static final String TAG = "MapFragment";

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private GoogleApiClient mClient;
    private GoogleMap mMap;
    private List<JournalEntry> entries = new ArrayList<>();

    public static MapFragment newInstance() {
        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // get Google API client
        mClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        getActivity().invalidateOptionsMenu();
                    }
                    @Override
                    public void onConnectionSuspended(int i) {}
                })
                .build();

        // get Google Map
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                // update map with markers when map is done loading
                mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        updateUI();
                    }
                });
                // On tap marker, show title
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        marker.showInfoWindow();
                        return true;
                    }
                });
                // On tap title, save entry
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        for (JournalEntry e : entries) {
                            if (e.getId().toString().equals(marker.getTag().toString())) {
                                JournalBook.get(getActivity()).addEntry(e);
                            }
                        }
                    }
                });

            }
        });
        // get database reference
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("entries");
        mRef.addValueEventListener(new ValueEventListener() {
            // get all data
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> journalMap = (HashMap<String, Object>)dataSnapshot.getValue();
                for (Object o : journalMap.values()) {
                    if (o instanceof Map) {
                        // parse title, text, and coordinates and add to list
                        JournalEntry entry = new JournalEntry();
                        entry.setTitle(((Map) o).get("title").toString());
                        entry.setText(((Map) o).get("text").toString());
                        entry.setLat((double)((Map) o).get("lat"));
                        entry.setLon((double)((Map) o).get("lon"));
                        entries.add(entry);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().invalidateOptionsMenu();
        mClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mClient.disconnect();
    }

    private void updateUI() {
        // If the map is invalid, dont update
        if (mMap == null) {
            return;
        }
        // clear the map
        mMap.clear();
        // get markers for all journal entries in firebase
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        if (entries.size() > 0) {
            for (int i = 0; i < entries.size(); i++) {
                LatLng point = new LatLng(entries.get(i).getLat(), entries.get(i).getLon());
                MarkerOptions marker = new MarkerOptions()
                        .position(point)
                        .title(entries.get(i).getTitle());
                Marker m = mMap.addMarker(marker);
                m.setTag(entries.get(i).getId().toString());
                boundsBuilder.include(point);
            }
        } else { // If our app has an empty database, create bounds to include the entire state of colorado
            LatLng coloradoNorthEast = new LatLng(41f, -102f);
            LatLng coloradoSouthWest = new LatLng(37f, -109f);
            boundsBuilder.include(coloradoNorthEast);
            boundsBuilder.include(coloradoSouthWest);
        }
        // update camera based on markers
        LatLngBounds bounds = boundsBuilder.build();
        int margin = getResources().getDimensionPixelSize(R.dimen.map_inset_margin);
        CameraUpdate update = CameraUpdateFactory.newLatLngBounds(bounds, margin);
        mMap.animateCamera(update);
    }


}
