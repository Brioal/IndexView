package com.brioal.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-8.
 */

public abstract class IndexAdapter extends RecyclerView.Adapter {
    private final int TYPE_NORMAL = 0;
    private final int TYPE_HEAD = 1;
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            return new NormalViewHolder(LayoutInflater.from(mContext).inflate(getNormalLayoutResID(), parent, false));
        } else if (viewType == TYPE_HEAD) {
            return new HeadViewHolder(LayoutInflater.from(mContext).inflate(getHeadLayoutResID(), parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            bindNormalView(((NormalViewHolder) holder).getRootView(), mList.get(position));
        } else if (holder instanceof HeadViewHolder) {
            bindHeadView(((HeadViewHolder) holder).getRootView(), mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).isHead()) {
            return TYPE_HEAD;
        }
        return TYPE_NORMAL;
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        private View mRootView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            mRootView = itemView;
        }

        public View getRootView() {
            return mRootView;
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        private View mRootView;

        public HeadViewHolder(View itemView) {
            super(itemView);
            mRootView = itemView;
        }

        public View getRootView() {
            return mRootView;
        }
    }

    public abstract void bindNormalView(View rootView, IndexBean bean);

    public abstract int getNormalLayoutResID();

    public abstract void bindHeadView(View rootView, IndexBean bean);

    public abstract int getHeadLayoutResID();
}
