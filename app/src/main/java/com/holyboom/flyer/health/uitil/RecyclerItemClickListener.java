package com.holyboom.flyer.health.uitil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by flyer on 15/3/20.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener{
    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public RecyclerItemClickListener(Context context,
                                     final RecyclerView recyclerView, OnItemClickListener listener) {
        mListener = listener;
        // 识别并处理手势
        mGestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        // 轻击触摸屏后,弹起，必须返回true，否则无法触发单击
                        return true;
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null
                && mGestureDetector.onTouchEvent(e)) {
            // 触发单击事件
            mListener.onItemClick(childView, rv.getChildPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }
}
