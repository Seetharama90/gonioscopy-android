package com.android.example.gonioscopy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t= new Thread()
        {
            public void run()
            {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent Myintent = new Intent(MainActivity.this, technicianLogin.class);
                    Myintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Myintent);
                    finish();
                }
            }
        };
        t.start();
    }
}
