package ru.aston.news.uiscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ru.aston.news.R;

public class GeneralHeadFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_head_general,
                container, false);


        //Button nextButton = (Button) view.findViewById(R.id.button_first);
        //nextButton.setOnClickListener(this);

        return view;
    }
}

