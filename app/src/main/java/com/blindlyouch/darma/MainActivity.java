package com.blindlyouch.darma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //メンバ変数
    private Button createBtn;
    private Button eyeBtn;
    private Button gridViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind
        createBtn = findViewById(R.id.createBtn);
        eyeBtn = findViewById(R.id.OKBtn);
        gridViewBtn = findViewById(R.id.gridViewBtn);

        //クリックイベント
        createBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplication(), EditActivity.class);
               startActivity(intent);
            }
        });

        gridViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), CustomRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
