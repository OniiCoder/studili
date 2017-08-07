package com.example.perez.studili;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.perez.studili.R.layout.activity_category;

public class MainActivity extends AppCompatActivity {



    //Object of RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    String[] titles;
    String[] texts;

//    String[] task_names = {"Call bae", "Study Laravel 5.4", "Finish my webDev Course", "Write my business proposal", "Make Eba for Mum", "Remind Dad for PCash", "Study for Monday Test", "Reconsider Offers"};
//    String[] task_texts = {"Make it ASAP. before she vex", "I must hit a milestone soon.", "So I can start developing real time projects", "my investor needs it ASAP", "Make she no vex", "Boys must survive na", "First Class is the target", "Maybe there can be a change of plan"};



    Toolbar toolbar;

    DatabaseHelper myDb;

    FloatingActionButton fab_main, fab_2, fab_3;
    TextView txtNewTask, txtNewBook;

    Animation fabOpen, fabClose, fabRotateCW, fabRotateACW;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Drawable logo = ContextCompat.getDrawable(this, R.drawable.ic_apps_white_24dp);
        getSupportActionBar().setIcon(logo);

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            if (toolbar.getChildAt(i) instanceof ImageView) {
                ImageView maybeLogo = ((ImageView) toolbar.getChildAt(i));
                if (maybeLogo.getDrawable() == logo) {
                    maybeLogo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent categoryActivity = new Intent(MainActivity.this, CategoryActivity.class);
                            startActivity(categoryActivity);
                        }
                    });
                    break;
                }
            }

        }





        //Getting Data from SQLite Database
        titles = myDb.getTitles();
        texts = myDb.getTexts();

        //Initialize Recycler View
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new RecyclerAdapter(titles, texts);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);




        //Call to Floating button animations and links
        floatingBtnAnimations();


        //New Intent for creating new task
        newTask();





    }

    public void newTask(){
        fab_2 = (FloatingActionButton)findViewById(R.id.fab_2);
        fab_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    public void floatingBtnAnimations(){
        fab_main = (FloatingActionButton)findViewById(R.id.fab_main);
        fab_2 = (FloatingActionButton)findViewById(R.id.fab_2);
        fab_3 = (FloatingActionButton)findViewById(R.id.fab_3);
        txtNewBook = (TextView)findViewById(R.id.txtNewBook);
        txtNewTask = (TextView)findViewById(R.id.txtNewTask);

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fabRotateCW = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        fabRotateACW = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    fab_2.startAnimation(fabClose);
                    fab_3.startAnimation(fabClose);
                    txtNewTask.startAnimation(fabClose);
                    txtNewBook.startAnimation(fabClose);

                    fab_main.startAnimation(fabRotateACW);
                    fab_2.setClickable(false);
                    fab_3.setClickable(false);

                    isOpen = false;
                } else{
                    fab_2.startAnimation(fabOpen);
                    fab_3.startAnimation(fabOpen);
                    txtNewTask.startAnimation(fabOpen);
                    txtNewBook.startAnimation(fabOpen);

                    fab_main.startAnimation(fabRotateCW);
                    fab_2.setClickable(true);
                    fab_3.setClickable(true);

                    isOpen = true;
                }
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id == R.id.action_settings){
            Toast.makeText(getApplicationContext(), "You selected 'Settings' option", Toast.LENGTH_SHORT).show();
        } else if(res_id == R.id.action_about){

                Toast.makeText(getApplicationContext(), "You selected 'About App' option", Toast.LENGTH_SHORT).show();

        } else if(res_id == R.id.action_help){

            Toast.makeText(getApplicationContext(), "You selected 'Help & Feedback' option", Toast.LENGTH_SHORT).show();

        } else if(res_id == R.id.action_notification){
            startActivity(new Intent(MainActivity.this, AllNotificationActivity.class));
            Toast.makeText(getApplicationContext(), "You selected 'Notification' option", Toast.LENGTH_SHORT).show();
        }

        return true;
    }


}
