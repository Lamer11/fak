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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    ArrayList<String> listDrugs = new ArrayList<>();
    RecyclerView mMessageRecycler;

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
        mMessageRecycler = (RecyclerView) findViewById(R.id.recycle); // finding all parametres





    }

    public void updateData(View view){

        /*
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


         */


        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));

        final DataAdapter dataAdapter = new DataAdapter(this, listDrugs);

        mMessageRecycler.setAdapter(dataAdapter);



    }


    public void testOnClick(View view){
        ViewHolder vv = new ViewHolder(view);

        selection.setText(vv.message.getText());
    }

}
