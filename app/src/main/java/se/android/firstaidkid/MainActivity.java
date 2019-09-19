package se.android.firstaidkid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    //run add medicine
    public void addDrug(View view) {
        Intent intent = new Intent(this, addDrugActivity.class);
        startActivity(intent);
        //добавление вибрации и (по идее) стандартного звука
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }

    //run add listPrepare
    public void listPrepar(View view) {
        Intent intent = new Intent(this, ListPrepar.class);
        startActivity(intent);
        //добавление вибрации и (по идее) стандартного звука
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }


    //soundOn
    public void matrix(View view) {
        Intent intent = new Intent(this, Matrix.class);
        startActivity(intent);

        //добавление вибрации и (по идее) стандартного звука
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    }
}