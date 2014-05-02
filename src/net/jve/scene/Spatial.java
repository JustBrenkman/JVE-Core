package net.jve.scene;

import net.jve.math.Transform;

/**
 * Created by ben on 01/05/14.
 */
public class Spatial {

    // Transforms
    private Transform localTransform;
    private Transform globalTramsform;

    // Parent Node for reference
    private Node parentNode;

    // Scene Name, for reference
    private String sceneName;

    public Spatial() {
        localTransform = new Transform();
        globalTramsform = new Transform();
    }

    public Spatial(String name) {
        this();
        this.sceneName = name;
    }

    public Transform getLocalTransform() {
        return localTransform;
    }

    public void setLocalTransform(Transform localTransform) {
        this.localTransform = localTransform;
    }

    public Transform getGlobalTramsform() {
        return globalTramsform;
    }

    public void setGlobalTramsform(Transform globalTramsform) {
        this.globalTramsform = globalTramsform;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public String getName() {
        return sceneName;
    }

    public void setName(String sceneName) {
        this.sceneName = sceneName;
    }
}
