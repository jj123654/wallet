package com.yns.wallet.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luck.picture.lib.utils.ToastUtils;
import com.yns.wallet.R;
import com.yns.wallet.base.BaseActivity;

/**
 * dialog样式
 */
public class CommonCenterDialog {
    private Context context;
    private AlertDialog dialog;

    public CommonCenterDialog(Context context) {
        this.context = context;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context, R.style.Dialog);
        dialog = dialogBuilder.create();
    }


    /**
     * 通用中间弹窗
     */
    public AlertDialog showCenterTipsDialog(String content, String left, String right, View.OnClickListener leftClickListener, View.OnClickListener rightClickListener) {
        View contentView = View.inflate(context, R.layout.dialog_common_tips, null);

        TextView contentTv = contentView.findViewById(R.id.content_tv);

        if (!TextUtils.isEmpty(content)) {
            contentTv.setText(content);
        }

        TextView leftTv = contentView.findViewById(R.id.left_tv);
        TextView rightTv = contentView.findViewById(R.id.right_tv);

        if (!TextUtils.isEmpty(left)) {
            leftTv.setText(left);
        }
        if (!TextUtils.isEmpty(right)) {
            rightTv.setText(right);
        }
        leftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (leftClickListener != null) {
                    leftClickListener.onClick(view);
                }
            }
        });
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (rightClickListener != null) {
                    rightClickListener.onClick(view);
                }

            }
        });

        showDialog(contentView);
        return dialog;
    }

    /**
     * 通用中间带输入框的弹窗
     */
    public AlertDialog showCenterEditDialog(boolean isPassword, String title, String content, String left, String right, View.OnClickListener leftClickListener, EditCallBackListener editCallBackListener) {
        View contentView = View.inflate(context, R.layout.dialog_common_edit, null);

        TextView titleTv = contentView.findViewById(R.id.title_tv);

        EditText inputEt = contentView.findViewById(R.id.input_et);


        ImageView eyeImg = contentView.findViewById(R.id.iv_pwd_eye);

        TextView leftTv = contentView.findViewById(R.id.left_tv);
        TextView rightTv = contentView.findViewById(R.id.right_tv);

        if (isPassword) {
            eyeImg.setVisibility(View.VISIBLE);
        } else {
            eyeImg.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
        }
        if(!TextUtils.isEmpty(content)){
            inputEt.setHint(content);
        }
        if (!TextUtils.isEmpty(left)) {
            leftTv.setText(left);
        }
        if (!TextUtils.isEmpty(right)) {
            rightTv.setText(right);
        }
        leftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (leftClickListener != null) {
                    leftClickListener.onClick(view);
                }
            }
        });
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(inputEt.getText().toString())) {
                    ToastUtils.showToast(context, context.getString(R.string.please_enter_a_name));
                } else {
                    dialog.dismiss();
                    if (editCallBackListener != null) {
                        editCallBackListener.onEditCallBackLisnter(inputEt.getText().toString());
                    }
                }

            }
        });

        showDialog(contentView);
        return dialog;
    }

    public interface EditCallBackListener {
        void onEditCallBackLisnter(String edit);
    }

    private void showDialog(View view) {
        dialog.setView(view);
        dialog.show();
        if (context instanceof BaseActivity) {
            WindowManager windowManager = ((BaseActivity) context).getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = (int) (display.getWidth() * 0.8); //设置宽度
            dialog.getWindow().setAttributes(lp);
        }
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
    }


}