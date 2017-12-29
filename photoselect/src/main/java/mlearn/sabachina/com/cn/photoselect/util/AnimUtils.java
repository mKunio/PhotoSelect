package mlearn.sabachina.com.cn.photoselect.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zhc on 16/9/12.
 */
public class AnimUtils {
    public static void slideDownToShow(View container, View background) {
        container.setVisibility(View.VISIBLE);
        background.setVisibility(View.VISIBLE);

        float height = container.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(container, "translationY", -height, 0);
        animator.setDuration(200);
        animator.start();
    }

    public static void slideUpToClose(final View container, final View background) {
        float height = container.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(container, "translationY", 0, -height);
        animator.setDuration(200);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                background.setVisibility(View.GONE);
                container.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                background.setVisibility(View.GONE);
                container.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    public static void slideUpToShow(View container, View background) {
        background.setVisibility(View.VISIBLE);
        float height = container.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(container, "translationY",
                height, 0);
        animator.setDuration(200);
        animator.start();
    }

    public static void slideDownToClose(View container, final View background) {
        float height = container.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(container, "translationY", 0, height);
        animator.setDuration(200);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                background.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                background.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    public static void slideUpToLeft(View container , View child) {
        final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();
        float width = child.getWidth() + lp.leftMargin;
        ObjectAnimator animator = ObjectAnimator.ofFloat(container, "translationX", -width);
        animator.setDuration(300);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.start();
    }

    public static void slideUpToRight(View container , View child) {
        float width = child.getWidth();
        ObjectAnimator animator = ObjectAnimator.ofFloat(container, "translationX", 0);
        animator.setDuration(300);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.start();
    }

    public static void scaleXToWrapContent(final View container, int start , int end) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(container, "scaleX", 1f,2f);
//        animator.setDuration(1000);
//        animator.start();
        ValueAnimator va = ValueAnimator.ofInt(start, end);
        final ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int w = (int) animation.getAnimatedValue();
                layoutParams.width = w;
                container.setLayoutParams(layoutParams);
                container.requestLayout();
            }
        });

        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        va.setDuration(300);
        va.start();
    }

    public static void scaleXToOrigin(View container) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f,
                1.1f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(container, pvhX).setDuration(1000).start();
    }
}
