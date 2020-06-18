package com.blindlyouch.darma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class CustomRecyclerViewActivity extends AppCompatActivity{
    private GridView gridView;
    private RecyclerView recyclerView;
    private CustomRecyclerViewAdapter adapter;
    private Realm realm;
    private RealmResults<Darma> darmas;
    private RecyclerView.LayoutManager layoutManager;
    private ViewHolder viewHolder;
    private ItemTouchHelper itemTouchHelper;
    private DividerItemDecoration dividerItemDecoration;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        //adapter
        recyclerView = findViewById(R.id.darma_recycle);


        realm = Realm.getDefaultInstance();
        darmas = realm.where(Darma.class).findAll().sort("darma_id", Sort.ASCENDING);
    }

    @Override
    protected void onStart(){
        super.onStart();
        darmas = realm.where(Darma.class).findAll();

        layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomRecyclerViewAdapter(darmas);
        recyclerView.setAdapter(adapter);

        // 並べ替え・削除には別途トランザクション処理が必要
        /*
        * 並び替え　->一覧に戻ったあと元通り
        * 削除　->一覧の一番下へ移動
        * */
        /*
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP,itemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                recyclerView.getAdapter().notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int from = viewHolder.getAdapterPosition();
                recyclerView.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.darma_return, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.action_return:
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }

}
