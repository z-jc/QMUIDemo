package com.qmui.android.mvc.entity;

import java.util.Arrays;
import java.util.List;

/**
 * 封装天气信息
 */
public class WeatherInfo {

    /**
     * data : {"yesterday":{"date":"28日星期一","high":"高温 16℃","fx":"西风","low":"低温 1℃","fl":"<![CDATA[5-6级]]>","type":"雷阵雨"},"city":"沈阳","forecast":[{"date":"29日星期二","high":"高温 11℃","fengli":"<![CDATA[3-4级]]>","low":"低温 2℃","fengxiang":"西风","type":"晴"},{"date":"30日星期三","high":"高温 19℃","fengli":"<![CDATA[4-5级]]>","low":"低温 5℃","fengxiang":"西南风","type":"晴"},{"date":"31日星期四","high":"高温 16℃","fengli":"<![CDATA[3-4级]]>","low":"低温 -1℃","fengxiang":"北风","type":"晴"},{"date":"1日星期五","high":"高温 14℃","fengli":"<![CDATA[<3级]]>","low":"低温 0℃","fengxiang":"北风","type":"晴"},{"date":"2日星期六","high":"高温 12℃","fengli":"<![CDATA[3-4级]]>","low":"低温 -3℃","fengxiang":"北风","type":"晴"}],"ganmao":"将有一次强降温过程，天气寒冷，极易发生感冒，请特别注意增加衣服保暖防寒。","wendu":"8"}
     * status : 1000
     * desc : OK
     */

    private DataBean data;
    private int status;
    private String desc;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class DataBean {
        /**
         * yesterday : {"date":"28日星期一","high":"高温 16℃","fx":"西风","low":"低温 1℃","fl":"<![CDATA[5-6级]]>","type":"雷阵雨"}
         * city : 沈阳
         * forecast : [{"date":"29日星期二","high":"高温 11℃","fengli":"<![CDATA[3-4级]]>","low":"低温 2℃","fengxiang":"西风","type":"晴"},{"date":"30日星期三","high":"高温 19℃","fengli":"<![CDATA[4-5级]]>","low":"低温 5℃","fengxiang":"西南风","type":"晴"},{"date":"31日星期四","high":"高温 16℃","fengli":"<![CDATA[3-4级]]>","low":"低温 -1℃","fengxiang":"北风","type":"晴"},{"date":"1日星期五","high":"高温 14℃","fengli":"<![CDATA[<3级]]>","low":"低温 0℃","fengxiang":"北风","type":"晴"},{"date":"2日星期六","high":"高温 12℃","fengli":"<![CDATA[3-4级]]>","low":"低温 -3℃","fengxiang":"北风","type":"晴"}]
         * ganmao : 将有一次强降温过程，天气寒冷，极易发生感冒，请特别注意增加衣服保暖防寒。
         * wendu : 8
         */

        private YesterdayBean yesterday;
        private String city;
        private String ganmao;
        private String wendu;
        private List<ForecastBean> forecast;

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 28日星期一
             * high : 高温 16℃
             * fx : 西风
             * low : 低温 1℃
             * fl : <![CDATA[5-6级]]>
             * type : 雷阵雨
             */

            private String date;
            private String high;
            private String fx;
            private String low;
            private String fl;
            private String type;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            @Override
            public String toString() {
                return "YesterdayBean{" +
                        "date='" + date + '\'' +
                        ", high='" + high + '\'' +
                        ", fx='" + fx + '\'' +
                        ", low='" + low + '\'' +
                        ", fl='" + fl + '\'' +
                        ", type='" + type + '\'' +
                        '}' + "\n\n";
            }
        }

        public static class ForecastBean {
            /**
             * date : 29日星期二
             * high : 高温 11℃
             * fengli : <![CDATA[3-4级]]>
             * low : 低温 2℃
             * fengxiang : 西风
             * type : 晴
             */

            private String date;
            private String high;
            private String fengli;
            private String low;
            private String fengxiang;
            private String type;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            @Override
            public String toString() {
                return "ForecastBean{" +
                        "date='" + date + '\'' +
                        ", high='" + high + '\'' +
                        ", fengli='" + fengli + '\'' +
                        ", low='" + low + '\'' +
                        ", fengxiang='" + fengxiang + '\'' +
                        ", type='" + type + '\'' +
                        '}' + "\n\n";
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "yesterday=" + yesterday +
                    ", city='" + city + '\'' +
                    ", ganmao='" + ganmao + '\'' +
                    ", wendu='" + wendu + '\'' +
                    ", \n\nforecast=" + Arrays.toString(forecast.toArray()) +
                    '}' + "\n\n";
        }
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "\n\ndata=" + data.toString() +
                "status=" + status +
                "\n\n, desc='" + desc + '\'' +
                '}';
    }
}