package com.lznby.smartiot.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lznby.smartiot.R;

/**
 * @author Lznby
 * @time 2018/6/7 13:32
 * Class Note: 自定义TextView控件，密码及账号的EditText封装
 */
public class MyEditText extends LinearLayout {

    public static final int IS_TEXT = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
    public static final int IS_PASSWORD = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;


    private TextView mTextView = null;
    private EditText mEditText = null;
    private ImageButton mImageButton = null;
    private View mView = null;


    /**
     * 自定义EditText控件的构造方法
     * @param context
     */
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取控件布局文件
        LayoutInflater.from(context).inflate(R.layout.my_edit_text, this, true);
        //获取布局中的需要设置属性的子控件
        mTextView = (TextView) findViewById(R.id.text_view);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mImageButton = (ImageButton) findViewById(R.id.image_button);
        mView = (View) findViewById(R.id.view);

        //获取MyEditText在布局文件中的属性
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MyEditText);

        if (attributes != null) {
            //布局文件中设置的属性不为空则执行以下操作

            //设置标题说明文字
            String tv_title = attributes.getString(R.styleable.MyEditText_title_text);
            if (!TextUtils.isEmpty(tv_title)) {
                mTextView.setText(tv_title);
            }

            //设置hint说明文字
            String et_hint = attributes.getString(R.styleable.MyEditText_edit_text_hint);
            if (!TextUtils.isEmpty(et_hint)) {
                mEditText.setHint(et_hint);
            } else {
                mEditText.setHint("");
            }

            //设置文本类型
            boolean et_input_type = attributes.getBoolean(R.styleable.MyEditText_edit_text_input_type,false);
            if (et_input_type != true) {
                //普通文本显示
                mEditText.setInputType(IS_TEXT);
            } else {
                //密码形式显示
                mEditText.setInputType(IS_PASSWORD);
            }

            //设置ImageButton是否显示,默认为不显示
            boolean ib_visible = attributes.getBoolean(R.styleable.MyEditText_image_bg_visible,false);
            if (ib_visible) {
                mImageButton.setVisibility(VISIBLE);
            } else {
                mImageButton.setVisibility(GONE);
            }

            //设置ImageButton的图片
            int ib_image = attributes.getResourceId(R.styleable.MyEditText_image_bg_src,-1);
            if (ib_image != -1) {
                setImageButtonBackground(ib_image);
            } else {
                mImageButton.setVisibility(GONE);
            }

        }


        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    mView.setBackgroundResource(R.drawable.blue_line);
                } else {
                    mView.setBackgroundResource(R.drawable.gray_line);
                }
            }
        });


    }

    /**
     * 设置ImageButton的背景
     * @param ib_image
     */
    public void setImageButtonBackground(int ib_image) {
        mImageButton.setBackgroundResource(ib_image);
    }

    /**
     * 为ImageButton设置onClickListener
     * @param onClickListener
     */
    public void setImageButtonOnClickListener(OnClickListener onClickListener) {
        mImageButton.setOnClickListener(onClickListener);
    }

    /**
     * 判断当前输入类型是否为可见类型
     * @return 不可见返回true,反则返回false
     */
    public boolean getEditTextTypeIsPassword() {
        return mEditText.getInputType()==IS_PASSWORD;
    }

    /**
     * 设置输入类型是否可见,true设置为不可见,反则为可见。
     * @param tag
     */
    public void setEditTextTypeIsPassword(boolean tag) {
        if (tag) {
            mEditText.setInputType(IS_PASSWORD);
        } else {
            mEditText.setInputType(IS_TEXT);
        }

    }

    /**
     * 获取左侧标题的TextView控件
     * @return
     */
    public TextView getTextView() {
        return mTextView;
    }

    /**
     * 获取EditText控件
     * @return
     */
    public EditText getEditText() {
        return mEditText;
    }

    /**
     * 获取ImageButton控件
     * @return
     */
    public ImageButton getImageButton() {
        return mImageButton;
    }

    /**
     * 获取View控件
     * @return
     */
    public View getView() {
        return mView;
    }

}
