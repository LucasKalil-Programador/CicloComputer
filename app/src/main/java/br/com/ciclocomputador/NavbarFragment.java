package br.com.ciclocomputador;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class NavbarFragment extends Fragment {

    private ImageButton routeButton;
    private ImageButton mapButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navbar, container, false);

        routeButton = view.findViewById(R.id.route_button);
        routeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRouteClick(v);
            }
        });

        mapButton = view.findViewById(R.id.map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapClick(v);
            }
        });

        return view;
    }

    private void onRouteClick(View v) {
        // Navegar para a página da rota
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    private void onMapClick(View v) {
        // Navegar para a página do mapa
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);
    }
}