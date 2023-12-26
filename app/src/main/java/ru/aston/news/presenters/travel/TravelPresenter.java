/*package ru.aston.news.presenters.travel;


import android.util.Log;

import com.github.terrakok.cicerone.Router;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.aston.news.App;

import ru.aston.news.DisposableManager;
import ru.aston.news.dto.Filters;
import ru.aston.news.dto.Screens;
import ru.aston.news.repository.PostRepository;

@InjectViewState
public class TravelPresenter extends MvpPresenter<TravelView> {

    private Router router;
    private final PostRepository repository;

    @Inject
    public TravelPresenter(PostRepository repository, Router router) {
        this.repository = repository;
        this.router = router;
    }

    public void navigate(int id) {
        router.navigateTo(Screens.ForwardSingleBusinessPost(id));
    }

    fun navigate(id:Int) {
        router.navigateTo(ForwardSingleBusinessPost(id))

    }

    public Filters getFilters() {
        return repository.getFilters();
    }

    fun getFilters():Filters =repository.getFilters()

    fun getData(language:String?, sortBy:String?, from:String?, to:String?) {
        DisposableManager.add(
                repository.getBusinessPosts(language, sortBy, from, to)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({response ->
                                viewState.updateRecycler(response)
                        }, {
                                Log.e(TAG, "error = $it")
                        })
        )
    }


}
*/
