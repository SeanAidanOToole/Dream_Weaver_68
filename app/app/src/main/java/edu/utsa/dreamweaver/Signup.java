package edu.utsa.dreamweaver;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Scanner;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Signup extends ComponentActivity {
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        setupButton();
    }

    private void setupButton(){
        Button button = (Button) findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText usernameInput = (EditText) findViewById(R.id.signup_username);
                EditText passwordInput = (EditText) findViewById(R.id.signup_password);
                EditText nameInput = (EditText) findViewById(R.id.signup_name);
                EditText emailInput = (EditText) findViewById(R.id.signup_email);

                if(validateAccountInfo()){
                    createLogin();
                    finish();
                }
                else{
                    usernameInput.setText("");
                    passwordInput.setText("");
                    nameInput.setText("");
                    emailInput.setText("");
                    usernameInput.setError("All fields need to be filled out");
                    passwordInput.setError("All fields need to be filled out");
                    nameInput.setError("All fields need to be filled out");
                    emailInput.setError("All fields need to be filled out");
                }
            }
        });
    }
    private boolean validateAccountInfo(){
        EditText usernameInput = (EditText) findViewById(R.id.signup_username);
        EditText passwordInput = (EditText) findViewById(R.id.signup_password);
        EditText nameInput = (EditText) findViewById(R.id.signup_name);
        EditText emailInput = (EditText) findViewById(R.id.signup_email);

        if(!usernameInput.getText().toString().equals("") && !passwordInput.getText().toString().equals("")
        && !nameInput.getText().toString().equals("") && !emailInput.getText().toString().equals("")){
            return true;
        }
    return false;
    }

    private void createLogin(){
        EditText usernameInput = (EditText) findViewById(R.id.signup_username);
        EditText passwordInput = (EditText) findViewById(R.id.signup_password);
        EditText nameInput = (EditText) findViewById(R.id.signup_name);
        EditText emailInput = (EditText) findViewById(R.id.signup_email);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();

        File f = new File(getFilesDir().getAbsolutePath() + "/loginInfo.txt");
        OutputStreamWriter w = null;
        Scanner scan;
        int id = 1;
        String str = null;
        String [] arr;
        //if file do not exist then
            // don't update id
            // create new file &
        //end if

        // count number of lines and set id = number of lines + 1
        // set user information
        //insert at the bottom

        if(!f.exists()){
            //crating new file
            try{
                w = new OutputStreamWriter(openFileOutput("loginInfo.txt", MODE_PRIVATE));
                w.write(id + "," + username + "," + password + "," + name + "," + email);
                w.close();
            } catch (IOException e) {
                Toast.makeText(getBaseContext(),"IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else{
            //file exist

            try {
                scan = new Scanner(openFileInput("loginInfo.txt"));
                while(scan.hasNext()){
                    str = scan.nextLine();
                }
                if(str != null){
                    arr = str.split(",");
                    if(arr.length == 5){
                        id = Integer.parseInt(arr[0]) + 1;
                    }
                }
                scan.close();

                w = new OutputStreamWriter(openFileOutput("loginInfo.txt", MODE_APPEND));
                w.append("\n" + id + "," + username + "," + password + "," + name + "," + email);
                w.close();

            }
            catch (IOException e){
                Toast.makeText(getBaseContext(),"IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
