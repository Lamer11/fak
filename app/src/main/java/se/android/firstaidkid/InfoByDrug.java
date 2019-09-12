package se.android.firstaidkid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoByDrug extends AppCompatActivity {

    TextView preparat;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_by_drug);


        Bundle arguments = getIntent().getExtras();
        String name = arguments.getString("key");

        preparat = (TextView) findViewById(R.id.textView13);
        preparat.setText(name);

    }


    public void editDrug(View view){

        // the logic for editing drugs

    }




}
