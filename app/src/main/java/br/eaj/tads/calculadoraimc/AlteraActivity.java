package br.eaj.tads.calculadoraimc;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AlteraActivity extends AppCompatActivity {
    Context c = this;
    public static int resultOne = 1;
    public static int resultTwo = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera);

        Intent intent =  getIntent();
        Bundle bundle = intent.getExtras();

        String controle =  bundle.getString("controle");
        double valor = 0;
        if(controle.equals("Peso")){
           valor = bundle.getDouble("valorPassado");
        }else if(controle.equals("Altura")){
           valor =  bundle.getDouble("valorPassado");
        }


        EditText valorAlterar = (EditText) findViewById(R.id.EvalorAlterar);
        TextView tx = (TextView) findViewById(R.id.Tx);

        valorAlterar.setText(""+ valor);
        tx.setText(controle);

        Button alterar = (Button) findViewById(R.id.Balterar);

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText valorAlterado = (EditText) findViewById(R.id.EvalorAlterar);
                Bundle  bundle =  new Bundle();
                bundle.putDouble("valorAlterado",Double.parseDouble(valorAlterado.getText().toString()));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(resultOne,intent);
                finish();
            }
        });
        Button cancelar = (Button) findViewById(R.id.Bcancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setResult(RESULT_CANCELED);
                finish();

            }
        });


    }
}
