package com.blindlyouch.darma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class EyeActivity extends AppCompatActivity {

    private Realm realm;
    private RealmResults<Darma> rResults;

    private String darma_wish;
    private int id;
    private int darma_eye_num;

    private Button OKBtn;
    private Button back_ichiranBtn;
    private Button back_titleBtn;
    private ImageView darma_for_eye;
    private ImageView left_eye;
    private ImageView right_eye;
    private TextView wish_text_check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye);

        //realm
        realm = Realm.getDefaultInstance();

        //bind
        OKBtn = findViewById(R.id.OKBtn);
        darma_for_eye = findViewById(R.id.darma_red_for_eye);
        back_ichiranBtn = findViewById(R.id.back_ichiranBtn);
        back_titleBtn = findViewById(R.id.back_titleBtn);
        left_eye = findViewById(R.id.left_eye);
        right_eye = findViewById(R.id.right_eye);
        wish_text_check= findViewById(R.id.wish_text_check);

        //extraを受け取る
        Intent intent = getIntent();
        darma_eye_num = intent.getIntExtra("darma_eye_num",0);
        darma_wish = intent.getStringExtra("darma_wish");
        id = intent.getIntExtra("id",0);

        //ボタンとか
        setVISIBILE();

        //textviewセット
        Typeface customFont = Typeface.createFromAsset(getAssets(), "RiiT_F.otf");
        this.wish_text_check.setTypeface(customFont);
        wish_text_check.setText(darma_wish);

        //クエリ
        final Darma darma = realm.where(Darma.class).equalTo("darma_id",id).findFirst();

        //クリックリスナー
        OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                darma_eye_num ++;
                //startScaling();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        darma.setDarma_eye(darma_eye_num);
                    }
                });
                setVISIBILE();
            }
        });

        back_ichiranBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), CustomRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        back_titleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.darma_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.action_delete:
                Toast.makeText(getApplication(),"aaaaa",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_edit:
                Intent intent = new Intent(getApplication(), EditActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
        }

        return true;
    }


    //可視・不可視
    public void setVISIBILE(){
        switch (darma_eye_num){
            case 0:
                left_eye.setVisibility(View.INVISIBLE);
                right_eye.setVisibility(View.INVISIBLE);
                OKBtn.setVisibility(View.VISIBLE);
                break;

            case 1:
                left_eye.setVisibility(View.INVISIBLE);
                right_eye.setVisibility(View.VISIBLE);
                OKBtn.setVisibility(View.VISIBLE);
                break;

            case 2:
                left_eye.setVisibility(View.VISIBLE);
                right_eye.setVisibility(View.VISIBLE);
                OKBtn.setVisibility(View.INVISIBLE);
                break;

            default:
                left_eye.setVisibility(View.INVISIBLE);
                right_eye.setVisibility(View.INVISIBLE);
                OKBtn.setVisibility(View.INVISIBLE);
                break;
        }
    }

/*
    private void startScaling() {
        // ScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 0.2f, 1.0f, 0.2f,
                Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // animation時間 msec
        scaleAnimation.setDuration(1000);
        // 繰り返し回数
        scaleAnimation.setRepeatCount(0);
        // animationが終わったそのまま表示にする
        scaleAnimation.setFillAfter(true);
        //アニメーションの開始
        darma_for_eye.startAnimation(scaleAnimation);
    }
    private void startTranslate(){
        // TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue)
        TranslateAnimation translateAnimation = new TranslateAnimation(
            Animation.ABSOLUTE, 0.0f,
            Animation.ABSOLUTE, 500.0f,
            Animation.ABSOLUTE, 0.0f,
            Animation.ABSOLUTE, 1200.0f);

        // animation時間 msec
        translateAnimation.setDuration(2000);
        // 繰り返し回数
        translateAnimation.setRepeatCount(0);
        // animationが終わったそのまま表示にする
        translateAnimation.setFillAfter(true);
        //アニメーションの開始
        //imageView.startAnimation(translateAnimation);
    }
*/


}
