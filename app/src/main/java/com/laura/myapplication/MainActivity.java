package com.laura.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private int qtde;
    private int jogador;
    private int mat[][] = new int[3][3];
    private Button b[] = new Button[9];
    private String ganhador;
    private String jogador1;
    private String jogador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qtde=1;
        jogador=1;
        b[0] = findViewById(R.id.bt_1);
        b[1] = findViewById(R.id.bt_2);
        b[2] = findViewById(R.id.bt_3);
        b[3] = findViewById(R.id.bt_4);
        b[4] = findViewById(R.id.bt_5);
        b[5] = findViewById(R.id.bt_6);
        b[6] = findViewById(R.id.bt_7);
        b[7] = findViewById(R.id.bt_8);
        b[8] = findViewById(R.id.bt_9);

        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogada(b[0], 0, 0);
            }
        });
        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogada(b[1], 0, 1);
            }
        });
        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogada(b[2], 0, 2);
            }
        });
        b[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogada(b[3], 1, 0);

            }
        });
        b[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogada(b[4], 1, 1);
            }
        });
        b[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogada(b[5], 1, 2);
            }
        });
        b[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogada(b[6], 2, 0);
            }
        });
        b[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogada(b[7], 2, 1);
            }
        });
        b[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogada(b[8], 2, 2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemNovo:
                limpar();

                final EditText editText2 = new EditText(this);
                AlertDialog.Builder segundoJogador = new AlertDialog.Builder(this);
                segundoJogador.setMessage("Digite o nome do jogador 2: ");
                segundoJogador.setTitle("JOGADOR 2");
                segundoJogador.setView(editText2);
                segundoJogador.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        jogador2 = editText2.getText().toString();
                    }
                });
                segundoJogador.create();
                segundoJogador.show();

                final EditText editText1 = new EditText(this);
                AlertDialog.Builder primeiroJogador = new AlertDialog.Builder(this);
                primeiroJogador.setMessage("Digite o nome do jogador 1: ");
                primeiroJogador.setTitle("JOGADOR 1");
                primeiroJogador.setView(editText1);
                primeiroJogador.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        jogador1 = editText1.getText().toString();
                    }
                });
                primeiroJogador.create();
                primeiroJogador.show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void jogada(Button b, int x, int y){
        b.setEnabled(true);
        if(jogador==1){
            mat[x][y] = 1;
            b.setText("X");
            jogador=2;
            ganhador=jogador1;
            checarJogada(1);
        }
        else{
            mat[x][y]=2;
            b.setText("O");
            jogador=1;
            ganhador=jogador2;
            checarJogada(2);
        }
        qtde++;
    }

    public boolean vitoria(int x){
        for(int i=0; i<mat.length; i++){
            if(mat[i][0]==x && mat[i][1]==x && mat[i][2]==x){
                return true;
            }
            if(mat[0][i]==x && mat[1][i]==x && mat[2][i]==x) {
                return true;
            }
        }
        if(mat[0][0]==x && mat[1][1]==x && mat[2][2]==x){
            return true;
        }
        if(mat[0][2]==x && mat[1][1]==x && mat[2][0]==x){
            return true;
        }
        return false;
    }

    public void checarJogada(int x){
        if(vitoria(x)){
            AlertDialog.Builder alertaVenceu = new AlertDialog.Builder(this);
            alertaVenceu.setTitle("VITÓRIA");
            alertaVenceu.setMessage("O jogador " + ganhador + " venceu!");
            alertaVenceu.setIcon(android.R.drawable.star_on);
            alertaVenceu.setPositiveButton("Ok", null);
            alertaVenceu.create();
            alertaVenceu.show();
            fimJogo();
        }
    }

    public void fimJogo(){
        for(int i=0; i<9;i++){
            b[i].setEnabled(false);
        }
    }

    public void limpar(){
        for(int i=0; i<9;i++){
            b[i].setEnabled(true);
            b[i].setText("");
        }
        for(int x=0; x<3;x++){
            for(int y=0; y<3;y++) {
                mat[x][y]=0;
            }
        }
        jogador=1;
        jogador1="";
        jogador2="";
        ganhador="";
    }
}

