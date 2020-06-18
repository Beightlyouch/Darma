package com.blindlyouch.darma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class DarmaActivity extends AppCompatActivity {

    private Button repairBtn;
    private Button OKBtn;
    private TextView wish_text_check;
    private RealmResults<Darma> rResults;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        //bind
        repairBtn = findViewById(R.id.back_ichiranBtn);
        OKBtn = findViewById(R.id.OKBtn);
        this.wish_text_check = findViewById(R.id.wish_text_check);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "RiiT_F.otf");
        this.wish_text_check.setTypeface(customFont);

        Intent intent =getIntent();
        String wish = intent.getStringExtra("wish");
        Toast.makeText(getApplication(),wish,Toast.LENGTH_LONG).show();
        this.wish_text_check.setText(wish);

        repairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),EditActivity.class);
                startActivity(intent);
            }
        });

        OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(),"かないますように",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplication(), CustomRecyclerViewActivity.class);
                startActivity(intent);
            }
        });


    }

    /*protected RealmResults<Darma> getNewDarma(){
        Number maxid = realm.where(Darma.class).max("darma_id");
        return realm.where(Darma.class).equalTo("darma_id", maxid.intValue()).findAll();
    }*/

}
