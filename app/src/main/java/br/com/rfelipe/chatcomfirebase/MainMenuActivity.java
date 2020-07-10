package br.com.rfelipe.chatcomfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void  chatStart(String categoria){
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("categoria", categoria);
        startActivity(intent);

    }

    public void cinemaButton (View view){
        chatStart("cinema");
    }

    public void newsButton (View view){
        chatStart("novidades");
    }

    public void techButton (View view){
        chatStart("tecnologia");
    }

    public void economyButton (View view){
        chatStart("economia");
    }

}
