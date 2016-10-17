package ru.shmakova.evotortest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ru.shmakova.evotortest.App;
import ru.shmakova.evotortest.R;
import ru.shmakova.evotortest.data.content.models.Content;
import ru.shmakova.evotortest.data.groups.models.Group;
import ru.shmakova.evotortest.di.components.ContentListComponent;
import ru.shmakova.evotortest.di.modules.ContentListModule;
import ru.shmakova.evotortest.presentation.presenters.ContentListPresenter;
import ru.shmakova.evotortest.presentation.views.ContentListView;
import ru.shmakova.evotortest.ui.adapters.ContentListAdapter;
import ru.shmakova.evotortest.utils.ErrorMessageDeterminer;


/**
 * Created by shmakova on 16.10.16.
 */

@FragmentWithArgs
public class ContentListFragment
        extends BaseLceFragment<SwipeRefreshLayout, List<Content>, ContentListView, ContentListPresenter>
        implements ContentListView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    ErrorMessageDeterminer errorMessageDeterminer;

    @Arg
    Group group;

    ContentListComponent contentListComponent;
    ContentListAdapter adapter;

    protected void injectDependencies() {
        contentListComponent =
                App.get(getContext()).applicationComponent().plus(new ContentListModule());
        contentListComponent.inject(this);
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
        FragmentArgs.inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ContentListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        contentView.setOnRefreshListener(this);
    }

    @Override
    public LceViewState<List<Content>, ContentListView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public List<Content> getData() {
        return adapter.getContentList();
    }

    @Override
    public void setData(List<Content> data) {
        adapter.setContentList(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return errorMessageDeterminer.getErrorMessage(e, pullToRefresh);
    }

    @Override
    public ContentListPresenter createPresenter() {
        return contentListComponent.presenter();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadContent(group, pullToRefresh);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
        e.printStackTrace();
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh && !contentView.isRefreshing()) {
            contentView.post(() -> contentView.setRefreshing(true));
        }
    }
}
