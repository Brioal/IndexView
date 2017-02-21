package com.brioal.index;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-8.
 */

public class IndexListView extends RelativeLayout implements OnIndexClickListener {
    private RecyclerView mRecyclerView;
    private LeftIndex mLeftIndex;
    private List<IndexBean> mList;
    private TextView mTvTip;
    private int mSelectIndex = 0;
    private boolean isMove = false;

    public IndexListView(Context context) {
        this(context, null);
    }

    public IndexListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLeftIndex = new LeftIndex(context);
        mRecyclerView = new RecyclerView(context);
        mTvTip = new TextView(context);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //在这里进行第二次滚动（最后的100米！）
                if (isMove) {
                    isMove = false;
                    int n = mSelectIndex - ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    if (0 <= n && n < mRecyclerView.getChildCount()) {
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        int top = mRecyclerView.getChildAt(n).getTop();
                        //最后的移动
                        mRecyclerView.scrollBy(0, top);
                    }
                }
            }
        });
    }

    //设置数据源
    public IndexListView setData(List<IndexBean> list) {
        mList = list;
        //进行排序
        Collections.sort(list, new Comparator<IndexBean>() {
            @Override
            public int compare(IndexBean lhs, IndexBean rhs) {
                if ("#".equals(lhs.getIndex())) {
                    return 1;
                } else if ("#".equals(rhs.getIndex())) {
                    return -1;
                }
                return lhs.getIndex() - rhs.getIndex();
            }

        });
        //标记用于充当Head的元素
        List<Character> avaliableIndex = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            //如果是第一个数据，添加一个Index
            if (i == 0) {
                avaliableIndex.add(mList.get(0).getIndex());
            }
            //如果当前数据的Index跟下一个的Index不一样，那么就在这个之后添加一个Index
            if (i + 1 < mList.size() && mList.get(i + 1) != null) {
                if (mList.get(i).getIndex() != mList.get(i + 1).getIndex()) {
                    avaliableIndex.add(mList.get(i + 1).getIndex());
                }
            }
        }
        //Index设置数据源及监听事件
        mLeftIndex.setAvailableIndex(avaliableIndex);
        mLeftIndex.setIndexClickListener(this);


        return this;
    }

    //Index列表是否有背景
    public IndexListView isIndexBG(boolean isBG) {
        mLeftIndex.setHasBg(isBG);
        return this;
    }

    //设置列表项的Adapter
    public IndexListView setAdapter(IndexAdapter adapter) {
        //Adapter数据添加
        adapter.setData(mList);
        //设置到RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        return this;
    }

    //最后数据集成
    public void build() {
        mLeftIndex.build();
        //添加RecyclerView到View
        RelativeLayout.LayoutParams recyclerParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mRecyclerView, recyclerParams);

        //添加LeftIndex到View
        RelativeLayout.LayoutParams leftIndexParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftIndexParams.addRule(RelativeLayout.CENTER_VERTICAL);
        leftIndexParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        leftIndexParams.leftMargin = 10;
        leftIndexParams.topMargin = 10;
        leftIndexParams.rightMargin = 10;
        leftIndexParams.bottomMargin = 10;
        addView(mLeftIndex, leftIndexParams);

        //添加指示器
        LayoutParams tipParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTvTip.setPadding(100, 50, 100, 50);
        mTvTip.setBackgroundResource(R.drawable.ic_index_bg);
        mTvTip.setTextColor(Color.WHITE);
        mTvTip.setTextSize(60);
        tipParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mTvTip.setVisibility(GONE);
        addView(mTvTip, tipParams);
    }


    @Override
    public void click(char ch) {
        int position = -1;
        //跳转到指定的position
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getIndex() == ch) {
                position = i;
                break;
            }
        }
        mTvTip.setText(ch + "");
        mTvTip.setVisibility(VISIBLE);
        mTvTip.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTvTip.setVisibility(GONE);
            }
        }, 500);
        moveToPosition(position);
    }

    private void moveToPosition(int n) {
        mSelectIndex = n;
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = ((LinearLayoutManager)mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        int lastItem = ((LinearLayoutManager)mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem ){
            //当要置顶的项在当前显示的第一个项的前面时
            mRecyclerView.scrollToPosition(n);
        }else if ( n <= lastItem ){
            //当要置顶的项已经在屏幕上显示时
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        }else{
            //当要置顶的项在当前显示的最后一项的后面时
            mRecyclerView.scrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
            isMove = true;
        }

    }
}
