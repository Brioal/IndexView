package com.brioal.indexview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.brioal.index.IndexAdapter;

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_normal, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContactViewHolder) {
            ((ContactViewHolder) holder).bindView((ContactBean) mList.get(position));
        }
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        View mItemView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
        }

        public void bindView(ContactBean bean) {
            TextView tvName = (TextView) mItemView.findViewById(R.id.item_normal_tv_name);
            TextView tvPhone = (TextView) mItemView.findViewById(R.id.item_normal_tv_phone);
            final ContactBean contactBean = (ContactBean) bean;
            tvName.setText(contactBean.getName());
            tvPhone.setText(contactBean.getPhone());
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, contactBean.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
