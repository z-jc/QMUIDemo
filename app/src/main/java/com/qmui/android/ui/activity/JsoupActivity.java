package com.qmui.android.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.qmui.android.R;
import com.qmui.android.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qq.e.o.utils.ILog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Z-JC
 * Date : 2020/4/29
 * Description :
 */
public class JsoupActivity extends BaseActivity {

    @BindView(R.id.topbar)
    public QMUITopBar topbar;
    @BindView(R.id.webView1)
    WebView webView1;
    @BindView(R.id.webView2)
    WebView webView2;

    @Override
    public void initView() {
        super.initView();
        topbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        topbar.setTitle("jsoup内容解析");
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);//退出动画
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                initDaAn("https://se.afanti100.com/share_html/mt8LUweuBsRoP1A%3D/");
            }
        }).start();
    }

    private void initDaAn(String url) {
        try {
            ILog.e("原始答案地址:" + url);
            //加载原始答案地址
            Document doc1 = Jsoup.connect(url).get();
            //解析题目截图
            Elements elementsIframe = doc1.select("iframe#sr_iframe");//解析题目答案地址
            Elements elementsImg = doc1.select("img.question_image");//解析题目截图地址

            if (elementsIframe.size() < 1) {
                ILog.e("暂无相关题目");
                ILog.e("抱歉，没有获得您想要的答案，可能是您拍的照片无法正确识别，请重新拍照上传");
            }

            Log.e("题目答案地址:", elementsIframe.attr("src"));
            Log.e("题目截图地址:", elementsImg.attr("src"));
            String cartImgUrl = elementsImg.attr("src");

            Document doc = Jsoup.connect("https://se.afanti100.com" + elementsIframe.attr("src")).get();
            Elements elements = doc.select("div.content");

            final StringBuilder builder = new StringBuilder();
            builder.append(Html.fromHtml(elements.get(0).toString()).toString() + "\n");
            builder.append(Html.fromHtml(elements.get(1).toString()).toString() + "\n");
            builder.append(Html.fromHtml(elements.get(2).toString()).toString() + "\n");

            Elements daan = doc.select("div.Afanti_Math");
            String strDa = "";
            for (int index = 0; index < daan.size(); index++) {
                strDa = daan.get(index).html();
                Log.e("题目", strDa);
                break;
            }

            ILog.e("题目:" + strDa);
            ILog.e("解析" + elements.get(1).html());
            ILog.e("答案" + elements.get(2).html());

            String finalStrDa = strDa;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView1.loadData("暂无答案","text/html", "utf-8");
                    webView2.loadData(elements.get(2).html(),"text/html", "utf-8");
                }
            });

        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_jsoup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}