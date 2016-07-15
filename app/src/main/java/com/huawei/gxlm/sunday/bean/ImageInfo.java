package com.huawei.gxlm.sunday.bean;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.huawei.gxlm.sunday.utils.FileUtils;
import com.huawei.gxlm.sunday.utils.MimeUtils;

import java.io.ByteArrayOutputStream;


/**
 * YouJoin
 * Created by ZQ on 2015/11/12.
 */
public class ImageInfo {

    private String fileName ;
    private String mime ;
    private String imagePath;

    public ImageInfo(String filePath){
        this.imagePath = filePath;
        this.fileName = FileUtils.getFileName(filePath);
        this.mime = MimeUtils.guessMimeTypeFromExtension(FileUtils.getFileExtension(filePath));
    }
    public byte[] getValue() {
        if(imagePath == null){
            return new ByteArrayOutputStream().toByteArray();
        }
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
        if(bitmap == null){
            Log.e("",imagePath);
            return null;
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos) ;
        return bos.toByteArray();
    }

    public String getFileName() {
        return fileName;
    }

    public String getMime() {
        return mime;
    }

    public String getImagePath() {
        return imagePath;
    }

}
