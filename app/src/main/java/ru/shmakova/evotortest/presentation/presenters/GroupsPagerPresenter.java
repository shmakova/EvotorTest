package ru.shmakova.evotortest.presentation.presenters;

import java.util.List;

import javax.inject.Inject;

import ru.shmakova.evotortest.data.groups.GroupsRepository;
import ru.shmakova.evotortest.data.groups.models.Group;
import ru.shmakova.evotortest.presentation.views.GroupsPagerView;
import rx.Observable;


/**
 * Created by shmakova on 16.10.16.
 */

public class GroupsPagerPresenter extends BaseRxPresenter<GroupsPagerView, List<Group>> {

    private final GroupsRepository groupsRepository;

    @Inject
    public GroupsPagerPresenter(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    public void loadContent(boolean pullToRefresh) {
        Observable<List<Group>> observable = groupsRepository.getGroups();
        subscribe(observable, pullToRefresh);
    }
}
