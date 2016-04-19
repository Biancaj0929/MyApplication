package umuc.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**File: SplashScreen.java
* Author: Team Bucket List
* Date: 15 April 2016
* Purpose:  Displays splash screen for three seconds when app is initiated. 
*           Shows title and contributors with image of bucket in background. 
**/

public class SplashScreen extends Goals {
    
    //Creates thread and puts thread to sleep (pauses thread) for n milliseconds to display contents of splash screen
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
                    Intent intent = new Intent(SplashScreen.this,Personal.class);
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


