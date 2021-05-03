package com.example.latihanstorage1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {
    private EditText editTextFolderName,editTextContentName,editTextFileName,tampil;
    private Button buttonCreate,buttonRead,buttonDelete,buttonUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View();
    }

    private void View (){
                editTextFolderName = findViewById(R.id.editTextFolderName);
                editTextContentName = findViewById(R.id.editTextContentName);
                tampil = findViewById(R.id.tampil);
                editTextFileName = findViewById(R.id.editTextFileName);
                buttonRead = findViewById(R.id.buttonRead);
                buttonRead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonRead_onClick(view);
                    }
                });
                buttonCreate = findViewById(R.id.buttonCreate);
                buttonCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonCreate_onClick(view);
                    }
                });
                buttonDelete = findViewById(R.id.buttonDelete);
                buttonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonDelete_onClick(view);
                    }
                });

        }

    private void buttonDelete_onClick(View view) {
        try {
            String folder = getApplication().getFilesDir().getAbsolutePath() + File.separator + editTextFolderName.getText().toString();
            File subFolder = new File(folder);
            File file = new File(folder, editTextFileName.getText().toString());
            if(file.exists()) {
                file.delete();
            }
            Toast.makeText(getApplicationContext(), getString(R.string.done), Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void buttonCreate_onClick(View view) {

        try{
            String folder = getApplication().getFilesDir().getAbsolutePath() + File.separator + editTextFolderName.getText().toString();
            File subFolder = new File(folder);
            if (!subFolder.exists()) {
                subFolder.mkdirs();
            }
            FileOutputStream outputStream = new FileOutputStream(new File(subFolder, editTextFileName.getText().toString()));
            outputStream.write(editTextContentName.getText().toString().getBytes());
            outputStream.close();
            Toast.makeText(getApplicationContext(), getString(R.string.done), Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void buttonRead_onClick(View view) {
       try {
           StringBuilder result = new StringBuilder();
           StringBuffer stringBuffer = new StringBuffer();
           String line;
           String folder = getApplication().getFilesDir().getAbsolutePath() + File.separator + editTextFolderName.getText().toString();
           File subFolder = new File(folder);
           BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(folder, editTextFileName.getText().toString())));
           while ((line = bufferedReader.readLine()) != null) {
               result.append(line);
           }
           editTextContentName.setText(result.toString());
           tampil.setText(result.toString());
           tampil.setEnabled(false);



       }catch (Exception e){
           Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
       }
}

}