package com.qmui.android.bean;

/**
 * Created by zb on 2019/10/11.
 */
public class ProgressBuilder {

    /**
     * 标题名
     */
    public String title;

    /**
     * 提示
     */
    public String titleMessage;

    /**
     * 弹出样式
     */
    public int style;

    /**
     * 点击背景是否可关闭
     */
    public boolean cancelable;
    public boolean canceledOnTouchOutside;

    /**
     * 最大值
     */
    public int max;

    /**
     * 显示当前的值
     */
    public int progress;

    public ProgressBuilder(Builder builder) {
        this.max = builder.max;
        this.title = builder.title;
        this.style = builder.style;
        this.progress = builder.progress;
        this.cancelable = builder.cancelable;
        this.titleMessage = builder.titleMessage;
        this.canceledOnTouchOutside = builder.canceledOnTouchOutside;
    }

    public static class Builder {

        /**
         * 标题名
         */
        public String title;

        /**
         * 提示
         */
        public String titleMessage;

        /**
         * 弹出样式
         */
        public int style;

        /**
         * 点击返回按钮是否可退出
         */
        public boolean cancelable;

        /**
         * 点击背景是否可关闭
         */
        public boolean canceledOnTouchOutside;

        /**
         * 最大值
         */
        public int max;

        /**
         * 显示当前的值
         */
        public int progress;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitleMessage(String titleMessage) {
            this.titleMessage = titleMessage;
            return this;
        }

        public Builder setStyle(int style) {
            this.style = style;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder setMax(int max) {
            this.max = max;
            return this;
        }

        public Builder setProgress(int progress) {
            this.progress = progress;
            return this;
        }

        public ProgressBuilder build() {
            return new ProgressBuilder(this);
        }
    }
}