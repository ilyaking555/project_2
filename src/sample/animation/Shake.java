package sample.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;
    //создаем конструктор Ноуд, то что мы хотим трести
    public Shake(Node node){
        //выделяем память и длительность анимации
        tt= new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(0f);// отсуп никакой и вернуться в прежнию точку
        tt.setByX(10f);//перемещение от одной позиции в другую
        tt.setCycleCount(3);// как много раз проиграет эта анимация
        tt.setAutoReverse(true);//поле возращалось обратно
    }
    //конструктор проигрывает анимацию
    public void playAnimation(){
        tt.playFromStart();
    }
}
