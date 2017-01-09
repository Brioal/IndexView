package com.brioal.index;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-8.
 */

public abstract class IndexBean {
    protected char mIndex = '#';//Index
    protected boolean isHead = false;

    public char getIndex() {
        return mIndex;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public abstract void setIndex();

    @Override
    public boolean equals(Object obj) {
        IndexBean bean = (IndexBean) obj;
        return mIndex == getIndex() && isHead() == bean.isHead();
    }
}
