package wburles.uk.threads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class MainActivity extends AppCompatActivity {

    private static String TAG="KITTEN";
    private Button picButton;
    private Bitmap bitmap;
    private ImageView picView;
    private TextView messageTxt;
    private String[] urls={"http://ow.ly/LOklW","http://ow.ly/LOkzH","http://ow.ly/LOkFc","http://ow.ly/LOl54"};
    private int picldx=0;

    private class Downloader extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... urlStr) {
            downloadImage(urlStr[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            picView.setImageBitmap(bitmap);
            picButton.setEnabled(true);
            messageTxt.setText(R.string.ready_str);
            picldx = (picldx+1) % urls.length;
        }

        @Override
        protected void onPreExecute(){
            picButton.setEnabled(false);
            messageTxt.setText("Loading...");
        }
    }

    //private final Handler handler = new Handler();
    private final Runnable updateUI = new Runnable() {
        @Override
        public void run() {
            picView.setImageBitmap(bitmap);
            picButton.setEnabled(true);
            messageTxt.setText(R.string.ready_str);
            picldx = (picldx+1) % urls.length;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picButton = (Button) findViewById(R.id.new_pic_btn);
        picView = (ImageView) findViewById(R.id.image_view);
        messageTxt = (TextView) findViewById(R.id.message_text);

        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Downloader().execute(urls[picldx]);
            }
        });
    }

    private void downloadImage(String urlStr){
        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);

        } catch (IOException e){
            Log.d(TAG, e.getMessage());
        }
    }

    //private void updateImageInBackground(final String urlStr){
    //    new Thread(new Runnable() {
    //        @Override
    //        public void run() {
    //            downloadImage(urlStr);
    //            handler.post(updateUI);
    //        }
    //    }).start();
    //}
}
