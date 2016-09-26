package com.bc.numberstyle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Created by Administrator on 2016/9/26 0026.
 */
public class NumberAddSubView extends LinearLayout implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Button mAddBtn;
    private Button mSubBtn;
    private TextView mNumTv;
    private int value;
    private int minValue;
    private int maxValue;
    private OnButtonClickListener mOnButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.mOnButtonClickListener = onButtonClickListener;
    }
    public NumberAddSubView(Context context) {
        this(context, null);
    }

    public NumberAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        initView();
        if (attrs != null) {
            TintTypedArray tta = TintTypedArray.obtainStyledAttributes(context,
                    attrs, R.styleable.NumberAddSubView, defStyleAttr, 0);
            int val = tta.getInt(R.styleable.NumberAddSubView_value, 0);
            setValue(val);
            int minValue = tta.getInt(R.styleable.NumberAddSubView_minValue, 0);
            setMinValue(minValue);
            int maxValue = tta.getInt(R.styleable.NumberAddSubView_maxValue, 0);
            setMaxValue(maxValue);
            Drawable addBtnDrawable = tta.getDrawable(R.styleable.NumberAddSubView_addBtnBg);
            setAddBtnBackground(addBtnDrawable);
            Drawable subBtnDrawable = tta.getDrawable(R.styleable.NumberAddSubView_subBtnBg);
            setsubBtnBackground(subBtnDrawable);
            int numTvDrawable=tta.getResourceId(R.styleable.NumberAddSubView_numTvBg,android.R.color.transparent);
            setNumTvBackground(numTvDrawable);
            tta.recycle();
        }
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn) {//点击+
            numAdd();
            if (mOnButtonClickListener != null) {
                mOnButtonClickListener.onButtonAddClick(v, value);
            }
        } else if (v.getId() == R.id.sub_btn) {//点击 -
            numSub();
            if (mOnButtonClickListener != null) {
                mOnButtonClickListener.onButtonSubClick(v, value);
            }
        }
    }

    private void numAdd() {
        if (value < maxValue) {
            value++;
        }
        mNumTv.setText(value + "");
    }

    private void numSub() {
        if (value > minValue) {
            value--;
        }
        mNumTv.setText(value + "");
    }

    public interface OnButtonClickListener {
        void onButtonAddClick(View view, int value);

        void onButtonSubClick(View view, int value);

    }
    private void setsubBtnBackground(Drawable subBtnDrawable) {
        this.mSubBtn.setBackground(subBtnDrawable);
    }

    private void setAddBtnBackground(Drawable addBtnDrawable) {
       this.mAddBtn.setBackground(addBtnDrawable);
    }
private void setNumTvBackground(int drawableId){
    this.mNumTv.setBackgroundResource(drawableId);
}
    private void initView() {
        View view = mInflater.inflate(R.layout.wieght_number_add_sub, this, true);
        mAddBtn = (Button) view.findViewById(R.id.add_btn);
        mSubBtn = (Button) view.findViewById(R.id.sub_btn);
        mNumTv = (TextView) view.findViewById(R.id.num_tv);
        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);
    }

    public int getValue() {
        String val = mNumTv.getText().toString();
        if (val != null && !"".equals(val)) {
            this.value = Integer.parseInt(val);
        }
        return value;
    }

    public void setValue(int value) {
        mNumTv.setText(value + "");
        this.value = value;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
