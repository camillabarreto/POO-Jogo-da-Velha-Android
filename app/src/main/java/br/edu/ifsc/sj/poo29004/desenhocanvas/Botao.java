package br.edu.ifsc.sj.poo29004.desenhocanvas;

import android.content.Context;


/**
 * Classe para representar um push button.
 */
public class Botao extends Componente {


    // true para indicar que o botão está pressionado
    private boolean pressionado;

    public Botao(Context context, int x, int y) {
        super(context, R.drawable.bup, x, y);
        pressionado = false;
    }

    /**
     * Troca a imagem do botão de acordo com seu estado
     */
    public void pressionar(){
        if (pressionado){
            this.setBitmap(R.drawable.bup);
        }else {
            this.setBitmap(R.drawable.bdown);
        }
        pressionado = ! pressionado;
    }

    /**
     * Um exemplo de como verificar se o toque do usuário na tela foi dentro da área onde o
     * botão está desenhado. Esse exemplo funciona bem se o botão for um retângulo. Se a forma do botão
     * for uma elipse, talvez então seria necessário pensar em outra solução.
     *
     * @param px - coordenada X do toque do usuário na tela
     * @param py - coordenada Y do toque do usuário na tela
     * @return retorna true se estiver dentro da área do botão
     */
    public boolean clicouNoBotao(float px, float py){

        // obtendo largura e altura da imagem do botão
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        return ((px > this.x && px < (this.x + width))
                && (py > this.y && py < (this.y + height))) ? true : false;

    }

}
