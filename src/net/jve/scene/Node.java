package net.jve.scene;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

/**
 * Created by ben on 01/05/14.
 */
public class Node extends Spatial {

    Logger logger = LoggerFactory.getLogger(Node.class);

    // List of all children connected to node
    private LinkedList<Spatial> children = new LinkedList<Spatial>();

    public Node() {

    }

    public Node(String name) {
        super(name);
    }

    /**
     * Gets the number of children
     * @return
     */
    public int getAmountOfChildren() {return children.size();}


    /**
     * Returns the amount of children after addition
     * @param child
     * @return
     * @throws IllegalArgumentException
     */
    public int attachChild(Spatial child) throws IllegalArgumentException {
        if (child == null)
            throw new IllegalArgumentException("Child cannot be null");

        child.setParentNode(this);
        children.add(child);

        logger.info("Attached child at {0}", new Object[] {child.getName()});

        return children.size();
    }

    /**
     * Detaches child
     * @param child
     * @return
     */
    public int detachChild(Spatial child) {
        if (child == null)
            throw new NullPointerException("Cannot remove a child that doesn't exist");

        if (child.getParentNode() == this) {
            int index = children.indexOf(child);
            if (index != -1) {
                detachChildAt(index);
            }
            return index;
        }
        return -1;
    }

    /**
     * removes specific child from index
     * @param index
     * @return
     */
    public Spatial detachChildAt(int index) {
        Spatial child = children.remove(index);

        logger.info("Child({0}) removed", new Object[] {child.getName()});

        if (child == null) {
            child.setParentNode(null);
        }
        return child;
    }


    /**
     * Removes all the children
     */
    public void detachAllChildren() {
        for (int i = 0; i < children.size(); i++) {
            detachChildAt(i);
        }

        logger.info("All children were removed");
    }

    /**
     * Returns the index of a Child
     * @param child
     * @return
     */
    public int getIndexOfChild(Spatial child) {
        return children.indexOf(child);
    }


    /**
     * Gets child at index
     * @param index
     * @return
     */
    public Spatial getChild(int index) {
        if (index > children.size() || index < children.size())
            return null;

        return children.get(index);
    }

    /**
     *
     * Determines if spatial has a specific child or not
     *
     * @param spat
     * @return
     */
    public boolean hasChild(Spatial spat) {
        if (children.contains(spat))
            return true;

        for (Spatial child : children) {
            if (child instanceof Node && ((Node)child).hasChild(spat))
                return true;
        }

        return false;
    }

}
