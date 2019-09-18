package se.android.firstaidkid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class InfoByDrug extends AppCompatActivity {

    TextView preparat, desc, t1, t2, idDocID, dateCreated, dateUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_by_drug);

        //тут идет передача сериализованного обьекта коллекции мапы по ключу key
        Intent arguments = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>) arguments.getSerializableExtra("key");

        String nameDrug = hashMap.get("drug");
        String description = hashMap.get("description");
        String test1 = hashMap.get("test1");
        String test2 = hashMap.get("test2");
        String idDocDrug = hashMap.get("key");
        String date_cr = hashMap.get("data_created");
        String date_up = hashMap.get("data_updated");


        preparat = (TextView) findViewById(R.id.textView13);
        desc = (TextView) findViewById(R.id.textView17);
        t1 = (TextView) findViewById(R.id.textView19);
        t2 = (TextView) findViewById(R.id.textView21);
        idDocID  = (TextView) findViewById(R.id.textView23);
        dateCreated  = (TextView) findViewById(R.id.textView25);
        dateUpdate  = (TextView) findViewById(R.id.textView27);

        preparat.setText(nameDrug);
        desc.setText(description);
        t1.setText(test1);
        t2.setText(test2);
        idDocID.setText(idDocDrug);
        dateCreated.setText(date_cr);
        dateUpdate.setText(date_up);
    }


    public void editDrug(View view){

        // the logic for editing drugs

    }

}
