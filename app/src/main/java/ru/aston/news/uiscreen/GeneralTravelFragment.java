package ru.aston.news.uiscreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import moxy.MvpAppCompatFragment;
import ru.aston.news.App;
import ru.aston.news.R;
import ru.aston.news.dto.Post;
import ru.aston.news.presenters.travel.TravelView;


public class GeneralTravelFragment extends MvpAppCompatFragment implements TravelView {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        App.appComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_head_travel,
                container, false);


        //Button nextButton = (Button) view.findViewById(R.id.button_first);
        //nextButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void updateToPost(List<Post> posts) {

    }

    @Override
    public void navigToPost(int id) {

    }
}