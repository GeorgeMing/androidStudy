package com.example.zzm.apptest;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.net.URLEncoder;

/**
 * Created by zzm on 2017/3/10.
 */

public class DataDownload {
    public DataDownload(String url, String file_name, Context context){
        String filename = "";
        Uri uri;
        try{
            filename = URLEncoder.encode(file_name,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        String idNumber = new DataBaseController(context).readidNumber();
        if(idNumber.length() < 12){
            uri = Uri.parse(ServerAction.DownloadServerAddr+"Administrator/teacher/teaDown.ashx?name="+filename);
        }else{
            uri = Uri.parse(ServerAction.DownloadServerAddr+"Administrator/stu/stuDown.ashx?name="+filename);
        }

        // uri 是你的下载地址，可以使用Uri.parse("http://")包装成Uri对象
        DownloadManager.Request req = new DownloadManager.Request(uri);

// 通过setAllowedNetworkTypes方法可以设置允许在何种网络下下载，
// 也可以使用setAllowedOverRoaming方法，它更加灵活
        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);

// 此方法表示在下载过程中通知栏会一直显示该下载，在下载完成后仍然会显示，
// 直到用户点击该通知或者消除该通知。还有其他参数可供选择
        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

// 设置下载文件存放的路径，同样你可以选择以下方法存放在你想要的位置。
// setDestinationUri
// setDestinationInExternalPublicDir
        req.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "test");

// 设置一些基本显示信息
        req.setTitle(file_name);
        req.setDescription("下载完后请点击打开");
//        req.setMimeType("application/vnd.android.package-archive");

// Ok go!
        DownloadManager dm = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
        dm.enqueue(req);
    }
}
