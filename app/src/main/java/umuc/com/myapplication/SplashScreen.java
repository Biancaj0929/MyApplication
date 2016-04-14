package umuc.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/**
 * Created by Steve on 4/14/2016.
 * Creates thread and puts thread to
 *  sleep (pauses thread) for n milliseconds
 *
 */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,Goals.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}


