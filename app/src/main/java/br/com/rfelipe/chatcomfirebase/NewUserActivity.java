package br.com.rfelipe.chatcomfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewUserActivity extends AppCompatActivity {

    private EditText loginNovoUsuarioEditText;
    private EditText senhaNovoUsuarioEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        loginNovoUsuarioEditText = findViewById(R.id.loginNovoUsuarioEditText);
        senhaNovoUsuarioEditText = findViewById(R.id.senhaNovoUsuarioEditText);
        mAuth = FirebaseAuth.getInstance();

    }

    public void criarNovoUsuario (View view){
        String login = loginNovoUsuarioEditText.getText().toString();
        String senha = senhaNovoUsuarioEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(login, senha)
                .addOnSuccessListener((result) -> {
                    Toast.makeText(this,
                            result
                                    .getUser()
                                    .getDisplayName()
                                    .toString(), Toast.LENGTH_SHORT).show();
            finish();
        }).addOnFailureListener(error -> error.printStackTrace());

    }
}
