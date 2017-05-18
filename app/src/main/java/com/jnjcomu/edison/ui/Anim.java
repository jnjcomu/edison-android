package com.jnjcomu.edison.ui;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-05-12
 */

public class Anim {

    public void startAnim(Context mContext, ImageView img, int anim) {
        Animation mAnim = AnimationUtils.loadAnimation(mContext, anim);
        img.startAnimation(mAnim);
    }

    public void startAnim(Context mContext, ImageView img, int anim, LogoInterpolator li) {
        Animation mAnim = AnimationUtils.loadAnimation(mContext, anim);
        mAnim.setInterpolator(li);
        img.startAnimation(mAnim);
    }

    public void cancelAnim(ImageView img) {
        Animation anim = img.getAnimation();
        if(anim!=null)
            img.getAnimation().cancel();
    }

}
