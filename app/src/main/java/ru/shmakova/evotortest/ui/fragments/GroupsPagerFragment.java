package ru.shmakova.evotortest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ru.shmakova.evotortest.App;
import ru.shmakova.evotortest.R;
import ru.shmakova.evotortest.data.groups.models.Group;
import ru.shmakova.evotortest.di.components.GroupsPagerComponent;
import ru.shmakova.evotortest.di.modules.GroupsPagerModule;
import ru.shmakova.evotortest.presentation.presenters.GroupsPagerPresenter;
import ru.shmakova.evotortest.presentation.views.GroupsPagerView;
import ru.shmakova.evotortest.ui.adapters.GroupsFragmentPagerAdapter;
import ru.shmakova.evotortest.utils.ErrorMessageDeterminer;


/**
 * Created by shmakova on 16.10.16.
 */

public class GroupsPagerFragment
        extends BaseLceFragment<LinearLayout, List<Group>, GroupsPagerView, GroupsPagerPresenter>
        implements GroupsPagerView {
    @BindView(R.id.pager)
    ViewPager viewPager;

    @Inject
    ErrorMessageDeterminer errorMessageDeterminer;

    GroupsPagerComponent groupsPagerComponent;
    GroupsFragmentPagerAdapter adapter;

    protected void injectDependencies() {
        groupsPagerComponent =
                App.get(getContext()).applicationComponent().plus(new GroupsPagerModule());
        groupsPagerComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        injectDependencies();
        return inflater.inflate(R.layout.fragment_content_list, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public LceViewState<List<Group>, GroupsPagerView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public List<Group> getData() {
        return adapter.getGroups();
    }

    @Override
    public void setData(List<Group> data) {
        viewPager.setAdapter(new GroupsFragmentPagerAdapter(getFragmentManager(), data));
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return errorMessageDeterminer.getErrorMessage(e, pullToRefresh);
    }

    @Override
    public GroupsPagerPresenter createPresenter() {
        return groupsPagerComponent.presenter();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadContent(pullToRefresh);
    }
}
