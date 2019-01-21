package menurestaurante.proyecto.mauriciogarcia.ejemplonaviationdrawer;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;

    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                View v= inflater.inflate(R.layout.fragment_contact_us,container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }


        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;

            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            UiSettings uiSettings = map.getUiSettings();
            uiSettings.setZoomControlsEnabled(true);

            LatLng  pp= new LatLng(13.8147954,-88.8636758);
            map.addMarker(new MarkerOptions().position(pp).title("Restaurante Ilobasco"));

            float zoomlevel = 16;

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp,zoomlevel));
        }
}
