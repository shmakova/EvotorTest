package ru.shmakova.evotortest.data.groups;

import java.util.List;

import ru.shmakova.evotortest.data.groups.models.Group;
import rx.Observable;

/**
 * Created by shmakova on 16.10.16.
 */

public interface GroupsRepository {
    Observable<List<Group>> getGroups();
}
