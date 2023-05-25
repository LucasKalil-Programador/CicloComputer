package br.com.ciclocomputer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class SaveDataActivity extends Fragment {


    private static ArrayList<SaveDataModel> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_save_data, container, false);
    }

    public static void addDataModel(SaveDataModel dataParam) {
        data.add(dataParam);
        System.out.println("print"+data);
    }

}