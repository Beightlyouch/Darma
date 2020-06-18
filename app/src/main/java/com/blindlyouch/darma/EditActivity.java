package com.blindlyouch.darma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;

public class EditActivity extends AppCompatActivity {
    private Realm realm;
    private Button createBtn2; //作成ボタン
    private EditText darma_wish_edit;
    private int darmaId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        //bind
        createBtn2 = findViewById(R.id.createBtn2);
        darma_wish_edit = findViewById(R.id.darma_wish);

        realm = Realm.getDefaultInstance(); //realmオブジェクトを取得

        Intent intent = getIntent();
        darmaId = intent.getIntExtra("id", 0);

        if(darmaId > 0){
            Darma darma = realm.where(Darma.class).equalTo("darma_id",darmaId).findFirst();
            darma_wish_edit.setText(darma.getDarma_wish());
        }



        createBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((darma_wish_edit.getText().toString().equals("") || darma_wish_edit.getText().toString() == null)){
                    Toast.makeText(getApplication(),"未入力です",Toast.LENGTH_LONG).show();
                }else {

                    Intent intent = new Intent(getApplication(),DarmaActivity.class);
                    intent.putExtra("wish",darma_wish_edit.getText().toString());
                    startActivity(intent);

                    if(darmaId == 0){
                        onRegister();
                    }else{
                        onUpdate();
                    }
                }

            }
        });

    }

    public void onRegister(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final String wish = darma_wish_edit.getText().toString();
                //start of transaction
                int nextid;
                Number maxid = realm.where(Darma.class).max("darma_id");

                if (maxid != null) {
                    nextid = maxid.intValue() + 1;
                } else {
                    nextid = 0;
                }

                Darma darma = realm.createObject(Darma.class, nextid);
                darma.setDarma_wish(wish);
            }
        });

        finish();
        realm.close();
    }

    public void onUpdate(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final String wish = darma_wish_edit.getText().toString();
                //start of transaction
                Darma darma = realm.where(Darma.class).equalTo("darma_id",darmaId).findFirst();
                darma.setDarma_wish(wish);

            }
        });

        finish();
        realm.close();
    }




    /*
    @Override
    public void onDestroy(){
        super.onDestroy();
        realm.close();
    }
    */
}
