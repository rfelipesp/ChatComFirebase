package br.com.rfelipe.chatcomfirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mensagensRecyclerView;
    private ChatAdapter adapter;
    private List<Mensagem> mensagens;
    private EditText mensagemEditText;
    private FirebaseUser firebaseUser;
    private CollectionReference mensagensReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mensagensRecyclerView = findViewById(R.id.mensagensRecyclerView);
        mensagens = new ArrayList<>();
        adapter = new ChatAdapter(mensagens, this);
        mensagensRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mensagensRecyclerView.setLayoutManager(linearLayoutManager);
        mensagemEditText = findViewById(R.id.mensagemEditText);


    }
    private void setupFirebase (String categoria){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mensagensReference = FirebaseFirestore.getInstance().collection(categoria);
        getRemoteMsgs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupFirebase(getCategoria());

    }

    public String getCategoria(){
        String categoria = "categoria";
        String valor = "mensagens";
        if (getIntent().hasExtra(categoria)){
            valor = getIntent().getStringExtra(categoria);
        }
        return valor;
    }

    private void getRemoteMsgs (){
        mensagensReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e){
                mensagens.clear();
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()){
                    Mensagem m = doc.toObject(Mensagem.class);
                    mensagens.add(m);
                }
                Collections.sort(mensagens);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void enviarMensagem (View view){
        String texto = mensagemEditText.getText().toString();
        Mensagem m = new Mensagem (firebaseUser.getEmail(), new Date(), texto);
        mensagensReference.add(m);
    }
}