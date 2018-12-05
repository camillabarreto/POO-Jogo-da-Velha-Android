package br.edu.ifsc.sj.poo29004.desenhocanvas;

public class JogoDaVelha {
    //JLabel vai ser Integer
    //tirar os getText()

    //Posições da matriz
    // 0 - ninguém jogou
    // 1 - PC jogou
    // -1 - pessoa jogou

    private Integer[][] matrizTabuleiro;
    private Integer jogadorDaVez;

    public JogoDaVelha() {
        this.jogadorDaVez = -1;
        this.matrizTabuleiro = new Integer[3][3];
        this.esvaziaTabuleiro();
    }

    public Integer getJogadorDaVez() {
        return jogadorDaVez;
    }

    //String posiçãoMatriz no formato formato: ij (i: linha , j: coluna)
    public boolean jogar(String posiçãoMatriz){
        if(jogadorDaVez == 1) jogadorDaVez = -1;
        else jogadorDaVez = 1;

        Integer linha = Integer.parseInt(posiçãoMatriz.substring(0, 1)) ;
        Integer coluna = Integer.parseInt(posiçãoMatriz.substring(1));

        if(this.matrizTabuleiro[linha][coluna] == 0){
            this.matrizTabuleiro[linha][coluna] = this.jogadorDaVez;

            return true;
        }

        return false;
    }

    public boolean tabuleiroCheio(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(matrizTabuleiro[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean ganhou(){
        int aux = 0;
        //linhas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.matrizTabuleiro[i][j] != jogadorDaVez) break;
                aux++;
            }
            if(aux == 3) return true;
            else aux = 0;
        }
        aux = 0;
        //colunas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.matrizTabuleiro[j][i] != jogadorDaVez) break;
                aux++;
            }
            if(aux == 3) return true;
            else aux = 0;
        }
        //diagonais
        if      ((this.matrizTabuleiro[0][0] == jogadorDaVez) &&
                (this.matrizTabuleiro[1][1] == jogadorDaVez) &&
                (this.matrizTabuleiro[2][2] == jogadorDaVez))  return true;
        if      ((this.matrizTabuleiro[0][2] == jogadorDaVez) &&
                (this.matrizTabuleiro[1][1] == jogadorDaVez) &&
                (this.matrizTabuleiro[2][0] == jogadorDaVez)) return true;

        return false;
    }

    public void esvaziaTabuleiro(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.matrizTabuleiro[i][j] = 0;
            }
        }
    }
}
