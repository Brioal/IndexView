package com.brioal.indexview;

import com.brioal.index.ChineseToCharUtil;
import com.brioal.index.IndexBean;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-8.
 */

public class ContactBean extends IndexBean {
    private String mName;
    private String mPhone;

    public ContactBean() {
    }

    public ContactBean(String name, String phone) {
        mName = name;
        mPhone = phone;
        setIndex();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        setIndex();
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    @Override
    public void setIndex() {
        mIndex = ChineseToCharUtil.getFirstLetter(mName.charAt(0));
    }
}
