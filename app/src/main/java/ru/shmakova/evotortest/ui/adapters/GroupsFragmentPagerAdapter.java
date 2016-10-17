package ru.shmakova.evotortest.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import ru.shmakova.evotortest.data.groups.models.Group;
import ru.shmakova.evotortest.ui.fragments.ContentListFragmentBuilder;

/**
 * Created by shmakova on 16.10.16.
 */

public class GroupsFragmentPagerAdapter extends FragmentPagerAdapter {
    private final List<Group> groups;

    public GroupsFragmentPagerAdapter(FragmentManager fm, List<Group> groups) {
        super(fm);
        this.groups = groups;
    }

    @Override
    public Fragment getItem(int position) {
        return new ContentListFragmentBuilder(groups.get(position)).build();
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return groups.get(position).title();
    }

    public List<Group> getGroups() {
        return groups;
    }
}
