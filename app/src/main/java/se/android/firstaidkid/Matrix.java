package se.android.firstaidkid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Matrix extends AppCompatActivity {

    TextView tv1;
    Handler handler=new Handler();
    int i = 0;

    ArrayList<String> arrStr;
    StringBuilder strBuild;
    ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        tv1 = (TextView) findViewById(R.id.textView8);
        scrollView = (ScrollView) findViewById(R.id.scrollView2);

        strBuild = new StringBuilder();

        InputStream inputStream = getResources().openRawResource(R.raw.codik);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        arrStr = new ArrayList<>();
        String str;
        while(true){
            try {
                if (!((str = bufferedReader.readLine()) == null)) {

                    arrStr.add(str);

                }else {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void onClick(View view) throws IOException {


        handler.post(new Runnable(){
            @Override
            public void run() {
                boolean bol = true;
                // upadte textView here
                if(i==arrStr.size()){ // if array of strings ended
                    i = 0; // then i nullify
                    strBuild.delete(0, strBuild.length()); // and in strBuild we delete all characters
                }
                strBuild.append(arrStr.get(i)).append("\n");
                tv1.setText(strBuild);
                System.out.println(strBuild);
                i++;
                scrollView.scrollTo(0, +300);
                scrollView.scrollTo(0, +100);
                scrollView.scrollTo(0, +100);
                handler.postDelayed(this,200); // set time here to refresh textView

            }
        });


    }


}
