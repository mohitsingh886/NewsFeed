package io.github.vishalecho.android.assignment.newsfeed.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.github.vishalecho.android.assignment.newsfeed.R;

public abstract class BaseView extends FrameLayout {

    protected String type = "default";
    protected View view;

    public BaseView(@NonNull Context context) {
        this(context, null);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
        init(context, attrs);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @NonNull AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseView, 0, 0);
            type = typedArray.getString(R.styleable.BaseView_type);
            typedArray.recycle();
        }
        initUI(type);
        viewInflated(view, type, attrs);
    }

    protected abstract void viewInflated(View view, String type, AttributeSet attrs);

    protected abstract @LayoutRes int provideLayoutId(String type);

    public void initUI(String type) {
        int viewId = provideLayoutId(type);
        if (viewId != -1) {
            removeAllViews();
            view = LayoutInflater.from(getContext()).inflate(provideLayoutId(type), null);
            addView(view);
        }
    }

    public View getView() {
        return view;
    }
}
