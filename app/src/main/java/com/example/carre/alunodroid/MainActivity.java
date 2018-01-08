package com.example.carre.alunodroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void callListAlunos(View view) {
        EditText txtId = (EditText) findViewById(R.id.editId);
        String id = txtId.getText().toString();
        Intent intent = new Intent(this, ListAlunosActivity.class);
        intent.putExtra("idAluno", id);

        startActivity(intent);
    }

}
