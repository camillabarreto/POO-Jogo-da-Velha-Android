package br.edu.ifsc.sj.poo29004.desenhocanvas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TabuleiroActivity extends AppCompatActivity {

    private JogoDaVelha jogoDaVelha;
    private Button[][] matrizButtons;
    private String[] simbolo;
    private TextView[] placar;
    private int INDICE_JOGADOR = 0;
    private int INDICE_INTELIGENCIA = 1;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabuleiro);
        Log.d(TAG, "=============================| OnCreate");

        this.simbolo = new String[2];
        this.simbolo[INDICE_JOGADOR] = "X"; //simbolo do Jogador usuário
        this.simbolo[INDICE_INTELIGENCIA] = "O"; //simbolo do PC inteligencia

        matrizButtons = new Button[3][3];
        matrizButtons[0][0] = findViewById(R.id.button00);
        matrizButtons[0][1] = findViewById(R.id.button01);
        matrizButtons[0][2] = findViewById(R.id.button02);
        matrizButtons[1][0] = findViewById(R.id.button10);
        matrizButtons[1][1] = findViewById(R.id.button11);
        matrizButtons[1][2] = findViewById(R.id.button12);
        matrizButtons[2][0] = findViewById(R.id.button20);
        matrizButtons[2][1] = findViewById(R.id.button21);
        matrizButtons[2][2] = findViewById(R.id.button22);

        placar = new TextView[2];
        placar[INDICE_JOGADOR] = findViewById(R.id.placarEu);
        placar[INDICE_INTELIGENCIA] = findViewById(R.id.placarPc);

        this.jogoDaVelha = new JogoDaVelha();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "=============================| OnPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "=============================| OnStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "=============================| OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "=============================| OnRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "=============================| OnResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "=============================| OnDestroy");
    }

    /**
     * Este método será responsável por salvar as informações atuais do jogo
     * - Matriz de botões
     * - Instancia Jogo da Velha
     * - Placar
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "=============================| OnSaveInstanceState");
        //outState.putInt("matrizBotões", matrizButtons);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "=============================| OnBackPressed");

        //implementar diálogo aqui
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("TESTE")
                .setCancelable(false)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAndRemoveTask();
                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void click(View view) {
        Button b = findViewById(view.getId());

        //Se jogada não for válida
        if(!jogoDaVelha.jogar(posiçãoMatriz(view))) return;

        //Se jogada válida, desenhar simbolo do jogadorDaVez
        b.setText(getSimbolo());

        if(!fimJogo()){
                //System.out.println("JOGADA INTELIGENTE");
                String pos = this.jogoDaVelha.inteligencia();
                int i = Integer.parseInt(pos.substring(0,1));
                int j = Integer.parseInt(pos.substring(1));
                matrizButtons[i][j].setText(getSimbolo());
                fimJogo();
        }

    }

    /**
     * Esse método é responsável por verificar se o jogo chegou ao fim
     * @return true se ocorreu vitória ou velha, false se não ocorreu nenhuma das condições
     */
    private boolean fimJogo(){
        if(jogoDaVelha.ganhou()){

            if(jogoDaVelha.getJogadorDaVez() == 1){

                String a = placar[INDICE_JOGADOR].getText().toString();
                int i = Integer.parseInt(a) + 1;
                placar[INDICE_JOGADOR].setText(""+i);

            }else{

                String a = placar[INDICE_INTELIGENCIA].getText().toString();
                int i = Integer.parseInt(a) + 1;
                placar[INDICE_INTELIGENCIA].setText(""+i);

            }

            System.out.println("GANHOU - jogador " + getSimbolo());

        }else if(jogoDaVelha.tabuleiroCheio()) {

            System.out.println("DEU VELHA");

        }else return false;

        esvaziaTabuleiro();
        return true;
    }

    private String posiçãoMatriz(View view){
        String pos = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(view.getId() == matrizButtons[i][j].getId()){
                    pos = i + "" + j;
                    return pos;
                }
            }
        }
        return pos;
    }

    private void esvaziaTabuleiro(){
        jogoDaVelha.esvaziaTabuleiro();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizButtons[i][j].setText("");
            }
        }
    }

    /**
     * Este método retorna o simbolo do jogador que realizou a última jogada
     * @return X (Jogador usuário) ou O (Inteligencia)
     */
    private String getSimbolo() {
        if(jogoDaVelha.getJogadorDaVez() == 1) return simbolo[INDICE_JOGADOR];
        else return simbolo[INDICE_INTELIGENCIA];
    }
}
