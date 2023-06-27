package br.com.ciclocomputador;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;

import java.util.Locale;

public class ConfigActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_CicloComputador);
        setContentView(R.layout.activity_config);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Spinner unitSpinner = findViewById(R.id.unity_spinner);
        ArrayAdapter<CharSequence> adapterUnity = ArrayAdapter.createFromResource(this, R.array.units_types, android.R.layout.simple_spinner_item);
        adapterUnity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapterUnity);
        unitSpinner.setSelection(0);
        unitSpinner.setOnItemSelectedListener(new onUnitChange());

        Spinner languageSpinner = findViewById(R.id.language_spinner);
        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this, R.array.language_codes, android.R.layout.simple_spinner_item);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);languageSpinner.setSelection(0);
        languageSpinner.setAdapter(adapterLanguage);
        languageSpinner.setSelection(0);
        languageSpinner.setOnItemSelectedListener(new onLanguageChange());

        SwitchCompat economy = findViewById(R.id.battery_save_switch);
        economy.setOnCheckedChangeListener(new onCheckedChange());
    }

    class onLanguageChange implements AdapterView.OnItemSelectedListener {

        /**
         * @param parent   The AdapterView where the selection happened
         * @param view     The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id       The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String text = parent.getItemAtPosition(position).toString();
            setLocale(text);
        }

        /**
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        private void setLocale(String languageCode) {
            // Check if the selected language is the same as the current language
            if (languageCode.equals(Locale.getDefault().getLanguage())) {
                return; // No need to change the language
            }

            Locale locale = new Locale(languageCode);
            Locale.setDefault(locale);

            Resources resources = getResources();
            Configuration configuration = resources.getConfiguration();
            configuration.setLocale(locale);
            configuration.setLayoutDirection(locale);

            resources.updateConfiguration(configuration, resources.getDisplayMetrics());

            // Restart the activity for the changes to take effect
            recreate();
        }
    }

    class onUnitChange implements AdapterView.OnItemSelectedListener {

        /**
         * @param parent   The AdapterView where the selection happened
         * @param view     The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id       The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String text = parent.getItemAtPosition(position).toString();
            if(text.equals("Miles")) {
                Configs.DefaultUnit = "mph";
            } else {
                Configs.DefaultUnit = "km/h";
            }
        }

        /**
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class onCheckedChange implements CompoundButton.OnCheckedChangeListener {

        /**
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(Configs.batteryEconomy != isChecked) {
                Configs.batteryEconomy = isChecked;
                recreate();
            }
        }
    }
}