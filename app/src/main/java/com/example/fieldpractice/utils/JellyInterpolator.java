package com.example.fieldpractice.utils;

import android.view.animation.LinearInterpolator;

/**
 * Created by JG on 2019/5/9.
 * 用于播放登录界面动画的插值器
 */

public class JellyInterpolator extends LinearInterpolator {
    private float factor;

    public JellyInterpolator() {
        this.factor = 0.15f;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input)
                * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}
