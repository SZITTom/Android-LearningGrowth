package com.yun.android_learninggrowth.view.activity.asynctast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.yun.android_learninggrowth.R;
import com.yun.android_learninggrowth.base.BaseAppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;

/**
 * Android AsyncTask两种线程池分析和总结
 */
public class AsyncTastActivity extends BaseAppCompatActivity {

    String url = "http://e.hiphotos.baidu.com/image/w%3D2048/sign=61711bd121a446237ecaa262ac1a730e/e850352ac65c10385f10af69b3119313b07e892a.jpg";
    String urls = "http://img2.3lian.com/2014/f4/193/d/68.jpg";
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;
    @BindView(R.id.iv_thread_pool)
    ImageView ivThreadPool;
    @BindView(R.id.iv_serial)
    ImageView ivSerial;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_async_tast;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private static int productTaskMaxNumber = 10;

    //开启异步线程池
    public void onStartAsyncTast(View view) {
        for (int i = 0; i < productTaskMaxNumber; i++) {
        }
        // 产生一个任务，并将其加入到线程池
        String task = "task@ " + 0;
        MyAsyncTask asynct = new MyAsyncTask(task);
        asynct.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);

    }

    /**
     * 三个泛型类params,progress和result
     * params表示通过execute（）方法传进来的参数的类型。
     * progress表示progress的单位的类型。
     * result表示在计算完成之后返回值的类型。
     */
    class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        public MyAsyncTask(String s) {

        }

        //开始运行线程（在UI线程）
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //更新Progress
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        //运行在子线程中
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            String url = params[0];
            HttpClient client = new DefaultHttpClient();//客服端请求
            HttpGet getMethod = new HttpGet(url);//请求类型
            try {
                HttpResponse response = client.execute(getMethod);//http响应
                if (response.getStatusLine().getStatusCode() == 200) {//=200成功
                    HttpEntity entity = response.getEntity();
                    byte[] data = EntityUtils.toByteArray(entity);
                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        //运行结束（在UI线程）
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                ivThreadPool.setImageBitmap(bitmap);
            } else {
                ivThreadPool.setImageResource(R.mipmap.ic_launcher);
            }
        }
    }


    //开启同步线程池
    public void onStartSerial(View view) {
        // 产生一个任务，并将其加入到线程池
        String task = "task@ " + 0;
        UrlHttpConnection asynctask = new UrlHttpConnection();
        asynctask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, urls);
//        asynctask.execute(url);//同步线程池,默认写法
    }

    class UrlHttpConnection extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            HttpURLConnection conn = null;
            try {
                URL url = new URL(params[0]);//创建URL对象
                //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000); //设置连接超时为5秒
                conn.setRequestMethod("GET"); //设定请求方式
                conn.connect(); //建立到远程对象的实际连接
                //返回打开连接读取的输入流
                //DataInputStream dis = new DataInputStream(conn.getInputStream());
                //判断是否正常响应数据
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }

            return bitmap;
        }

        //运行结束（在UI线程）
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                ivSerial.setImageBitmap(bitmap);
            } else {
                ivSerial.setImageResource(R.mipmap.ic_launcher);
            }
        }
    }
}
