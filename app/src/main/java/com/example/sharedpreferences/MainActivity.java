package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 1.0 attributes
    EditText t1, t2;
    Button savebtn, delbtn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.2 connection to xml id
        t1=(EditText)findViewById(R.id.t1);
        t2=(EditText)findViewById(R.id.t2);
        tv=(TextView)findViewById(R.id.tv);
        savebtn=(Button)findViewById(R.id.savebtn);
        delbtn=(Button)findViewById(R.id.delbtn);

        // 1.9 check credentials after saving
        checkexistedrecord();

        // 1.3 action on click
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usename=t1.getText().toString().trim();
                String password=t2.getText().toString().trim();
                // check if username and password are blank
                if(usename.isEmpty()||password.isEmpty()) {
                    tv.setText("Cannot be saved!! Please fill the username and password field.");
                }
                else {
                    // 1.4 open file
                    SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                    // 1.5 write with editor
                    SharedPreferences.Editor editor = sp.edit();
                    // 1.6 write
                    editor.putString("username", t1.getText().toString().trim());
                    editor.putString("password", t2.getText().toString().trim());
                    // 1.7 save
                    editor.apply();
                    // 1.8 after saving clearing the fields
                    t1.setText("");
                    t2.setText("");
                    tv.setText("Saved successfully");
                    tv.setTextColor(Color.GREEN);
                }
            }
        });

        // 1.12 action on click
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1.13 open file
                SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
//                // 1.14 write with editor
//                SharedPreferences.Editor editor=sp.edit();
//                // 1.15 clear all
//                editor.clear();
//                // 1.16 save
//                editor.apply();
//                // 1.17 after deleting clearing the fields
//                t1.setText("");
//                t2.setText("");
//                tv.setText("Deleted successfully");
//                tv.setTextColor(Color.RED);

                // check if username and password exist in the record file
                if (sp.contains("username")) {
                    // 1.14 write with editor
                    SharedPreferences.Editor editor=sp.edit();
                    // 1.15 clear all
                    editor.clear();
                    // 1.16 save
                    editor.apply();
                    // 1.17 after deleting clearing the fields
                    t1.setText("");
                    t2.setText("");
                    tv.setText("Deleted successfully");
                    tv.setTextColor(Color.RED);
                }
                else{
                    tv.setText("Cannot be deleted!! Record not found.");
                    tv.setTextColor(Color.RED);
                }

            }
        });

    }

    // 1.9 check record method
    private void checkexistedrecord() {
        // 1.10 open file
        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        // 1.11 check if fields exist
        if (sp.contains("username")) {
            t1.setText(sp.getString("username",""));
            t2.setText(sp.getString("password",""));
            tv.setText("Records found");
        }
        else{
            tv.setText("Record not found.");
            tv.setTextColor(Color.RED);
        }
    }

}