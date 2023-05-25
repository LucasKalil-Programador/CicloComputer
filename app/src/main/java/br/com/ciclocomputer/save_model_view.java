package br.com.ciclocomputer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link save_model_view#newInstance} factory method to
 * create an instance of this fragment.
 */
public class save_model_view extends Fragment {

    private static final String ARG_PARAM1 = "avgSpeedParam";
    private static final String ARG_PARAM2 = "timeParam";
    private static final String ARG_PARAM3 = "distanceParam";

    // TODO: Rename and change types of parameters
    private String avgSpeedParam;
    private String timeParam;
    private String distanceParam;

    public save_model_view() {
        // Required empty public constructor
    }

    public static save_model_view newInstance(String avgSpeedParam, String timeParam, String distanceParam) {
        save_model_view fragment = new save_model_view();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, avgSpeedParam);
        args.putString(ARG_PARAM2, timeParam);
        args.putString(ARG_PARAM3, distanceParam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            avgSpeedParam = getArguments().getString(ARG_PARAM1);
            timeParam = getArguments().getString(ARG_PARAM2);
            distanceParam = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_save_model_view, container, false);
    }
}