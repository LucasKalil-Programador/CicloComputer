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

    private ImageButton configButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navbar, container, false);

        routeButton = view.findViewById(R.id.route_button);
        routeButton.setOnClickListener(this::onRouteClick);

        mapButton = view.findViewById(R.id.map_button);
        mapButton.setOnClickListener(this::onMapClick);

        configButton = view.findViewById(R.id.config_button);
        configButton.setOnClickListener(this::onConfigClick);

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

    private void onConfigClick(View v) {
        // Navegar para a página de configuraçoes
        Intent intent = new Intent(getActivity(), ConfigActivity.class);
        startActivity(intent);
    }
}