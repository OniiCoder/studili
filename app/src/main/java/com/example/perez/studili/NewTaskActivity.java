package com.example.perez.studili;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewTaskActivity extends AppCompatActivity {

    EditText task_title_field, task_interval_field, task_text_field;

    DatabaseHelper myDb;

    FloatingActionButton fab_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        myDb = new DatabaseHelper(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Drawable logo = ContextCompat.getDrawable(this, R.drawable.ic_action_add_white);
        getSupportActionBar().setIcon(logo);

//        fab_create = (FloatingActionButton) findViewById(R.id.fab_create);
//        fab_create.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Task created successfully", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        //Call to Database Insert
        addData();
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
            Toast.makeText(getApplicationContext(), "You selected settings option", Toast.LENGTH_SHORT).show();
        }

        return true;
    }


    //Database Inserting
    public void addData(){
        task_title_field = (EditText)findViewById(R.id.task_title_field);
        task_text_field = (EditText)findViewById(R.id.task_text_field);
        task_interval_field = (EditText)findViewById(R.id.task_interval_field);

        fab_create = (FloatingActionButton)findViewById(R.id.fab_create);
        fab_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted;
                if(task_title_field.getText().toString().equals("") || task_text_field.getText().toString().equals("") || task_interval_field.getText().equals("")){
                    Toast.makeText(NewTaskActivity.this, "Please fill the form to create a new Task!", Toast.LENGTH_SHORT).show();
                } else{
                    isInserted = myDb.insertData(task_title_field.getText().toString(),
                            task_text_field.getText().toString(),
                            task_interval_field.getText().toString());

                    if(isInserted = true){
                        Toast.makeText(NewTaskActivity.this, "Task Created Successfully", Toast.LENGTH_SHORT).show();

                        startActivity( new Intent(NewTaskActivity.this, MainActivity.class));
                    } else{
                        Toast.makeText(NewTaskActivity.this, "Sorry, something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });


    }

}
