package ru.shmakova.evotortest.data.groups;

import java.util.ArrayList;
import java.util.List;

import ru.shmakova.evotortest.data.groups.models.Group;
import rx.Observable;

/**
 * Created by shmakova on 16.10.16.
 */

public class GroupsRepositoryImpl implements GroupsRepository {
    @Override
    public Observable<List<Group>> getGroups() {
        List<Group> groups = new ArrayList<>();
        groups.add(Group.builder().filename("test1").title("Группа 1").build());
        groups.add(Group.builder().filename("test2").title("Группа 2").build());
        groups.add(Group.builder().filename("test3").title("Группа 3").build());
        return null;
    }
}
