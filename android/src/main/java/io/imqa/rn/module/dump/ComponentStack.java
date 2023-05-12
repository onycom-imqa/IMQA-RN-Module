package io.imqa.rn.module.dump;

public class ComponentStack {
    private static ComponentStack componentStack = new ComponentStack();

    private String currentComponent = "Anonymous Component";

    public static ComponentStack getInstance() {
        if (componentStack == null)
            componentStack = new ComponentStack();
        return componentStack;
    }

    public String getCurrentComponent() {
        return currentComponent;
    }

    public void setCurrentComponent(String currentComponent) {
        this.currentComponent = currentComponent;
    }
}