package br.edu.ifsc.sj.poo29004.desenhocanvas;

public class JogoDaVelha {

    //Posições da matriz
    // 0 - ninguém jogou
    // -1 - PC jogou
    // 1 - pessoa jogou

    private Integer[][] matrizTabuleiro;
    private int jogadorDaVez;
    private int jogadorDaPartida;

    public JogoDaVelha() {
        this.jogadorDaVez = -1;
        this.jogadorDaPartida = 1;
        this.matrizTabuleiro = new Integer[3][3];
        this.esvaziaTabuleiro();
    }

    public Integer getJogadorDaVez() {
        return jogadorDaVez;
    }

    public int getJogadorDaPartida() { return jogadorDaPartida; }

    public Integer[][] getMatrizTabuleiro() {
        return matrizTabuleiro;
    }

    public void setMatrizTabuleiro(Integer[][] matrizTabuleiro) {
        this.matrizTabuleiro = matrizTabuleiro;
    }

    //String posiçãoMatriz no formato formato: ij (i: linha , j: coluna)
    public boolean jogar(String posiçãoMatriz){

//        if(jogadorDaVez == 0); //jogadorDaVez = jogadorDaPartida;
//        else jogadorDaVez = jogadorDaVez*-1;
        jogadorDaVez = jogadorDaVez*-1;

        //System.out.println("JOGADOR DA VEZ: " + jogadorDaVez);

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

    public boolean tabuleiroVazio(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(matrizTabuleiro[i][j] != 0) return false;
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
            if(aux == 3){
                //jogadorDaPartida = jogadorDaPartida*-1;
                //jogadorDaVez = 0;
                return true;
            }
            else aux = 0;
        }
        aux = 0;
        //colunas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.matrizTabuleiro[j][i] != jogadorDaVez) break;
                aux++;
            }
            if(aux == 3){
                //jogadorDaPartida = jogadorDaPartida*-1;
                //jogadorDaVez = 0;
                return true;
            }
            else aux = 0;
        }
        //diagonais
        if      ((this.matrizTabuleiro[0][0] == jogadorDaVez) &&
                (this.matrizTabuleiro[1][1] == jogadorDaVez) &&
                (this.matrizTabuleiro[2][2] == jogadorDaVez)){

                    //jogadorDaPartida = jogadorDaPartida*-1;
                    //jogadorDaVez = 0;
                    return true;
        }
        if      ((this.matrizTabuleiro[0][2] == jogadorDaVez) &&
                (this.matrizTabuleiro[1][1] == jogadorDaVez) &&
                (this.matrizTabuleiro[2][0] == jogadorDaVez)){

                    //jogadorDaPartida = jogadorDaPartida*-1;
                    //jogadorDaVez = 0;
                    return true;
        }

        return false;
    }

    public void esvaziaTabuleiro(){
        //Setando para que na próxima jogada
        jogadorDaVez = -1;

//        if(jogadorDaPartida == 1){
//            jogadorDaPartida = -1;
//            jogadorDaVez = 1;
//        }
//        else{
//            jogadorDaPartida = 1;
//            jogadorDaVez = -1;
//        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.matrizTabuleiro[i][j] = 0;
            }
        }
    }

    /**
     * Esse método é responsável por realizar as jogadas da Inteligencia (PC)
     */
    public String inteligencia(){

        //Posição que a inteligencia vai fazer a jogada
        String pos;

        //Jogar no meio se tabuleiro estiver vazio
        if(tabuleiroVazio()){
            //System.out.println("=======================TABULEIRO VAZIO");

            //jogar no meio do tabuleiro
            pos = "11";
            this.jogar(pos);
            return pos;
        }

        //Realizar jogada da vitória se existir
        pos = jogarInteligencia(-1);
        if(pos.length() > 0) {
            //System.out.println("=======================JOGADA DA VITORIA");
            return pos;
        }

        //Bloquear jogada da vitória adversária se existir
        pos = jogarInteligencia(1);
        if(pos.length() > 0) {
            //System.out.println("=======================JOGADA BLOQUEADA");
            return pos;
        }

        //Realizar jogada estratégica
        pos = jogadaEstrategica();
        jogar(pos);
        //System.out.println("=======================JOGADA ESTRATÉGICA");
        return pos;
    }

    /**
     * @return
     */
    private String jogadaEstrategica(){

        String pos = "";

//        //Se Jogador usuário tem uma jogada no meio
//        if(matrizTabuleiro[1][1] != 0){
//            if(Math.abs(matrizTabuleiro[0][0] - matrizTabuleiro[2][2]) == 2){
//                jogar("02");
//            }
//        }
//

        //jogar no meio do tabuleiro
        if(matrizTabuleiro[1][1] == 0){
            pos = "11";
        }
        //jogar em um dos cantos
        else if(matrizTabuleiro[0][0] == 0){
            pos = "00";
        }
        else if (matrizTabuleiro[2][2] == 0){
            pos = "22";
        }
        else if (matrizTabuleiro[0][2] == 0){
            pos = "02";
        }
        else if (matrizTabuleiro[2][0] == 0){
            pos = "20";
        }else{
            //procurar uma posição vazia
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(matrizTabuleiro[i][j] == 0){
                        return i + "" + j;
                    }
                }
            }
        }

        return pos;
    }

    /**
     * Esse método verifica se falta somente uma jogada para Jogador (usuário) ou Inteligencia vencer
     * @param jogador: Jogador usuário 1 ; Inteligencia -1
     * @return posição onde a Inteligencia deve fazer sua jogada (se não retorna String vazia)
     */
    private String jogarInteligencia(int jogador){
        //verificando linhas
        for (int i = 0; i < 3; i++) {
            if(somatoriaLinha(i) == jogador*2){
                //procurar posição que está vazia para jogar nela
                String pos = posiçãoVaziaLinha(i);
                jogar(pos);
                return pos;
            }
        }

        //verificando colunas
        for (int j = 0; j < 3; j++) {
            if(somatoriaColuna(j) == jogador*2){
                //procurar posição que está vazia para jogar nela
                String pos = posiçãoVaziaColuna(j);
                jogar(pos);
                return pos;
            }
        }

        //verificando diagonal principal
        for (int j = 0; j < 3; j++) {
            if(somatoriaDiagonalP() == jogador*2){
                //procurar posição que está vazia para jogar nela
                String pos = posiçãoVaziaDiagonalP();
                jogar(pos);
                return pos;
            }
        }

        //verificando diagonal secundária
        for (int j = 0; j < 3; j++) {
            if(somatoriaDiagonalS() == jogador*2){
                //procurar posição que está vazia para jogar nela
                String pos = posiçãoVaziaDiagonalS();
                jogar(pos);
                return pos;
            }
        }
        return "";
    }

    private int somatoriaLinha(int i){
        int somatoria = 0;
        for (int j = 0; j < 3; j++) {
            somatoria = somatoria + matrizTabuleiro[i][j];
        }
        return somatoria;
    }

    private String posiçãoVaziaLinha(int i){
        String posição = "";
        for (int j = 0; j < 3; j++) {
            if(matrizTabuleiro[i][j] == 0){
                posição = i + "" + j;
                break;
            }
        }
        return posição;
    }

    private int somatoriaColuna(int j){
        int somatoria = 0;
        for (int i = 0; i < 3; i++) {
            somatoria = somatoria + matrizTabuleiro[i][j];
        }
        return somatoria;
    }

    private String posiçãoVaziaColuna(int j){
        String posição = "";
        for (int i = 0; i < 3; i++) {
            if(matrizTabuleiro[i][j] == 0){
                posição = i + "" + j;
                break;
            }
        }
        return posição;
    }

    private int somatoriaDiagonalP(){
        int somatoria = 0;
        for (int j = 0, i = 0; j < 3; j++, i++) {
            somatoria = somatoria + matrizTabuleiro[i][j];
        }
        return somatoria;
    }

    private String posiçãoVaziaDiagonalP(){
        String posição = "";
        for (int j = 0, i = 0; j < 3; j++, i++) {
            if(matrizTabuleiro[i][j] == 0){
                posição = i + "" + j;
                break;
            }
        }
        return posição;
    }

    private int somatoriaDiagonalS(){
        int somatoria = 0;
        for (int i = 0, j = 2; i < 3; i++, j--) {
            somatoria = somatoria + matrizTabuleiro[i][j];
        }
        return somatoria;
    }

    private String posiçãoVaziaDiagonalS(){
        String posição = "";
        for (int i = 0, j = 2; i < 3; i++, j--) {
            if(matrizTabuleiro[i][j] == 0){
                posição = i + "" + j;
                break;
            }
        }
        return posição;
    }

}
