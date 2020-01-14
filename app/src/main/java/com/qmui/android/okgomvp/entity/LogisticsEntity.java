package com.qmui.android.okgomvp.entity;

import java.util.List;

/**
 * Author : Z-JC
 * Date : 2020/1/12
 * Description :
 */
public class LogisticsEntity extends BaseEntity{

    /**
     * resultcode : 200
     * reason : 查询物流信息成功
     * result : {"company":"EMS","com":"ems","no":"1186465887499","status":"1","status_detail":"PENDING","list":[{"datetime":"2016-06-15 21:44:04","remark":"离开郴州市 发往长沙市【郴州市】","zone":""}]}
     * error_code : 0
     */

    private String resultcode;      //请求状态码
    private String reason;          //查询物流信息成功
    private ResultBean result;      //物流数据
    private int error_code;         //错误码，0表示查询正常，其他表示查询不到物流信息或发生了其他错误

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {

        /**
         * company : EMS
         * com : ems
         * no : 1186465887499
         * status : 1
         * status_detail : PENDING
         * list : [{"datetime":"2016-06-15 21:44:04","remark":"离开郴州市 发往长沙市【郴州市】","zone":""}]
         */

        private String company;     //快递公司名字
        private String com;         //快递字母缩写
        private String no;          //快递单号
        private String status;      //物流信息状态
        private String status_detail;
        /* 详细的状态信息，可能为null，仅作参考。其中：
         PENDING 待查询
         NO_RECORD 无记录
         ERROR 查询异常
         IN_TRANSIT 运输中
         DELIVERING 派送中
         SIGNED 已签收
         REJECTED 拒签
         PROBLEM 疑难件
         INVALID 无效件
         TIMEOUT 超时件
         FAILED 派送失败
         SEND_BACK 退回
         TAKING 揽件 */
        private List<ListBean> list;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus_detail() {
            return status_detail;
        }

        public void setStatus_detail(String status_detail) {
            this.status_detail = status_detail;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * datetime : 2016-06-15 21:44:04
             * remark : 离开郴州市 发往长沙市【郴州市】
             * zone :
             */

            private String datetime;    //物流事件发生的时间
            private String remark;      //物流事件的描述
            private String zone;        /* 快件当时所在区域，由于快递公司升级，现大多数快递不提供此信息 */

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getZone() {
                return zone;
            }

            public void setZone(String zone) {
                this.zone = zone;
            }
        }
    }
}