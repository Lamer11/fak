package se.android.firstaidkid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

public class Matrix extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    int count = 0;
    boolean bol = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        tv1 = (TextView) findViewById(R.id.textView5);
        tv2 = (TextView) findViewById(R.id.textView6);
        tv3 = (TextView) findViewById(R.id.textView7);


    }

    public void onClick(View view){
        Texter t = new Texter();
        t.execute();
    }

    class Texter extends  AsyncTask<Void, Integer, Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv1.setText("Кот полез на крышу столько раз:");
            bol = false;
            System.out.println("bol в ПРЕДпоследней :" + bol);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            System.out.println("начало тяжко работы:" + bol);
            SystemClock.sleep(200);
            System.out.println("конец тяжко работы:" + bol);
            ddd();
            return null;
        }

        public void ddd(){
            tv2.setText("Кот залез на крышу = " + count);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tv2.setText("Кот залез на крышу = " + count);
            count++;
            bol = true;
            System.out.println("bol в последней :" + bol);
        }
    }
}
