package br.com.ciclocomputer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;


public class SaveDataActivity extends AppCompatActivity {


    private static ArrayList<SaveDataModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
    }

    public static void addDataModel(SaveDataModel dataParam) {
        data.add(dataParam);
        System.out.println("print"+data);
    }

}