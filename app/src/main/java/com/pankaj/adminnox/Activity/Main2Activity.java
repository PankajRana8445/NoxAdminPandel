package com.pankaj.adminnox.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.pankaj.adminnox.Adapter.MyAdapter;
import com.pankaj.adminnox.JavaClass.Utils;
import com.pankaj.adminnox.R;

public class Main2Activity extends AppCompatActivity implements
        SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener{

    private RecyclerView rv;
    public ProgressBar mProgressBar;
    private LinearLayoutManager layoutManager;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initializeViews();
        bindData();
    }


    private void initializeViews(){
        mProgressBar = findViewById(R.id.mProgressBarLoad);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);
        rv = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                layoutManager.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);
        adapter=new MyAdapter(Utils.DataCache);
        rv.setAdapter(adapter);
    }
    private void bindData(){
        Utils.select(this,Utils.getDatabaseRefence(),mProgressBar,rv,adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.scientists_page_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(true);
        searchView.setQueryHint("Search");
        return true;
    }
    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }
    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    @Override
    public boolean onQueryTextChange(String query) {
        Utils.searchString=query;
        Utils.search(this,Utils.getDatabaseRefence(),mProgressBar, adapter,query);
        return false;
    }
}
