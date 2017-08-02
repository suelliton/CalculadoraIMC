package br.eaj.tads.calculadoraimc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static br.eaj.tads.calculadoraimc.AlteraActivity.resultOne;
import static br.eaj.tads.calculadoraimc.AlteraActivity.resultTwo;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    public final static int requestOne = 1;
    public final static int requestTwo = 2;
    Context c = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPeso = (Button) findViewById(R.id.BalterarPeso);

        buttonPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView valorPeso = (TextView) findViewById(R.id.Tpeso);

                Bundle bundle = new Bundle();
                bundle.putDouble("valorPassado",Double.parseDouble(valorPeso.getText().toString()));
                String controle = "Peso";
                bundle.putString("controle",controle);

                Intent intent = new Intent(c,AlteraActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,requestOne);
            }
        });

        Button buttonAltura = (Button) findViewById(R.id.BalterarAltura);

        buttonAltura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView valorAltura = (TextView) findViewById(R.id.Taltura);
                Bundle bundle = new Bundle();
                bundle.putDouble("valorPassado",Double.parseDouble(valorAltura.getText().toString()));
                String controle = "Altura";
                bundle.putString("controle",controle);
                Intent intent = new Intent(c,AlteraActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,requestTwo);
            }
        });

        final Button calcular = (Button) findViewById(R.id.Bcalcular);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calcular();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == requestOne){
            if(resultCode == resultOne){
                Bundle bundle = data.getExtras();
                double valorAlterado = bundle.getDouble("valorAlterado");
                TextView Tpeso = (TextView) findViewById(R.id.Tpeso);
                Tpeso.setText(""+valorAlterado);

            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(c, "Operação cancelada", Toast.LENGTH_SHORT).show();
            }

        }else if(requestCode == requestTwo){
            if(resultCode == resultOne){
                Bundle bundle = data.getExtras();
                double valorAlterado = bundle.getDouble("valorAlterado");
                TextView Taltura = (TextView) findViewById(R.id.Taltura);
                Taltura.setText(""+valorAlterado);
            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(c, "Operação cancelada", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void Calcular(){
        TextView Tpeso = (TextView) findViewById(R.id.Tpeso);
        TextView Taltura = (TextView) findViewById(R.id.Taltura);

        double IMC = Double.parseDouble(Tpeso.getText().toString())/Math.pow(Double.parseDouble(Taltura.getText().toString()),2);


        String message = "";
        if(IMC < 16){
            message = "Magreza Grave";
        }else if(IMC > 16 && IMC <17){
            message = "Magreza moderada";
        }else if(IMC > 17 && IMC <18.5){
            message = "Magreza leve";
        }else if(IMC > 18.5 && IMC <25){
            message = "Saudavel pai";
        }else if(IMC > 25 && IMC <30){
            message = "Sobrepeso";
        }else if(IMC > 30 && IMC <35){
            message = "Obesidade grau I";
        }else if(IMC > 35 && IMC <40){
            message = "Obesidade grau II";
        }else if(IMC > 40){
            message = "Obesidade grau III morbida";
        }
        TextView Timc=  (TextView) findViewById(R.id.Timc);
        TextView Tmessage =  (TextView) findViewById(R.id.Tdescricao);
        Timc.setText(""+IMC);
        Tmessage.setText(""+message);



    }




}
