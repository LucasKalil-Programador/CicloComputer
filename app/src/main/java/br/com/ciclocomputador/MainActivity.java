package br.com.ciclocomputador;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.ciclocomputador.Permission.LocationPermissionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocationPermissionManager.requestLocationPermission(getApplicationContext(), this);

        try {Thread.sleep(1000);} catch (InterruptedException e) { e.printStackTrace(); }

        setTheme(R.style.Theme_CicloComputador);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LocationPermissionManager.defaultOnRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}