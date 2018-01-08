package com.example.carre.alunodroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carre.alunodroid.rest.Aluno;
import com.example.carre.alunodroid.rest.Endereco;
import com.example.carre.alunodroid.restservice.AlunoService;
import com.example.carre.alunodroid.restservice.ControllerList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListAlunosActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_list_alunos);

        mTextMessage = (TextView) findViewById(R.id.message);

        carregar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void carregar() {

        AlunoService service = ControllerList.createService();

        Call<List<Aluno>> call = service.listAllAlunos();
        call.enqueue(new Callback<List<Aluno>>() {

            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                if(response.isSuccessful()) {
                    List<Aluno> changesList = response.body();
                    ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(getApplicationContext(),
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

                t.printStackTrace();
            }
        } );
    }



}
