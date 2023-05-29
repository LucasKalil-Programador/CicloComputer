package br.com.ciclocomputer;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.ciclocomputer.databinding.ActivityMapsBinding;
import br.com.ciclocomputer.tracker.GPSTracker;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (MainActivity.checkLocationPermissions(this)) {
            // As permissões de localização já foram concedidas, inicialize o mapa
            mapFragment.getMapAsync(this);
        } else {
            // As permissões de localização não foram concedidas, solicite à MainActivity

            MainActivity mainActivity;
            mainActivity = new MainActivity();
            mainActivity.requestLocationPermission(this, this);

        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        GPSTracker gpsTracker = new GPSTracker(getBaseContext());
        gpsTracker.startTracking();

        Location location = gpsTracker.getLocation();


            LatLng currentLocation = new LatLng(gpsTracker.getLocation().getLatitude(), gpsTracker.getLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

    }
}
