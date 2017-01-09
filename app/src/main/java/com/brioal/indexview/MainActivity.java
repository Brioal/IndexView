package com.brioal.indexview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brioal.index.IndexBean;
import com.brioal.index.IndexListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private IndexListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (IndexListView) findViewById(R.id.main_indexView);

        List<IndexBean> list = new ArrayList<>();
        list.add(new ContactBean("张三", "123"));
        list.add(new ContactBean("653", "123"));
        list.add(new ContactBean("李四", "321312"));
        list.add(new ContactBean("王五", "4233"));
        list.add(new ContactBean("的法师大", "312312"));
        list.add(new ContactBean("全文企鹅要", "31231"));
        list.add(new ContactBean("爱的", "312312"));
        list.add(new ContactBean("青岛市", "4112"));
        list.add(new ContactBean("包含", "31"));
        ContactAdapter adapter = new ContactAdapter(this);
        mListView.setData(list).setAdapter(adapter).isIndexBG(true).build();
    }
}
