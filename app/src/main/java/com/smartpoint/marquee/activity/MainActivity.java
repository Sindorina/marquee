package com.smartpoint.marquee.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.smartpoint.adapter.MyAdapter;
import com.smartpoint.marquee.AEStool;
import com.smartpoint.marquee.MD5Util;
import com.smartpoint.marquee.R;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    public static final String METHOD_GET = "GET";
    public String BaseUri = "https://apiequipment.signp.cn";
    public String key = "56a8d122ec0d330d6d9f541b459e43e1";
    private TextView test;
    private StringBuilder stringBuilder1 = new StringBuilder();
    View view;//底部弹出的dialog的显示View
    private boolean lightSwitch = false;
    private ListView listView;
    private MyAdapter adapter;
    private DrawerLayout drawerLayout;
    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCpuInfo();
        test = findViewById(R.id.test);
        listView = findViewById(R.id.listView);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        adapter.addData(getListData());
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://surfaceView
                        SurfaceViewActivity.start(MainActivity.this);
                        break;
                    case 1://字母快速指引
                        ContactActivity.start(MainActivity.this);
                        break;
                    case 2://折现图
                        LineChartsActivity.start(MainActivity.this);
                        break;
                    case 3://柱状图
                        ColumnChartActivity.start(MainActivity.this);
                        break;
                    case 4://webApp
                        WebAppActivity.start(MainActivity.this);
                        break;
                    case 5://字体
                        FontActivity.start(MainActivity.this);
                        break;
                    case 6://ping
                        PingTestActivity.start(MainActivity.this);
                    case 7://DECODE
                        Decode2Activity.start(MainActivity.this);
                        break;
                    case 8://webSocket
                        WebSocketActivity.start(MainActivity.this);
                        break;
                    case 9://waterWave
                        WaterWaveActivity.start(MainActivity.this);
                        break;
                    case 10://百度语音合成
                        BaiDuVoiceMadeActivity.start(MainActivity.this);
                        break;
                }
            }
        });
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        test.setText(getInfo());
        Log.e("ost", "加密数据-->" + MD5Util.MD5("P1QRMJTWM8"));
        AEStool aeStool = new AEStool("RzyoJXeCuyBkVMRX");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //httpsTest("/panel");
            }
        }).start();
        findViewById(R.id.btn_rxjava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxJavaLearnActivity.start(MainActivity.this);
            }
        });
    }

    private String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        String news1 = "春江潮水连海平，海上明月共潮生。 ";
        String news2 = "滟滟随波千万里，何处春江无月明！ ";
        String news3 = "江流宛转绕芳甸，月照花林皆似霰； ";
        String news4 = "空里流霜不觉飞，汀上白沙看不见。";
        String news5 = "江天一色无纤尘，皎皎空中孤月轮。" +
                "江畔何人初见月？江月何年初照人？" +
                "人生代代无穷已，江月年年只相似。" +
                "不知江月待何人，但见长江送流水。" +
                "白云一片去悠悠，青枫浦上不胜愁。" +
                "谁家今夜扁舟子？何处相思明月楼？" +
                "可怜楼上月徘徊，应照离人妆镜台。" +
                "玉户帘中卷不去，捣衣砧上拂还来。" +
                "此时相望不相闻，愿逐月华流照君。" +
                "鸿雁长飞光不度，鱼龙潜跃水成文。" +
                "昨夜闲潭梦落花，可怜春半不还家。" +
                "江水流春去欲尽，江潭落月复西斜。" +
                "斜月沉沉藏海雾，碣石潇湘无限路。" +
                "不知乘月几人归，落月摇情满江树。";
        stringBuilder.append(news1).append("        ")
                .append(news2).append("        ").append(news3).append("        ").append(news4).append("        ").append(news5);
        return stringBuilder.toString();
    }

    private void httpsTest(String functionName) {
        allowAllSSL();
        try {
            URL url = new URL(BaseUri + functionName);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(METHOD_GET);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            int code = conn.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String res = null;
                while ((res = reader.readLine()) != null) {
                    stringBuilder1.append(res);
                }
                reader.close();
                Message message = handler.obtainMessage();
                message.obj = stringBuilder1.toString();
                handler.sendMessage(message);
            } else {
                Log.e("MainActivity", "error");
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //信任所有证书
    private void allowAllSSL() {
        TrustManager[] trustManagers = new TrustManager[]{new HttpsTrustManager()};
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }


    public class HttpsTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String result = (String) msg.obj;
            Log.e("MainActivity", "测试数据-->" + result);
            return false;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.web:
                WebViewActivity.start(this);
                return true;
            case R.id.vr:
                VRActivity.start(this);
                return true;
            case R.id.pickerView:
                PickerViewActivity.start(this);
                return true;
            case R.id.tencentWebView:
                TencentWebViewActivity.start(this);
                return true;
            case R.id.media:
                MediaPlayActivity.start(this);
                return true;
            case R.id.zxin:
                ZxinActivity.start(this);
                return true;
            case R.id.install:
                checkPermission();
                return true;
            case R.id.sqlite:
                SQLiteActivity.start(this);
                return true;
            case R.id.litePal:
                LitePalActivity.start(this);
                return true;
            case R.id.vbar:
                DecodeActivity.start(this);
                return true;
            case R.id.jni:
                JniActivity.start(this);
                return true;
            case R.id.reboot:
                try {
                    Log.v("ost", "root Runtime->reboot");
                    Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot "});
                    proc.waitFor();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return true;
            case R.id.show:
                if (lightSwitch) {
                    lightSwitch = false;
                    lightTest(-1.0f);
                } else {
                    lightSwitch = true;
                    lightTest(0.00000000000000000000000000001f);
                }
                return true;
            case R.id.screenLock:
                ScreenLockActivity.start(MainActivity.this);
                return true;
            case R.id.timeCount:
                CountDownTimerActivity.start(MainActivity.this);
                return true;
            case R.id.chrome:
                CountDownTimerActivity.start(MainActivity.this);
                return true;
            case R.id.distinct:
                DistinctActivity.start(MainActivity.this);
                return true;
            case R.id.svg:
                SvgActivity.start(MainActivity.this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * 安装
     *
     * @param context   接收外部传进来的context
     * @param mFilePath apk保存路径
     */
    public void install(Context context, String mFilePath) {
        // 核心是下面几句代码
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(mFilePath)),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    // TODO: 2018/5/3 权限检测 
    private void checkPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_SETTINGS)
                .subscribe(new Observer<Permission>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Permission permission) {
                        if (permission.granted) {
                            install(MainActivity.this, Environment.getExternalStorageDirectory().getAbsolutePath() + "/123.apk");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            return;
                        } else {
                            return;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 屏幕亮度测试
     */
    private void lightTest(float brightness) {
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness == -1) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        } else {
            lp.screenBrightness = (brightness <= 0 ? 1 : brightness);
        }
        window.setAttributes(lp);
    }

    /**
     * 获取侧边栏数据
     *
     * @return 侧边栏返回数据
     */
    private List<String> getListData() {
        List<String> list = new ArrayList<>();
        list.add("SurfaceView");
        list.add("字母快速指引");
        list.add("折现图");
        list.add("柱状图");
        list.add("JQuery");
        list.add("字体");
        list.add("PING");
        list.add("DECODE2");
        list.add("webSocket");
        list.add("waterWave");
        list.add("百度语音合成");
        return list;
    }

    private void addDrawerListenner() {
        drawerbar = new ActionBarDrawerToggle(this, drawerLayout, R.mipmap.ic_launcher, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerbar);
    }
    //获取Android手机cpu信息
    private String getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        Log.e("OST", "cpu型号-->" + cpuInfo[0]);
        Log.e("OST", "cpu频率-->" + cpuInfo[1]);
        return cpuInfo[0];
    }
}
