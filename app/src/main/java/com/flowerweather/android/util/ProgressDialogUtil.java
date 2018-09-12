package com.flowerweather.android.util;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogUtil {
    private static ProgressDialog progressDialog;

    /**
     * 显示进度对话框
     */
    public static void showProgressDialog(Context context){
        if (progressDialog==null){
            progressDialog=new ProgressDialog(context);
            progressDialog.setMessage("正在获取信息……");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    public static void closeProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }
}
