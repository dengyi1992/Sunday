package com.huawei.gxlm.sunday;

/**
 * Created by deng on 16-7-14.
 */
import java.util.Hashtable;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


public class AutoNewLineLinearLayout extends LinearLayout {
    private int mLeft, mRight, mTop, mBottom;
    private Hashtable map = new Hashtable();

    public AutoNewLineLinearLayout(Context context) {
        super(context);
    }

    public AutoNewLineLinearLayout(Context context, int horizontalSpacing,
                                   int verticalSpacing) {
        super(context);
    }

    public AutoNewLineLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int mCount = getChildCount();
        int mX = 0;
        int mY = 0;
        mLeft = 0;
        mRight = 0;
        mTop = 5;
        mBottom = 0;

        int j = 0;

        View lastview = null;
        for (int i = 0; i < mCount; i++) {
            final View child = getChildAt(i);

            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            // 姝ゅ澧炲姞onlayout涓殑鎹㈣鍒ゆ柇锛岀敤浜庤绠楁墍闇?殑楂樺害
            int childw = child.getMeasuredWidth();
            int childh = child.getMeasuredHeight();
            mX += childw; // 灏嗘瘡娆″瓙鎺т欢瀹藉害杩涜缁熻鍙犲姞锛屽鏋滃ぇ浜庤瀹氱殑楂樺害鍒欓渶瑕佹崲琛岋紝楂樺害鍗砊op鍧愭爣涔熼渶閲嶆柊璁剧疆

            Position position = new Position();
            mLeft = getPosition(i - j, i);
            mRight = mLeft + child.getMeasuredWidth();
            if (mX >= mWidth) {
                mX = childw;
                mY += childh;
                j = i;
                mLeft = 0;
                mRight = mLeft + child.getMeasuredWidth();
                mTop = mY + 5;
                // PS锛氬鏋滃彂鐜伴珮搴﹁繕鏄湁闂灏卞緱鑷繁鍐嶇粏璋冧簡
            }
            mBottom = mTop + child.getMeasuredHeight();
            mY = mTop; // 姣忔鐨勯珮搴﹀繀椤昏褰?鍚﹀垯鎺т欢浼氬彔鍔犲埌涓?捣
            position.left = mLeft;
            position.top = mTop + 3;
            position.right = mRight;
            position.bottom = mBottom;
            map.put(child, position);
        }
        setMeasuredDimension(mWidth, mBottom);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(1, 1); // default of 1px spacing
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            Position pos = (Position)map.get(child);
            if (pos != null) {
                child.layout(pos.left, pos.top, pos.right, pos.bottom);
            } else {
                Log.i("MyLayout", "error");
            }
        }
    }

    private class Position {
        int left, top, right, bottom;
    }

    public int getPosition(int IndexInRow, int childIndex) {
        if (IndexInRow > 0) {
            return getPosition(IndexInRow - 1, childIndex - 1)
                    + getChildAt(childIndex - 1).getMeasuredWidth() + 8;
        }
        return getPaddingLeft();
    }
}
