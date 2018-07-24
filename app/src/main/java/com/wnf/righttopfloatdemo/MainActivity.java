package com.wnf.righttopfloatdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private TextView textView1;
    private TextView textView2;
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonPanel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });

        textView1=(TextView) findViewById(R.id.TextView1_seekBar);
        textView2=(TextView) findViewById(R.id.TextView2_seekBar);
        seekBar1=(SeekBar) findViewById(R.id.SeekBar1);
        seekBar2=(SeekBar) findViewById(R.id.SeekBar2);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);

        final ProgressBar bar = (ProgressBar) findViewById(R.id.pb_one);
        final TextView textView= (TextView) findViewById(R.id.tvProgress);
        new Thread(){
            @Override
            public void run() {
                int i=0;
                while(i<100){
                    i++;
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final int j=i;
                    bar.setProgress(i);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(j+"%");
                        }
                    });
                }
            }
        }.start();
    }
    /*
   * onProgressChanged：进度条改变事件
   * onStartTrackingTouch：进度条开始拖动事件
   * onStopTrackingTouch：进度条停止拖动事件
   * (non-Javadoc)
   * @see android.widget.SeekBar.OnSeekBarChangeListener#onProgressChanged(android.widget.SeekBar, int, boolean)
   */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        // TODO Auto-generated method stub
        if(seekBar1.getId()==R.id.SeekBar1){
            //Toast.makeText(this, "1111111", Toast.LENGTH_SHORT).show();
            textView1.setText("seekBar1当前位置："+progress);
        }else{
            textView2.setText("seekBar2当前位置："+progress);
        }
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
        if(seekBar1.getId()==R.id.SeekBar1){
            textView1.setText("seekBar1开始拖动");
        }else{
            textView2.setText("seekBar2开始拖动：");
        }
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
        if(seekBar1.getId()==R.id.SeekBar1){
            textView1.setText("seekBar1停止拖动");
        }else{
            textView2.setText("seekBar2停止拖动：");
        }
    }
}
