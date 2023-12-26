package ru.aston.news.presenters.travel;

import java.util.List;

import moxy.MvpView;
import ru.aston.news.dto.Post;


public interface TravelView extends MvpView {
     void updateToPost(List<Post> posts) ;

     void navigToPost(int id);
}
