package ru.shmakova.evotortest.ui.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.annotatedadapter.annotation.ViewField;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;

import java.util.List;

import javax.inject.Inject;

import ru.shmakova.evotortest.R;
import ru.shmakova.evotortest.data.content.models.Content;

/**
 * Created by shmakova on 16.10.16.
 */

public class ContentListAdapter extends SupportAnnotatedAdapter implements ContentListAdapterBinder {
    @ViewType(
            layout = R.layout.item_content,
            views = {
                    @ViewField(id = R.id.pic, name = "pic", type = ImageView.class),
                    @ViewField(id = R.id.title, name = "title", type = TextView.class)
            }
    )
    public final int row = 0;
    private List<Content> contentList;

    @Inject
    public ContentListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return contentList == null ? 0 : contentList.size();
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    @Override
    public void bindViewHolder(ContentListAdapterHolders.RowViewHolder vh, int position) {
        Content content = contentList.get(position);

        vh.title.setText(content.title());

        Glide.with(vh.pic.getContext())
                .load(content.pic())
                .placeholder(R.color.grey)
                .error(R.color.grey)
                .into(vh.pic);
    }
}
