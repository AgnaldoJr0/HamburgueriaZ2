package com.example.hamburgueriaz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {
      private int Quantidade = 0;
      private EditText edtNome;
      private CheckBox chkBacon, chkQueijo, chkOnionRings;
      private TextView txtResumoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNome = findViewById(R.id.editText);
        chkBacon = findViewById(R.id.checkBox1);
        chkQueijo = findViewById(R.id.checkBox2);
        chkOnionRings = findViewById(R.id.checkBox3);
        txtResumoPedido = findViewById(R.id.txtResumopedido);
    }

    public void subtrairQuantidade(View view){
        if(Quantidade > 0){
            Quantidade--;

            atualizarQuantidadeNaView();
        }else{
            Toast.makeText(this, "A quantidade não pode ser menor que zero!!!", Toast.LENGTH_SHORT).show();
        }
    }
    public void somarQuantidade(View view){
        Quantidade++;

        atualizarQuantidadeNaView();
    }
    public void atualizarQuantidadeNaView(){
        TextView TxtQuantidade = findViewById(R.id.txtQuantidade);
        TxtQuantidade.setText(Integer.toString(Quantidade));
    }

    public void FazerPedido(View view) {

        String nomeCliente = edtNome.getText().toString();

        boolean Bacon = chkBacon.isChecked();
        boolean Queijo = chkQueijo.isChecked();
        boolean OnionRings = chkOnionRings.isChecked();

        int quantidade = Quantidade;

        double precoBase = 20.0;
        double precoTotal = precoBase + ( (quantidade * (Bacon ? 2.0 : 0.0)) + (quantidade * (Queijo ? 2.0 : 0.0)) + (quantidade * (OnionRings ? 3.0 : 0.0)) );

        String resumoPedido = "Nome do cliente: " + nomeCliente + "\n" +
                "Tem Bacon? " + (Bacon ? "Sim" : "Não") + "\n" +
                "Tem Queijo? " + (Queijo ? "Sim" : "Não") + "\n" +
                "Tem Onion Rings? " + (OnionRings ? "Sim" : "Não") + "\n" +
                "Quantidade: " + quantidade + "\n" +
                "Preço final: R$ " + precoTotal;

        txtResumoPedido.setText(resumoPedido);
    }
     public void enviarPedido(View view){

         String nomeCliente = edtNome.getText().toString();

         boolean Bacon = chkBacon.isChecked();
         boolean Queijo = chkQueijo.isChecked();
         boolean OnionRings = chkOnionRings.isChecked();

         int quantidade = Quantidade;

         double precoBase = 20.0;
         double precoTotal = precoBase + ( (quantidade * (Bacon ? 2.0 : 0.0)) + (quantidade * (Queijo ? 2.0 : 0.0)) + (quantidade * (OnionRings ? 3.0 : 0.0)) );

         String resumoPedido = "Nome do cliente: " + nomeCliente + "\n" +
                 "Tem Bacon? " + (Bacon ? "Sim" : "Não") + "\n" +
                 "Tem Queijo? " + (Queijo ? "Sim" : "Não") + "\n" +
                 "Tem Onion Rings? " + (OnionRings ? "Sim" : "Não") + "\n" +
                 "Quantidade: " + quantidade + "\n" +
                 "Preço final: R$ " + precoTotal;

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:"));

         intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de " + nomeCliente);

         intent.putExtra(Intent.EXTRA_TEXT, resumoPedido);

         try {

             if (intent.resolveActivity(getPackageManager()) != null) {
                 startActivity(intent);
             } else {
             }
         } catch (ActivityNotFoundException e) {
         }
     }

     public void FazerPedidoEEnviar(View view){
        FazerPedido(view);
        enviarPedido(view);
     }
}