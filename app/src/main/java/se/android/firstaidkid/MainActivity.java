package se.android.firstaidkid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    }

    //run add listPrepare
    public void listPrepar(View view) {
        Intent intent = new Intent(this, ListPrepar.class);
        startActivity(intent);
    }
}