package com.wg.aidlservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.wg.aidlsample.ICat;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: Wangg
 * @Name：AidlService
 * @Description:  Aidl的Service服务 类似服务器段
 * 备注：aidl文件的客户端和服务器端 包名必须相同
 * @Created on:2017/3/3  16:44.
 */


//Aidl的Service服务

public class AidlService extends Service {

    private CatBinder catBinder;

    Timer timer = new Timer();
    String[] colors = new String[]{
            "红色", "黄色", "黑色", "北京", "上海", "深圳", "广州"
    };

    double[] weights = new double[]{
            2.3, 3.1, 1.58, 100, 200, 300, 400
    };

    private String color;
    private double weight;


    @Override
    public IBinder onBind(Intent intent) {
        return catBinder;
    }


    public class CatBinder extends ICat.Stub {

        @Override
        public String getColor() throws RemoteException {
            return color;
        }

        @Override
        public double getWeight() throws RemoteException {
            return weight;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        catBinder = new CatBinder();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int rand = new Random().nextInt(7);
                color = colors[rand];
                weight = weights[rand];
                Log.d("ddf", "_______" + rand);
            }
        }, 0, 800);
    }

    @Override
    public void onDestroy() {
        timer.cancel();
    }
}
