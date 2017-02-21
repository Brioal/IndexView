package com.brioal.index;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-8.
 */

public class LeftIndex extends LinearLayout implements View.OnClickListener {
    private List<Character> mAvailableIndex;//可用Index的列表
    private int mColorNormal; //普通Index颜色
    private int mColorAvailable; //可用的Index颜色
    private boolean hasBg = true;//是否有背景
    private OnIndexClickListener mIndexClickListener;//Index点击监听器

    public LeftIndex(Context context) {
        super(context);
    }


    //设置可用的Index
    public void setAvailableIndex(List<Character> availableIndex) {
        mAvailableIndex = availableIndex;
    }

    //是否黑色背景
    public LeftIndex setHasBg(boolean hasBg) {
        this.hasBg = hasBg;
        return this;
    }

    //设置点击监听器
    public LeftIndex setIndexClickListener(OnIndexClickListener indexClickListener) {
        mIndexClickListener = indexClickListener;
        return this;
    }

    //最终参数设置
    public void build() {
        mColorNormal = getResources().getColor(R.color.index_color_normal);
        mColorAvailable = getResources().getColor(R.color.index_color_avaliable);
        initData();
        //设置背景
        if (hasBg) {
            setBackgroundResource(R.drawable.ic_index_bg);
        }

    }


    private void initData() {
        //设置垂直布局
        setOrientation(LinearLayout.VERTICAL);
        //设置居中
        setGravity(Gravity.CENTER);
        //设置Padding和Margin
        setPadding(5, 5, 5, 5);
        //添加数据
        for (int i = 'A' - 1; i <= 'Z'; i++) {
            char index = (i == 'A' - 1) ? '#' : (char) i;
            TextView tv = new TextView(getContext());
            tv.setId(Integer.valueOf(index));
            tv.setText(index + "");
            tv.setOnClickListener(this);
            if (mAvailableIndex != null && mAvailableIndex.contains(index)) {
                //包含
                tv.setTextColor(mColorAvailable);
            } else {
                //不包含
                tv.setTextColor(mColorNormal);
            }
            LayoutParams tvParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tvParams.weight = 1;
            addView(tv, tvParams);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        char index = (char) id;
        if (mIndexClickListener != null) {
            mIndexClickListener.click(index);
        }
    }
}
