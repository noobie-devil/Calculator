package hcmute.edu.vn.calculator_18;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class RightAlignedHorizontalScrollView extends HorizontalScrollView {

    public RightAlignedHorizontalScrollView(Context context) {
        super(context);
    }

    public RightAlignedHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RightAlignedHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(getChildAt(0).getMeasuredWidth(), 0);
    }
}