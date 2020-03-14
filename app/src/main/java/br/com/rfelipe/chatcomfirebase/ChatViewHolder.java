package br.com.rfelipe.chatcomfirebase;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ChatViewHolder extends RecyclerView.ViewHolder{
    TextView dataNomeTextView;
    TextView mensagemTextView;
    ChatViewHolder (View v){
        super (v);
        this.dataNomeTextView = v.findViewById(R.id.dataNomeTextView);
        this.mensagemTextView = v.findViewById(R.id.mensagemTextView);
    }
}
