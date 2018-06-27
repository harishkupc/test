package in.syncro.grabit.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.syncro.grabit.R;

public class OfferView extends RelativeLayout {

    TextView leftText, rightText, desc;
    RecyclerView recyclerView;
    LinearLayout header;

    String tag = null;

    public OfferView(Context context) {
        super(context);
        init(null);
    }

    public OfferView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public OfferView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public OfferView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet set) {

        inflate(getContext(), R.layout.offer_view, this);
        leftText = findViewById(R.id.leftText);
        rightText = findViewById(R.id.rightText);
        desc = findViewById(R.id.desc);
        recyclerView = findViewById(R.id.recycler);
        header = findViewById(R.id.header);

        recyclerView.setNestedScrollingEnabled(false);

        header.setVisibility(GONE);
        leftText.setVisibility(GONE);
        rightText.setVisibility(GONE);
        desc.setVisibility(GONE);

        if (set != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.OfferView);

            String t = typedArray.getString(R.styleable.OfferView_tag);
            if (t != null) {
                setCustomTag(t);
            }

            String left = typedArray.getString(R.styleable.OfferView_leftText);
            if (left != null) {
                setLeftText(left);
            }

            String right = typedArray.getString(R.styleable.OfferView_rightText);
            if (right != null) {
                setRightText(right);
            }

            String description = typedArray.getString(R.styleable.OfferView_description);
            if (description != null) {
                setDescripsion(description);
            }

            int leftColor = typedArray.getColor(R.styleable.OfferView_leftTextColor, 0);
            if (leftColor != 0) {
                setLeftTextColor(leftColor);
            }

            int rightColor = typedArray.getColor(R.styleable.OfferView_rightTextColor, 0);
            if (rightColor != 0) {
                setRightTextColor(rightColor);
            }

            typedArray.recycle();
        }

    }

    public void setCustomTag(String t) {
        tag = t;
    }

    public void setAdapterManager(RecyclerView.Adapter adapter,
                                  RecyclerView.LayoutManager manager) {
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void setLeftText(int text) {
        leftText.setText(getContext().getString(text));
    }

    public void setLeftText(String text) {
        leftText.setText(text);
        leftText.setVisibility(VISIBLE);
        header.setVisibility(VISIBLE);
    }

    public void setRightText(int text) {
        rightText.setText(getContext().getString(text));
    }

    public void setRightText(String text) {
        rightText.setText(text);
        rightText.setVisibility(VISIBLE);
        header.setVisibility(VISIBLE);
    }

    public void setLeftOnClickListener(final OnClicker listener) {
        leftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(tag, false);
            }
        });
    }

    public void setRightOnClickListener(final OnClicker listener) {
        rightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(tag, true);
            }
        });
    }

    public void setDescripsion(int text) {
        desc.setText(getContext().getString(text));
    }

    public void setDescripsion(String text) {
        desc.setText(text);
        desc.setVisibility(VISIBLE);
        header.setVisibility(VISIBLE);
    }

    public void setLeftTextColor(int color) {
        leftText.setTextColor(color);
    }

    public void setRightTextColor(int color) {
        rightText.setTextColor(color);
    }

    public interface OnClicker {
        void onClick(String tag, boolean isRight);
    }
}
