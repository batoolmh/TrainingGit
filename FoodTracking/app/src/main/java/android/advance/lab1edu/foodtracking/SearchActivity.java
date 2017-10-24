package android.advance.lab1edu.foodtracking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    String [] txt =  {"Nuts & Seeds 100g     700 calories","Nuts & Seeds cup (132g)     948 calories","Nuts & Seeds ounce (28g)     201 calories",
            "Chocolate 100g     598 calories", "Chocolate bar (101g)     604 calories","Chocolate ounce (28g)     167 calories",
            "Avocados 100g     160 calories", "Avocados cubes(150g)    240 calories","Avocados avocado (201g)     332 calories",
            "Milk, Dairy & Eggs 100g    452 calories", "Milk, Dairy & Eggs 2 oz (56g)     254 calories","Milk, Dairy & Eggs ounce (28g)    127 calories",
            "Oily Fish 100g     262 calories" , "Oily Fish fillet (88g)     231 calories" , "Oily Fish 3oz (85g)     223 calories"};

    ArrayAdapter adapter;
    MaterialSearchView searchView;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Food Tracking");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        lv = (ListView)findViewById(R.id.lstView);
        adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,txt);
        lv.setAdapter(adapter);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener(){

            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                lv = (ListView)findViewById(R.id.lstView);
                ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,txt);
                lv.setAdapter(adapter);

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener(){

            /**
             * Called when the user submits the query. This could be due to a key press on the
             * keyboard or due to pressing a submit button.
             * The listener can override the standard behavior by returning true
             * to indicate that it has handled the submit request. Otherwise return false to
             * let the SearchView handle the submission by launching any associated intent.
             *
             * @param query the query text that is to be submitted
             * @return true if the query has been handled by the listener, false to let the
             * SearchView perform the default action.
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            /**
             * Called when the query text is changed by the user.
             *
             * @param newText the new content of the query text field.
             * @return false if the SearchView should perform the default action of showing any
             * suggestions if available, true if the action was handled by the listener.
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()){
                    List<String> lstFound = new ArrayList<String>();
                    for(String item: txt){
                        if(item.contains(newText))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,lstFound);
                    lv.setAdapter(adapter);
                }
                else {
                    //if search ext is null, return default
                    ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,txt);
                    lv.setAdapter(adapter);
                }
                return true;
            }
        });

       // read = (Button)findViewById(R.id.button12);
       // text = (TextView)findViewById(R.id.textView21);










       // adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, txt);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_profile){
            Intent intent = new Intent(SearchActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.menu_report){
            Intent intent = new Intent(SearchActivity.this, ReportActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.exit_ex){
            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }
}
