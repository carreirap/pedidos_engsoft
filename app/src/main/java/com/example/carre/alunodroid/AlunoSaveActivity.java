package com.example.carre.alunodroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carre.alunodroid.rest.Aluno;
import com.example.carre.alunodroid.rest.Endereco;
import com.example.carre.alunodroid.restservice.AlunoService;
import com.example.carre.alunodroid.restservice.ControllerList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoSaveActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_save);

        mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void saveAluno(View view) {
        EditText ed = (EditText) findViewById(R.id.editNome);
        Aluno a = new Aluno();
        a.setNome(ed.getText().toString());
        ed = (EditText) findViewById(R.id.editCpf);
        a.setCPF(ed.getText().toString());
        ed = (EditText) findViewById(R.id.editIdade);
        a.setIdade(ed.getText().toString());
        a.setEndereco(new Endereco());
        ed = (EditText) findViewById(R.id.editRua);
        a.getEndereco().setLogradouro(ed.getText().toString());
        ed = (EditText) findViewById(R.id.editTNumero);
        a.getEndereco().setNumero(ed.getText().toString());
        ed = (EditText) findViewById(R.id.editBairro);
        a.getEndereco().setBairro(ed.getText().toString());
        ed = (EditText) findViewById(R.id.editCidade);
        a.getEndereco().setCidade(ed.getText().toString());
        ed = (EditText) findViewById(R.id.editEstado);
        a.getEndereco().setEstado(ed.getText().toString());
        ed = (EditText) findViewById(R.id.editCep);
        a.getEndereco().setCep(ed.getText().toString());
        gravar(a);
     }

     private void gravar(Aluno a) {

         AlunoService service = ControllerList.createService();

         Call<Aluno> call = service.saveAluno(a);
         call.enqueue(new Callback<Aluno>() {

             @Override
             public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                 if(response.isSuccessful()) {
                     Aluno alunoResponse = response.body();
                     CharSequence ms = "Aluno gravado com sucesso! Matricula: " + alunoResponse.getId();
                     Toast tost = Toast.makeText(getApplicationContext(), ms, Toast.LENGTH_LONG);
                     tost.show();
                                  //changesList.forEach(change -> System.out.println(change.subject));
                 } else {
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
