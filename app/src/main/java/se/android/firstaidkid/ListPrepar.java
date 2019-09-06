package se.android.firstaidkid;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListPrepar extends AppCompatActivity {

    FirebaseFirestore firebase = FirebaseFirestore.getInstance();
    private TextView selection;
    ListView countriesList;
    String[] countries = { "Бразилия", "Аргентина"};
    List<String> listDrugs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_prepar);


        firebase.collection("apteka").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //получаем карту с документом
                        Map<String, Object> docum = document.getData();
                        //вытаскиеваем из карты значение по ключу drug и запихиваем в массив list
                        listDrugs.add((String) docum.get("drug"));
                    } System.out.println(listDrugs);
                } else {
                    //gggggggg
                }
            }
        });



        // получаем элемент TextView
        selection = (TextView) findViewById(R.id.selection);
        // получаем элемент ListView
        countriesList = (ListView) findViewById(R.id.countriesList);



    }

    public void updateData(View view){

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listDrugs);
        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);
        // добвляем для списка слушатель
        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = listDrugs.get(position);
                // установка текста элемента TextView
                selection.setText(selectedItem);
            }
        });

    }


}
