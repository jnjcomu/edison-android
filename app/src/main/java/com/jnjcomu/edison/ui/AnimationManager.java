package com.jnjcomu.edison.ui;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-05-12
 */

@EBean
public class AnimationManager {
    @RootContext
    Context context;

    public void startAnim(View view, @AnimRes int animRes) {
        Animation anim = AnimationUtils.loadAnimation(context, animRes);

        view.startAnimation(anim);
    }

    public void startAnim(View view, @AnimRes int animRes, Interpolator interpolator) {
        Animation anim = AnimationUtils.loadAnimation(context, animRes);
        anim.setInterpolator(interpolator);

        view.startAnimation(anim);
    }

    public void restartAnim(View view, @AnimRes int animRest) {
        cancelAnim(view);
        startAnim(view, animRest);
    }

    public void restartAnim(View view, @AnimRes int animRest, Interpolator interpolator) {
        cancelAnim(view);
        startAnim(view, animRest, interpolator);
    }

    public void cancelAnim(View v) {
        Animation anim = v.getAnimation();
        if (anim != null) {
            anim.cancel();
        }
    }
}
