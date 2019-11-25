package com.qmui.android.util;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import com.qq.e.o.ads.v2.ads.banner.BannerAD;
import com.qq.e.o.ads.v2.ads.banner.BannerADListener;
import com.qq.e.o.ads.v2.ads.interstitial.InterstitialAD;
import com.qq.e.o.ads.v2.ads.interstitial.InterstitialADListener;
import com.qq.e.o.ads.v2.ads.nativ.NativeAD;
import com.qq.e.o.ads.v2.ads.nativ.NativeADListener;
import com.qq.e.o.ads.v2.ads.splash.SplashAD;
import com.qq.e.o.ads.v2.ads.splash.SplashADListener;
import com.qq.e.o.ads.v2.ads.video.InterstitialVideoAD;
import com.qq.e.o.ads.v2.ads.video.InterstitialVideoADListener;
import com.qq.e.o.ads.v2.ads.video.RewardVideoAD;
import com.qq.e.o.ads.v2.ads.video.RewardVideoADListener;
import com.qq.e.o.ads.v2.error.AdError;

/**
 * CreateName : Z-JC
 * Date : 2019/10/25
 * Describe :
 */
public class AdManager implements SplashADListener, BannerADListener, NativeADListener, RewardVideoADListener {

    private String TAG = this.getClass().getSimpleName();

    public SplashAD splashAD;
    public InterstitialAD interstitialAD;
    public InterstitialVideoAD videoAD;

    private static volatile AdManager instance = null;

    public static AdManager getInstance() {
        if (instance == null) {
            synchronized (AdManager.class) {
                if (instance == null) {
                    instance = new AdManager();
                }
            }
        }
        return instance;
    }

    /**
     * 拉取开屏广告
     *
     * @param activity    展示广告的activity
     * @param adContainer 展示广告的大容器
     */
    public void showSplashAd(Activity activity, ViewGroup adContainer) {
        if (splashAD != null) {
            splashAD = null;
        }
        splashAD = new SplashAD(activity, adContainer, this);
    }

    /**
     * 拉取开屏广告
     *
     * @param activity    展示广告的activity
     * @param adContainer 展示广告的大容器
     */
    public void showSplashAd(final Activity activity, final ViewGroup adContainer, final SplashADListener listener) {
        if (splashAD != null) {
            splashAD = null;
        }
        splashAD = new SplashAD(activity, adContainer, listener);
    }

    /**
     * 拉取原生广告
     *
     * @param activity    展示广告的activity
     * @param adContainer 展示广告的大容器
     */
    public void showNativeAD(final Activity activity, final ViewGroup adContainer) {
        NativeAD nativeAD = new NativeAD(activity, adContainer, AdManager.this);
        nativeAD.loadAD();
    }

    /**
     * 拉取原生广告
     *
     * @param activity    展示广告的activity
     * @param adContainer 展示广告的大容器
     */
    public void showNativeAD(final Activity activity, final ViewGroup adContainer, final NativeADListener listener) {
        NativeAD nativeAD = new NativeAD(activity, adContainer, listener);
        nativeAD.loadAD();
    }

    /**
     * 拉取Banaer广告
     *
     * @param activity    展示广告的activity
     * @param adContainer 展示广告的大容器
     */
    public void showBannerAD(final Activity activity, final ViewGroup adContainer) {
        BannerAD bannerAD = null;
        if (bannerAD != null) {
            bannerAD.destroy();
            bannerAD = null;
        }
        bannerAD = new BannerAD(activity, 60, adContainer, AdManager.this);
        bannerAD.loadAD();
    }

    /**
     * 拉取Banaer广告
     *
     * @param activity    展示广告的activity
     * @param adContainer 展示广告的大容器
     */
    public void showBannerAD(final Activity activity, final ViewGroup adContainer, final BannerADListener listener) {
        BannerAD bannerAD = null;
        if (bannerAD != null) {
            bannerAD.destroy();
            bannerAD = null;
        }
        bannerAD = new BannerAD(activity, 60, adContainer, listener);
        bannerAD.loadAD();
    }

    /**
     * 拉取插屏广告
     *
     * @param activity
     */
    public void showInterstitialAD(final Activity activity) {
        getIAD(activity, new InterstitialADListener() {
            @Override
            public void onADReceive() {
                interstitialAD.showAD();
            }

            @Override
            public void onADExposure() {

            }

            @Override
            public void onADClicked() {
            }

            @Override
            public void onADClosed() {
            }

            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailed(int i, AdError adError) {

            }
        });
        interstitialAD.loadAD();
    }

    public InterstitialAD getIAD(Activity activity, InterstitialADListener adListener) {
        if (interstitialAD != null) {
            interstitialAD.closeAD();
            interstitialAD.destroy();
            interstitialAD = null;
        }
        interstitialAD = new InterstitialAD(activity, adListener);
        return interstitialAD;
    }

    public InterstitialVideoAD getVideoAd(Activity activity, boolean isPreload) {
        if (videoAD != null) {
            videoAD.destroy();
            videoAD = null;
        }
        videoAD = new InterstitialVideoAD(activity, new InterstitialVideoADListener() {
            @Override
            public void onADLoad() {

            }

            @Override
            public void onVideoCached() {

            }

            @Override
            public void onADShow() {

            }

            @Override
            public void onADExpose() {

            }

            @Override
            public void onReward() {

            }

            @Override
            public void onADClick() {

            }

            @Override
            public void onVideoComplete() {

            }

            @Override
            public void onADClose() {

            }

            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailed(int i, AdError adError) {

            }

            @Override
            public void onSkippedVideo() {

            }

            @Override
            public void onPreload() {

            }
        });
        if (isPreload) {
            videoAD.preloadAD();
        }
        return videoAD;
    }

    /**
     * 激励视频广告
     *
     * @param activit
     */
    public void getRewardVideoAd(Activity activit) {
        RewardVideoAD rewardVideoAD = new RewardVideoAD(activit, this);
        rewardVideoAD.loadAD();
    }

    /**
     * 激励视频广告
     *
     * @param activit
     */
    public RewardVideoAD getRewardVideoAd(Activity activit, RewardVideoADListener rewardVideoADListener) {
        RewardVideoAD rewardVideoAD = new RewardVideoAD(activit, rewardVideoADListener);
        return rewardVideoAD;
    }


    @Override
    public void onADLoad() {

    }

    @Override
    public void onVideoCached() {

    }

    @Override
    public void onADShow() {

    }

    @Override
    public void onADExpose() {

    }

    @Override
    public void onReward() {

    }

    @Override
    public void onADClick() {

    }

    @Override
    public void onVideoComplete() {

    }

    @Override
    public void onADClose() {
        Log.e(TAG, "-------------> + onADClose");
    }

    @Override
    public void onNoAD(AdError adError) {
        Log.e(TAG, "-------------> + AdError:" + adError.getErrorCode() + "," + adError.getErrorMsg());
    }

    @Override
    public void onSuccess(int i) {

    }

    @Override
    public void onFailed(int i, AdError adError) {

    }

    @Override
    public void onSkippedVideo() {

    }

    @Override
    public void onPreload() {

    }

    @Override
    public void onADPresent() {
        Log.e(TAG, "-------------> + onADPresent");
    }

    @Override
    public void onADClicked() {
        Log.e(TAG, "-------------> + onADClicked");
    }

    @Override
    public void onADSkip() {
        Log.e(TAG, "-------------> + onADSkip");
    }

    @Override
    public void onADTimeOver() {
        Log.e(TAG, "-------------> + onADTimeOver");
    }
}
