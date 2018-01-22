package com.example.carre.alunodroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carre.alunodroid.rest.Aluno;
import com.example.carre.alunodroid.restservice.AlunoService;
import com.example.carre.alunodroid.restservice.ControllerList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void buscarAluno(View view) {
        EditText txtId = (EditText) findViewById(R.id.editId);
        String id = txtId.getText().toString();
    }

    public void callListAlunos(View view) {
        EditText txtId = (EditText) findViewById(R.id.editId);
        String id = txtId.getText().toString();
        Intent intent = new Intent(this, ListAlunosActivity.class);
        intent.putExtra("idAluno", id);

        startActivity(intent);
    }

    public void callBuscarAlunos(View view) {
        EditText txtId = (EditText) findViewById(R.id.editId);
        String id = txtId.getText().toString();
        Intent intent = new Intent(this, ListAlunosActivity.class);
        intent.putExtra("idAluno", id);

        startActivity(intent);
    }

    public void callSaveAlunos(View view) {
       // EditText txtId = (EditText) findViewById(R.id.editId);
        //String id = txtId.getText().toString();
        Intent intent = new Intent(this, AlunoSaveActivity.class);
        //intent.putExtra("idAluno", id);

        startActivity(intent);
    }


    public void carregar(String id) {

        AlunoService service = ControllerList.createService();

        Call<Aluno> call = service.getAluno(id);
        call.enqueue(new Callback<Aluno>() {

            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if(response.isSuccessful()) {
                    Aluno a = response.body();

                    Intent intent = new Intent(MainActivity.this, AlunoSaveActivity.class);
                    intent.putExtra("aluno", a);

                    startActivity(intent);

                } else {
                    Toast tost = Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_LONG);
                    tost.show();
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                CharSequence ms = t.getMessage();
                Toast tost = Toast.makeText(getApplicationContext(), ms, Toast.LENGTH_LONG);
                tost.show();
                t.printStackTrace();
            }
        } );
    }

}
