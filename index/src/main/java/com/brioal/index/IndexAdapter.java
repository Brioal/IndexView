package com.brioal.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-8.
 */

public abstract class IndexAdapter<C extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    protected List<IndexBean> mList = new ArrayList<>();
    protected Context mContext;

    public IndexAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<IndexBean> list) {
        mList.clear();
        mList.addAll(list);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
