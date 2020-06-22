package com.example.android.nasapod;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import static android.content.Context.DOWNLOAD_SERVICE;

@SuppressWarnings("ConstantConditions")
public final class ImageUrlDownloadUtil {
    public static final int DEFAULT_REQUEST_CODE_FOR_WRITE_PERMISSION = 42;

    private static void startDownloading(@NonNull final Context context, @NonNull final String url) {
        String imageName = getEndingName(url);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(url));

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, imageName);
        DownloadManager dm = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        dm.enqueue(request);
    }

    private static String getEndingName(String url) {
        String returnName = "";
        String delims = "/";
        String[] tokens = url.split(delims);
        if (tokens.length > 0) {
            returnName = tokens[tokens.length - 1];
        }
        return returnName;
    }

    public static void attemptToDownload(final @Nullable Activity activityContext, final @Nullable String downloadUrl, final int requestCode) {
        if (activityContext != null && !TextUtils.isEmpty(downloadUrl)) {

            if (hasPermission(activityContext)) {
                startDownloading(activityContext, downloadUrl);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activityContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activityContext);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setMessage(R.string.permission_write_external_rationale_message);
                    alertBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activityContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
                        }
                    });
                    alertBuilder.create().show();
                } else {
                    ActivityCompat.requestPermissions(activityContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
                }
            }
        }
    }

    private static boolean hasPermission(@NonNull final Context context) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
}