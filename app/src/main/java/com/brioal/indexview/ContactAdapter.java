package com.brioal.indexview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brioal.index.IndexAdapter;
import com.brioal.index.IndexBean;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-8.
 */

public class ContactAdapter extends IndexAdapter {

    public ContactAdapter(Context context) {
        super(context);
    }

    @Override
    public void bindNormalView(View rootView, IndexBean bean) {
        TextView tvName = (TextView) rootView.findViewById(R.id.item_normal_tv_name);
        TextView tvPhone = (TextView) rootView.findViewById(R.id.item_normal_tv_phone);
        final ContactBean contactBean = (ContactBean) bean;
        tvName.setText(contactBean.getName());
        tvPhone.setText(contactBean.getPhone());
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, contactBean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getNormalLayoutResID() {
        return R.layout.item_normal;
    }

    @Override
    public void bindHeadView(View rootView, IndexBean bean) {
        TextView tvIndex = (TextView) rootView.findViewById(R.id.item_head_tv_index);
        TextView tvName = (TextView) rootView.findViewById(R.id.item_head_tv_name);
        TextView tvPhone = (TextView) rootView.findViewById(R.id.item_head_tv_phone);
        final ContactBean contactBean = (ContactBean) bean;
        tvIndex.setText(contactBean.getIndex()+"");
        tvName.setText(contactBean.getName());
        tvPhone.setText(contactBean.getPhone());

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, contactBean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getHeadLayoutResID() {
        return R.layout.item_head;
    }
}
