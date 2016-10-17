package ru.shmakova.evotortest.presentation.presenters;

import java.util.List;

import javax.inject.Inject;

import ru.shmakova.evotortest.data.content.NixanService;
import ru.shmakova.evotortest.data.content.models.Content;
import ru.shmakova.evotortest.data.groups.models.Group;
import ru.shmakova.evotortest.presentation.views.ContentListView;
import rx.Observable;


/**
 * Created by shmakova on 16.10.16.
 */

public class ContentListPresenter extends BaseRxPresenter<ContentListView, List<Content>> {

    private final NixanService nixanService;

    @Inject
    public ContentListPresenter(NixanService nixanService) {
        this.nixanService = nixanService;
    }

    public void loadContent(Group group, boolean pullToRefresh) {
        Observable<List<Content>> observable = nixanService.getContent(group.filename());
        subscribe(observable, pullToRefresh);
    }
}
