package se.android.firstaidkid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListPrepar extends AppCompatActivity {

    FirebaseFirestore firebase = FirebaseFirestore.getInstance();
    ListView countriesList;
    List<Map<String, Object>> listMapDrugs = new ArrayList<>(); //карта, в которой будет хранится вся аптека из коллекции
    List<String> arrayDrug = new ArrayList<>(); // создаем массив с названиями лекарст, для ArrayAdapter (для аргумента внутри, разбираться не охота)

    //делаем объект прогресс бара в виде круглешка, который будет отрабатывать пока не подгрузится инфа из БД
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_prepar);

        // получаем элемент ListView
        countriesList = (ListView) findViewById(R.id.countriesList);

        spinner = (ProgressBar)findViewById(R.id.progressBar1); // инициализируем наш прогрессбар
        spinner.setVisibility(View.VISIBLE); // делаем видимым

        // создаем и инициализируем базу данных Firebase внутри метода onCreate()
        firebase.collection("apteka").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //тут заполняем карту и лист

                        Map<String, Object> mapPlusIdOfDoc = document.getData(); // создаем карту для локального документа, чтобы потом еще запихнуть айди этого документа
                        mapPlusIdOfDoc.put("key", document.getId()); // создаем новую ячецку с Id документа

                        listMapDrugs.add(mapPlusIdOfDoc); // добавляем эту мапу в лист, который будет в ArrayAdapter<String> adapter
                        arrayDrug.add((String) document.getData().get("drug")); // тут пополняем просто список с названиями лекарств
                    }

                    spinner.setVisibility(View.GONE); // тут делаем прогрессбар невидимым, так как тут БД уже подгружайсон

                    // создаем адаптер (прям в этом во всем асинхронном. Асинхронное это все для получения данных из базы данных)
                    ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayDrug);
                    // устанавливаем для списка адаптер
                    countriesList.setAdapter(adapter);
                    // добавляем для списка слушатель
                    countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        //тык на каждый элемент
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
                        {
                            // по позиции получаем выбранный элемент
                            String selectedItem = (String) listMapDrugs.get(position).get("drug");
                            //создаем временный мап для передачи в интент
                            Map<String, Object> intentMapa = listMapDrugs.get(position);

                            // создание объекта Intent для запуска InfoByDrug
                            Intent intent = new Intent(getApplicationContext(), InfoByDrug.class);
                            // передача объекта с ключом "key" и вторым аргументом сеариализуем объект мапы
                            intent.putExtra("key", (Serializable) intentMapa);
                            //System.out.println("Интент = " + selectedItem);
                            startActivity(intent);

                        }
                    });


                } else {
                    //Если что не так, то показываем мессадж тостом
                    Toast t = Toast.makeText(getApplicationContext(), "Что-то посло не так", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER, 0,0);
                    t.show();
                }
            }
        });


    }

}
