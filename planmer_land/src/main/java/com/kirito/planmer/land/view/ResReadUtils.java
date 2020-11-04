package com.kirito.planmer.land.view;


import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description:
 * @Author: kirito
 * @CreatTime: 2020/11/2
 * @LastModify: kirito
 * @LastModifyTime: 2020/11/2
 * @LastCheckedBy: kirito
 */
public class ResReadUtils {
    /**
     * 读取资源
     * @param resourceId
     */
    public static String readResource(Context context,int resourceId) {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            InputStreamReader streamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String textLine;
            while ((textLine = bufferedReader.readLine()) != null) {
                builder.append(textLine);
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
