package com.example.will.audiovideo;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    private final String TAG = "U08971_Audio";

    private Button playBtn, pauseBtn, restartBtn, stopBtn;
    private EditText audioSourceEdit;
    private Spinner sources;
    private MediaPlayer player;
    private int currentPosition = 0;

    private final double NOTE_DURATION = 0.2;
    private final int SAMPLE_RATE = 8000;
    private final double SAMPLE_SIZE = NOTE_DURATION*SAMPLE_RATE;
    private final byte[] audioBuffer = new byte[2*SAMPLE_RATE];
    private Handler handler = new Handler();

    private Button aBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playBtn = (Button) findViewById(R.id.button_play);
        pauseBtn = (Button) findViewById(R.id.button_pause);
        restartBtn = (Button) findViewById(R.id.button_restart);
        stopBtn = (Button) findViewById(R.id.button_stop);
        aBtn = (Button) findViewById(R.id.button_a);
        audioSourceEdit = (EditText) findViewById(R.id.audio_source_edit);
        sources = (Spinner) findViewById(R.id.source_spinner);
        ArrayAdapter<CharSequence> sourceAdapter = ArrayAdapter.createFromResource(this,R.array.audio_sources_array,
                android.R.layout.simple_spinner_item);
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sources.setAdapter(sourceAdapter);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sources.getSelectedItemPosition() == 2){
                    playLocalAudio();
                } else { playAudio(); }
            }
        });
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
            }
        });
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartAudio();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });
        aBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 400; i < 650; i=i+10){
                    playNote(i);
                }

            }
        });
    }

    private void fillBuffer(double freq){
        for(int i = 0; i < audioBuffer.length / 2; i++){
            double val = Math.sin(2 * Math.PI * i / (SAMPLE_RATE / freq));
            short normVal = (short) ((val*32767));
            audioBuffer[2*i] = (byte)(normVal & 0x00ff);
            audioBuffer[2*i+1] = (byte)((normVal & 0xff00) >>> 8);
        }
    }

    private void playBuffer(){
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, SAMPLE_RATE, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,audioBuffer.length, AudioTrack.MODE_STATIC);
        audioTrack.write(audioBuffer,0,audioBuffer.length);
        audioTrack.play();
    }

    protected void playNote(double freq){
        final double finFreq = freq;
        final Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                fillBuffer(finFreq);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        playBuffer();
                    }
                });
            }
        });
        thread.start();
    }

    private void playLocalAudio() {
        releaseMediaPlayer();
        player = MediaPlayer.create(this, R.raw.fun);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
    }

    private void pauseAudio() {
        if(player != null && player.isPlaying()){
            currentPosition = player.getCurrentPosition();
            player.pause();
        }
    }

    private void restartAudio() {
        if(player != null && !player.isPlaying()){
            player.seekTo(currentPosition);
            player.start();
        }
    }

    private void stopAudio() {
        if(player != null){
            player.stop();
            currentPosition = 0;
        }
    }

    private void playAudio(){
        releaseMediaPlayer();
        player = new MediaPlayer();

        String sourceStr = audioSourceEdit.getEditableText().toString();
        String urlStr = audioSourceEdit.getEditableText().toString();

        if(sources.getSelectedItemPosition() == 0){
            urlStr = sourceStr;
        } else if(sources.getSelectedItemPosition() == 1){
            urlStr = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MUSIC) + "/" + sourceStr;
        } else {

        }

        //player = MediaPlayer.create(this, Uri.parse(urlStr));
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource(urlStr);
            player.setOnPreparedListener(this);
            player.prepareAsync();
            player.start();
        } catch (IllegalArgumentException e) {
            Log.e("myerror","IllegalArgumentException in playAudio(): " + e.getMessage());
        } catch (IOException e) {
            Log.e("myerror", "IOExpcetion in playAudio(): " + e.getMessage());
        } catch (SecurityException e) {
            Log.e("myerror", "SecurityException in playAudio(): " + e.getMessage());
        } catch (IllegalStateException e) {
            Log.e("myerror", "IllegalState in playAudio(): " + e.getMessage());
        }
    }

    private void releaseMediaPlayer(){
        if(player != null){
            try {
                player.release();
            } catch (Exception ex){
                Log.d("myerror","Exception releasing media player: "+ex.getMessage());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }
}
