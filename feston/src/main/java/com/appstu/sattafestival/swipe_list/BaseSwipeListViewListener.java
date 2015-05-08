package com.appstu.sattafestival.swipe_list;

import android.view.View;
import android.widget.AdapterView;

public class BaseSwipeListViewListener implements SwipeListViewListener {
    @Override
    public void onOpened(int position, boolean toRight) {

    }

    @Override
    public void onClosed(int position, boolean fromRight) {

    }

    @Override
    public void onListChanged() {

    }

    @Override
    public void onMove(int position, float x, View backView) {

    }

    @Override
    public void onStart(int position, int action, boolean right, View view) {

    }

    @Override
    public void onStartClose(int position, boolean right) {

    }

    @Override
    public void onClickFrontView(int position, AdapterView adapterView) {

    }

    @Override
    public void onClickBackView(int position) {

    }

    @Override
    public void onDismiss(int[] reverseSortedPositions) {

    }

    @Override
    public int onChangeSwipeMode(int position) {
        return -1;
    }

    @Override
    public void onChoiceChanged(int position, boolean selected) {

    }

    @Override
    public void onChoiceStarted() {

    }

    @Override
    public void onChoiceEnded() {

    }

    @Override
    public void onFirstListItem() {

    }

    @Override
    public void onLastListItem() {

    }

    @Override
    public void onEnd(View view) {

    }
}
