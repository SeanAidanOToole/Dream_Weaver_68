package edu.utsa.dreamweaver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

public class MainActivity extends ComponentActivity {
    Button button1;
    Button button2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        setupButtons();
    }
    private void setupButtons(){
        button1 = (Button) findViewById(R.id.login_button);
        button2 = (Button) findViewById(R.id.signup_button);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText uname = (EditText) findViewById(R.id.input_username);
                EditText upass = (EditText) findViewById(R.id.input_password);
                int id = authenticate(uname.getText().toString(), upass.getText().toString());
                if (id > 0) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                else{
                    uname.setText("");
                    upass.setText("");
                    uname.setError("Username not found");
                    upass.setError("Incorrect Password");
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }
        });
    }


    private int authenticate (String username, String password){

        Scanner scan;
        String str = "";
        String [] arr = null;
        int id = -1;
        File f = new File(getFilesDir().getAbsolutePath() + "/loginInfo.txt");

        try{
            if(f.exists()){
                scan = new Scanner(openFileInput("loginInfo.txt"));
                while(scan.hasNext()){
                    str = scan.nextLine();
                    arr = str.split(",");
                    if(username.equals(arr[1]) && password.equals(arr[2])){
                        id = Integer.parseInt(arr[0]);
                        break;
                    }
                }
                scan.close();
            }
        }
        catch (IOException e){
            System.out.print("Error" + e.getMessage());
        }
        return id;
    }
}
