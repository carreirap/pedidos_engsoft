package com.example.carre.alunodroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carre.alunodroid.rest.Aluno;
import com.example.carre.alunodroid.rest.Endereco;
import com.example.carre.alunodroid.restservice.AlunoService;
import com.example.carre.alunodroid.restservice.ControllerList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListAlunosActivity extends AppCompatActivity {

    private TextView mTextMessage;

    List<Aluno> changesList;
    ArrayAdapter<Aluno> adapter;

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
        setContentView(R.layout.activity_list_alunos);
        setTheme(R.style.AppTheme);
        mTextMessage = (TextView) findViewById(R.id.message);

        ListView v = (ListView) findViewById(R.id.lstView);
        v.setLongClickable(true);
        v.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Aluno a = (Aluno) adapterView.getItemAtPosition(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(ListAlunosActivity.this);

                builder.setMessage("Deseja deletar o aluno matricula " + a.getId() + "?");
                builder.setPositiveButton("Sim", createEvent(a));
                builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();

                dialog.show();

                return false;
            }
        });

        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Aluno a = (Aluno) adapterView.getItemAtPosition(i);
                 Intent intent = new Intent(ListAlunosActivity.this, AlunoSaveActivity.class);
                 intent.putExtra("aluno", a);

                 startActivity(intent);
             }
         });

                String id = "";
        Bundle extras = getIntent().getExtras();
        id= extras.getString("idAluno");
        if (id != null && !id.equals("")) {
            carregarAlunoId(id);
        } else {
            carregar();
        }


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private DialogInterface.OnClickListener createEvent(final Aluno a) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deletar(a);
                changesList.remove(a);
                adapter.notifyDataSetChanged();
            }
        };
        return onClickListener;
    }

    public void carregar() {

        AlunoService service = ControllerList.createService();

        Call<List<Aluno>> call = service.listAllAlunos();
        call.enqueue(new Callback<List<Aluno>>() {

            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                if(response.isSuccessful()) {
                    changesList = response.body();
                    adapter = new ArrayAdapter<Aluno>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, changesList);
                    ListView v = (ListView) findViewById(R.id.lstView);

                    v.setAdapter(adapter);

                    //changesList.forEach(change -> System.out.println(change.subject));
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                CharSequence ms = t.getMessage();
                Toast tost = Toast.makeText(getApplicationContext(), ms, Toast.LENGTH_LONG);
                tost.show();
                t.printStackTrace();
            }
        } );
    }

    public void deletar(Aluno a) {
        AlunoService service = ControllerList.createService();

        Call<Aluno> call = service.deleteAluno(a.getId().toString());
        call.enqueue(new Callback<Aluno>() {

            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if(response.isSuccessful()) {
                    Aluno aluno = response.body();
                    Toast tost = Toast.makeText(getApplicationContext(), "Aluno deleted!", Toast.LENGTH_LONG);
                    tost.show();

                } else {
                    Toast tost = Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_LONG);
                    tost.show();
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                Toast tost = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                tost.show();
                t.printStackTrace();
            }
        } );

    }


    public void carregarAlunoId(String id) {

        AlunoService service = ControllerList.createService();

        Call<Aluno> call = service.getAluno(id);
        call.enqueue(new Callback<Aluno>() {

            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if(response.isSuccessful()) {
                    Aluno changesList = response.body();
                    List<Aluno> lst = Arrays.asList(changesList);
                    ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, lst);
                    ListView v = (ListView) findViewById(R.id.lstView);
                    v.setAdapter(adapter);

                    //changesList.forEach(change -> System.out.println(change.subject));
                } else {
                    Toast tost = Toast.makeText(getApplicationContext(), "Error code: " + String.valueOf(response.code()), Toast.LENGTH_LONG);
                    tost.show();
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                Toast tost = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                tost.show();
                t.printStackTrace();
            }
        } );
    }


}
