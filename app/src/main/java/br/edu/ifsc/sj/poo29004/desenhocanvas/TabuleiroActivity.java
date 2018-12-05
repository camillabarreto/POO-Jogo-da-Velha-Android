package br.edu.ifsc.sj.poo29004.desenhocanvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TabuleiroActivity extends AppCompatActivity {

    private int jogadorDaVez = 1;
    private JogoDaVelha jogoDaVelha;
    private Button[][] matrizButtons;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabuleiro);

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

        this.jogoDaVelha = new JogoDaVelha();
    }

    public void click(View view) {
        System.out.println("CLICK BUTTON " + posiçãoMatriz(view));

        Button b = findViewById(view.getId());
        if(jogoDaVelha.jogar(posiçãoMatriz(view))){
            System.out.println("JOGADA VÁLIDA");
            b.setText(Integer.toString(this.jogoDaVelha.getJogadorDaVez()));

            if(this.jogoDaVelha.ganhou()){
                System.out.println("GANHOU - jogador: " + jogoDaVelha.getJogadorDaVez());
                this.esvaziaTabuleiro();
            }
        }
    }

    private String posiçãoMatriz(View view){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(view.getId() == matrizButtons[i][j].getId()){
                    return i + "" + j;
                }
            }

        }
        return "";
    }

    private void esvaziaTabuleiro(){
        this.jogoDaVelha.esvaziaTabuleiro();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.matrizButtons[i][j].setText("");
            }
        }
    }

    /**
     * Esse método é responsável por realizar as jogadas da Inteligencia (PC)
     */
    private void jogadaInteligente(){
    }

    /**
     * Esse método é responsável por verificar se alguém ganhou
     * @return true: ganhou - false: não ganhou
     */
    private boolean ganhou(){
        return false;
    }

    /**
     * Esse método é responsável por verificar se a posição tocada é válida de jogada
     * @return true: válida - false: inválida
     */
    private boolean posiçãoVálida(){
        //se posição clicada entre p0 e p1
        //onde p0, p1, etc são os limite superiores de cada posição do tabuleiro


        return false;
    }
}
