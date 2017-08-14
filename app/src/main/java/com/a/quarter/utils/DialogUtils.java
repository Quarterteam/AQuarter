package com.a.quarter.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.presenter.main.MainPresenter;

/**
 * Created by acer on 2017/8/8.
 */
public class DialogUtils {

    public static SignEditDialog getSignEditDialog(final Context context, final MainPresenter presenter) {
        final SignEditDialog dialog = new SignEditDialog(context, R.style.Dialog_FullScreen_DarkDimOutside);
        dialog.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到EditText上的内容，调用接口
                String newSign = dialog.etSign.getText().toString().trim();
                presenter.editSign(newSign, App.getUser().userId);
            }
        });
        dialog.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        //dialog.init();
        return dialog;
    }

    //修改个性签名的对话框
    public static class SignEditDialog extends Dialog{
        public Button btnOk;
        public Button btnCancel;
        public EditText etSign;

        public SignEditDialog(Context context, int themeResId) {
            super(context, themeResId);

            View view = View.inflate(context, R.layout.layout_sign_edit_dialog, null);
            etSign = (EditText) view.findViewById(R.id.et_sign);
            btnOk = (Button) view.findViewById(R.id.btn_ok);
            btnCancel = (Button) view.findViewById(R.id.btn_cancel);
            setContentView(view);

            init();
        }

        private void init(){
            //使Dialog的宽高能显示出想要的效果，不被系统自动调整（dialog使用的style里需要设置android:windowBackground）
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;//这里是让dialog宽能全屏，不然dialog左右距离屏幕边缘默认会有一段距离
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.CENTER;
            getWindow().setAttributes(params);

            //自己设置dialog内容距离屏幕边缘的距离（也可以在dialog使用的style里设置android:padding）
            getWindow().getDecorView().setPadding(100, 0, 100, 0);
        }

    }

}
