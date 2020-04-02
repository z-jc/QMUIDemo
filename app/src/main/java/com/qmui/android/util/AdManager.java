package com.qmui.android.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import com.qq.e.o.HXAdConfig;
import com.qq.e.o.ads.v2.HXSdk;
import com.qq.e.o.ads.v2.ads.banner.BannerAD;
import com.qq.e.o.ads.v2.ads.banner.BannerADListener;
import com.qq.e.o.ads.v2.ads.floating.FloatingAD;
import com.qq.e.o.ads.v2.ads.floating.FloatingADListener;
import com.qq.e.o.ads.v2.ads.interstitial.InterstitialAD;
import com.qq.e.o.ads.v2.ads.interstitial.InterstitialADListener;
import com.qq.e.o.ads.v2.ads.nativ.NativeAD;
import com.qq.e.o.ads.v2.ads.nativ.NativeADListener;
import com.qq.e.o.ads.v2.ads.splash.SplashAD;
import com.qq.e.o.ads.v2.ads.splash.SplashADListener;
import com.qq.e.o.ads.v2.ads.video.FullscreenVideoAD;
import com.qq.e.o.ads.v2.ads.video.FullscreenVideoADListener;
import com.qq.e.o.ads.v2.ads.video.RewardVideoAD;
import com.qq.e.o.ads.v2.ads.video.RewardVideoADListener;
import com.qq.e.o.ads.v2.error.AdError;

public class AdManager {

    private static final String TAG = "AdManager";
    private static AdManager INSTANCE;

    private FloatingAD fad;
    private InterstitialAD iad;
    private FullscreenVideoAD ivAd;
    private RewardVideoAD rvAd;

    public static AdManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdManager();
        }
        return INSTANCE;
    }

    public void initEntry(Context context) {
        HXSdk.initEntry(context);
    }

    public void initSDK(Context context, String chId, String cpId, boolean isDebug, boolean isLog) {
        initSDK(context, chId, cpId, isDebug, isLog, true);
    }

    /**
     * 初始化广告SDK
     *
     * @param context context
     * @param chId    渠道号
     * @param cpId    CP号
     * @param isDebug 是否测试服广告 默认值false
     * @param isLog   是否打印log 建议BuildConfig.DEBUG
     * @param isCrash 是否拦截崩溃异常 默认值true
     */
    public void initSDK(Context context, String chId, String cpId, boolean isDebug, boolean isLog, boolean isCrash) {
        HXAdConfig.DEBUG = isDebug;
        HXAdConfig.IS_LOG = isLog;
        HXSdk.initSDK(context, chId, cpId, isCrash);
    }

    /**
     * 显示全屏视频广告
     */
    public void showFullscreenVideoAD() {
        if (FullscreenVideoAD.isPreloaded())
            ivAd.showAD();
    }

    /**
     * 显示激励视频广告
     */
    public void showRewardVideoAD() {
        if (RewardVideoAD.isPreloaded())
            rvAd.showAD();
    }

    /**
     * 加载开屏广告
     *
     * @param activity    activity
     * @param adContainer 容器
     * @param adListener  监听回调
     */
    public void showSplashAD(Activity activity, ViewGroup adContainer, SplashADListener adListener) {
        new SplashAD(activity, adContainer, adListener);
    }

    /**
     * 加载浮窗广告
     *
     * @param activity activity
     */
    public void showFloatingAD(Activity activity) {
        if (fad != null) {
            fad.destroy();
            fad = null;
        }
        fad = new FloatingAD(activity);
        fad.setADListener(new FloatingADListener() {
            @Override
            public void onNoAD(AdError adError) {
                Log.i(TAG, "FloatingAD onFailed, eCode=" + adError.getErrorCode() + " msg:" + adError.getErrorMsg());
            }

            @Override
            public void onADPresent() {
                Log.i(TAG, "FloatingAD onADPresent");
            }

            @Override
            public void onADClicked() {
                Log.i(TAG, "FloatingAD onADClicked");
            }

            @Override
            public void onADClosed() {
                Log.i(TAG, "FloatingAD onADClosed");
            }
        });
        fad.show();
    }

    public void closeFloatingAD() {
        if (fad != null) {
            fad.destroy();
            fad = null;
        }
    }

    /**
     * 加载插屏广告
     *
     * @param activity activity
     */
    public void showInterstitialAD(final Activity activity) {
        getInterstitialAD(activity, new InterstitialADListener() {
            @Override
            public void onADReceive() {
                Log.i(TAG, "InterstitialAD onADReceive");
            }

            @Override
            public void onADExposure() {
                Log.i(TAG, "InterstitialAD onADExposure");
            }

            @Override
            public void onADClicked() {
                Log.i(TAG, "InterstitialAD onADClicked");
            }

            @Override
            public void onADClosed() {
                Log.i(TAG, "InterstitialAD onADClosed");
            }

            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailed(int i, AdError adError) {
                Log.i(TAG, "InterstitialAD onFailed, eCode=" + adError.getErrorCode() + " msg:" + adError.getErrorMsg());
            }
        });
        iad.loadAD();
    }

    private void getInterstitialAD(Activity activity, InterstitialADListener adListener) {
        if (iad != null) {
            iad.closeAD();
            iad.destroy();
            iad = null;
        }
        iad = new InterstitialAD(activity, adListener);
    }

    /**
     * 加载原生信息流广告
     *
     * @param activity    activity
     * @param adContainer 容器
     * @param adWidth     广告宽度（dp）
     * @param adHeight    广告高度（dp）
     * @param adStyle     广告样式（参照HXInfoAdStyle类）
     */
    public void showNativeAD(Activity activity, ViewGroup adContainer, int adWidth, int adHeight, int adStyle) {
        NativeAD nad = new NativeAD(activity, adContainer, adWidth, adHeight, adStyle, new NativeADListener() {
            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailed(int i, AdError adError) {
                Log.i(TAG, "NativeAD onFailed, eCode=" + adError.getErrorCode() + " msg:" + adError.getErrorMsg());
            }

            @Override
            public void onADPresent() {
                Log.i(TAG, "NativeAD onADPresent");
            }

            @Override
            public void onADClicked() {
                Log.i(TAG, "NativeAD onADClicked");
            }

            @Override
            public void onADClosed() {
                Log.i(TAG, "NativeAD onADClosed");
            }

            @Override
            public void onPreload() {

            }
        });
        nad.loadAD();
    }

    /**
     * 加载横幅广告
     *
     * @param activity    activity
     * @param adContainer 容器
     * @param adHeight    广告高度（dp）
     */
    public void showBannerAD(Activity activity, ViewGroup adContainer, int adHeight) {
        BannerAD bad = new BannerAD(activity, adContainer, adHeight, new BannerADListener() {

            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailed(int i, AdError adError) {
                Log.i(TAG, "BannerAD onFailed, eCode=" + adError.getErrorCode() + " msg:" + adError.getErrorMsg());
            }

            @Override
            public void onADPresent() {
                Log.i(TAG, "BannerAD onADPresent");
            }

            @Override
            public void onADClicked() {
                Log.i(TAG, "BannerAD onADClicked");
            }

            @Override
            public void onADClosed() {
                Log.i(TAG, "BannerAD onADClosed");
            }
        });
        bad.loadAD();
    }

    /**
     * 预加载或加载全屏视频广告
     *
     * @param activity  activity
     * @param isPreload 是否预加载
     */
    public void showFullscreenVideoAD(final Activity activity, final boolean isPreload) {
        getFullscreenVideoAD(activity, new FullscreenVideoADListener() {

            @Override
            public void onADLoad() {
                Log.i(TAG, "FullscreenVideoAD onADLoad");
            }

            @Override
            public void onVideoCached() {
                Log.i(TAG, "FullscreenVideoAD onVideoCached");
            }

            @Override
            public void onADShow() {
                Log.i(TAG, "FullscreenVideoAD onADShow");
            }

            @Override
            public void onADExpose() {
                Log.i(TAG, "FullscreenVideoAD onADExpose");
            }

            @Override
            public void onADClicked() {
                Log.i(TAG, "FullscreenVideoAD onADClicked");
            }

            @Override
            public void onVideoComplete() {
                Log.i(TAG, "FullscreenVideoAD onVideoComplete");
            }

            @Override
            public void onADClosed() {
                Log.i(TAG, "FullscreenVideoAD onADClosed");
                if (isPreload) {
                    showFullscreenVideoAD(activity, true);
                }
            }

            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailed(int i, AdError adError) {
                Log.i(TAG, "FullscreenVideoAD onFailed, eCode=" + adError.getErrorCode() + " msg:" + adError.getErrorMsg());
            }

            @Override
            public void onSkippedVideo() {
                Log.i(TAG, "FullscreenVideoAD onSkippedVideo");
            }

            @Override
            public void onPreload() {
                Log.i(TAG, "FullscreenVideoAD onPreload");
            }
        });
        if (isPreload) {
            ivAd.preloadAD();
        } else {
            ivAd.loadAD();
        }
    }

    private void getFullscreenVideoAD(Activity activity, FullscreenVideoADListener adListener) {
        ivAd = new FullscreenVideoAD(activity, adListener);
    }

    /**
     * 预加载或加载激励视频广告
     *
     * @param activity  activity
     * @param isPreload 是否预加载
     */
    public void showRewardVideoAD(final Activity activity, final boolean isPreload) {
        showRewardVideoAD(activity,isPreload,null);
    }

    /**
     * 预加载或加载激励视频广告
     *
     * @param activity  activity
     * @param isPreload 是否预加载
     */
    public void showRewardVideoAD(final Activity activity, final boolean isPreload, final OnAdLister lister) {
        getRewardVideoAD(activity, new RewardVideoADListener() {

            @Override
            public void onADLoad() {
                Log.i(TAG, "RewardVideoAD onADLoad");
            }

            @Override
            public void onVideoCached() {
                Log.i(TAG, "RewardVideoAD onVideoCached");
            }

            @Override
            public void onADShow() {
                Log.i(TAG, "RewardVideoAD onADShow");
            }

            @Override
            public void onADExpose() {
                Log.i(TAG, "RewardVideoAD onADExpose");
            }

            @Override
            public void onReward() {
                Log.i(TAG, "RewardVideoAD onReward");
                if(lister != null){
                    lister.onReward();
                }
            }

            @Override
            public void onADClicked() {
                Log.i(TAG, "RewardVideoAD onADClicked");
            }

            @Override
            public void onVideoComplete() {
                Log.i(TAG, "RewardVideoAD onVideoComplete");
            }

            @Override
            public void onADClosed() {
                Log.i(TAG, "RewardVideoAD onADClosed");
                if (isPreload) {
                    showRewardVideoAD(activity, true);
                }
            }

            @Override
            public void onSuccess(int i) {

            }

            @Override
            public void onFailed(int i, AdError adError) {
                Log.i(TAG, "RewardVideoAD onFailed, eCode=" + adError.getErrorCode() + " msg:" + adError.getErrorMsg());
            }

            @Override
            public void onSkippedVideo() {
                Log.i(TAG, "RewardVideoAD onSkippedVideo");
            }

            @Override
            public void onPreload() {
                Log.i(TAG, "RewardVideoAD onPreload");
            }
        });
        if (isPreload) {
            rvAd.preloadAD();
        } else {
            rvAd.loadAD();
        }
    }

    public interface OnAdLister{
        void onReward();

        void onClosed();
    }

    private void getRewardVideoAD(Activity activity, RewardVideoADListener adListener) {
        rvAd = new RewardVideoAD(activity, adListener);
    }
}