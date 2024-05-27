package chatroom;

import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EDTInvocationHandler implements InvocationHandler {

    private Object invocationResult = null;
    private final UITasks ui;

    public EDTInvocationHandler (UITasks ui) {
        this.ui = ui;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (SwingUtilities.isEventDispatchThread()) {
            invocationResult = method.invoke(ui, args);
        } else {
            Runnable shell = () -> {
                try {
                    invocationResult = method.invoke(ui, args);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            };
            SwingUtilities.invokeAndWait(shell);
        }
        return invocationResult;
    }
}