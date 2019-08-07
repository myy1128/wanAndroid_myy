package com.example.wanandroid_myy.util;

import android.graphics.Color;

import java.util.Random;

public class ContextCompat {
    public static int getRandomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red;
        int green;
        int blue;
        if (false) {
//            150-255
            red = random.nextInt(105) + 150;
            green = random.nextInt(105) + 150;
            blue = random.nextInt(105) + 150;
        } else {
            red = random.nextInt(190);
            green = random.nextInt(190);
            blue = random.nextInt(190);
        }
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue);
    }
}
