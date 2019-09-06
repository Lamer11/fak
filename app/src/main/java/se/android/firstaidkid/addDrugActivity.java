package se.android.firstaidkid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import static android.widget.Toast.makeText;

public class addDrugActivity extends AppCompatActivity {

    EditText t1, t2, t3, t4;
    FirebaseFirestore firebase = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        t1 = (EditText) findViewById(R.id.edit_1);
        t2 = (EditText) findViewById(R.id.edit_2);
        t3 = (EditText) findViewById(R.id.editText);
        t4 = (EditText) findViewById(R.id.editText2);
    }

    //String word = String.valueOf(edit_key.getText());

    public void saveDataInFire(View view){

        String str1 = String.valueOf(t1.getText());
        String str2 = String.valueOf(t2.getText());
        String str3 = String.valueOf(t3.getText());
        String str4 = String.valueOf(t4.getText());

        //если все поля пустые, то выводим тостик с предупреждением
        if(str1.equals("") && str2.equals("") && str3.equals("") && str4.equals("")){
            Toast toast = makeText(this, "Заполните поля", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0,0);
            toast.show();
            //иначе делаем все остальное
        }else {

            Map<String, String> drugs = new HashMap<>();
            drugs.put("drug", str1);
            drugs.put("description", str2);
            drugs.put("test1", str3);
            drugs.put("test2", str4);

            //firebase.collection("apteka").document(str1).set(drugs);

            // Add a new document with a generated id
            firebase.collection("apteka").add(drugs).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast toast = makeText(getApplicationContext(), "method onSuccess", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0,0);
                            toast.show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            makeText(getApplicationContext(), "2222222", Toast.LENGTH_SHORT).show();
                        }
                    });



            //после сохранения немножко выждем время чтобы очистить поля для эффектности так сказать
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                makeText(this, "Какая-то шляпа с Thread.sleep", Toast.LENGTH_SHORT).show();
            }

            //очищаем поля
            makeText(this, "Внесено в базу данных", Toast.LENGTH_SHORT).show();
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");

        }
    }

}
