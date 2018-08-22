package com.lznby.smartiot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.lznby.smartiot.R;
import com.lznby.smartiot.network.AppState;
import com.lznby.smartiot.utils.CacheUtils;
/**
 * @author Lznby
 * @time 2018/6/20 13:06
 * Class Note: 启动页的Activity
 */
public class SplashActivity extends AppCompatActivity {
    /**
     * 静态常量
     */
    public static final String START_MAIN = "start_main";

    private RelativeLayout rl_splahs_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splahs_root = (RelativeLayout) findViewById(R.id.rl_splahs_root);

        //渐变动画,缩放动画，旋转动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        //        aa.setDuration(500);//持续播放时间
        aa.setFillAfter(true);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        //        sa.setDuration(500);
        sa.setFillAfter(true);

        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        //        ra.setDuration(500);
        ra.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        //添加三个动画没有先后顺序,便于同时播放动画
        set.addAnimation(ra);
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.setDuration(2000);

        rl_splahs_root.startAnimation(set);

        set.setAnimationListener(new MyAnimationListener());

    }

    class MyAnimationListener implements Animation.AnimationListener {

        /**
         * 当动画开始播放的时候回调这个方法
         *
         * @param animation
         */
        @Override
        public void onAnimationStart(Animation animation) {

        }

        /**
         * 当动画播放结束的时候回调这个方法
         *
         * @param animation
         */
        @Override
        public void onAnimationEnd(Animation animation) {

            //判断是进入过主页面
            boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, AppState.LOGIN_STATE);
            Intent intent;
            if (isStartMain) {
                //如果进入过主页面，直接进入主页面
                intent = new Intent(SplashActivity.this, MainActivity.class);

            } else {
                // 如果没有进入过主页面，进入引导页面
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            startActivity(intent);

            //关闭Splash页面
            finish();


            //           Toast.makeText(SplashActivity.this,"动画播放完成了", Toast.LENGTH_SHORT).show();
        }

        /**
         * 当动画播放的时候回调这个方法
         *
         * @param animation
         */
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
