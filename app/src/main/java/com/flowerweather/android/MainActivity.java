package com.flowerweather.android;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.flowerweather.android.status.AddressStatus;
import com.flowerweather.android.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public LocationClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //定位部分
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        //创建空的List集合
        List<String> permissionList=new ArrayList<>();
        //判断是否授权
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission
                .ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            //未授权则添加到List集合中
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission
                .READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission
                .WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            //将List转换为数组
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            //一次性申请权限
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }else {
            requestLocation();
        }

        TextView weatherContentText= (TextView) findViewById(R.id.weather_content_text);
        String flowerContent=generateFlowerContent();
        weatherContentText.setText(flowerContent);

//        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
//        //设置menu图标颜色为图标本身的颜色
//        navigationView.setItemIconTintList(null);
//        //动态添加menu的Item
//        navigationView.getMenu().add(R.id.nav_g,1,1,"测试");
    }

    private void requestLocation(){
        initLocation();
        //开始定位
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        option.setOpenGps(true);
        mLocationClient.setLocOption(option);
    }

    private String generateFlowerContent(){
        StringBuilder flowerContent=new StringBuilder();
        for (int i=0;i<3000;i++){
            flowerContent.append("先试试");
        }
        return flowerContent.toString();
    }

    public class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Utility.queryMyCityNow(MainActivity.this, AddressStatus.getCityAdd+bdLocation.getLongitude()+","
                    +bdLocation.getLatitude()+"&group=cn&number=1");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    //循环判断申请的所有权限
                    for (int result:grantResults){
                        if (result!=PackageManager.PERMISSION_DENIED){
                            AlertDialog.Builder dialog=new AlertDialog.Builder(this);
                            //设置内容
                            dialog.setMessage("必须同意所有权限才能使用本程序");
                            //可否取消
                            dialog.setCancelable(false);
                            //设置确定按钮点击事件
                            dialog.setPositiveButton("确认",new DialogInterface.
                                    OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            //显示对话框
                            dialog.show();
                            //任何一个权限被拒绝，则关闭程序
                            finish();
                            return;
                        }
                    }
                    //所有权限都被同意后开始定位
                    requestLocation();
                }else {
                    AlertDialog.Builder dialog=new AlertDialog.Builder(this);
                    //设置内容
                    dialog.setMessage("发生未知错误");
                    //可否取消
                    dialog.setCancelable(false);
                    //设置确定按钮点击事件
                    dialog.setPositiveButton("确认",new DialogInterface.
                            OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    //显示对话框
                    dialog.show();
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
