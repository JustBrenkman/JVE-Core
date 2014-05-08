package net.jve.scene.geometry;

import net.jve.math.Vertex3f;
import net.jve.scene.geometry.MeshData.VertexBufferObject;

/**
 * Created by ben on 02/05/14.
 */
public class Mesh {

    private VertexBufferObject vertexBufferObject = null;

    public Mesh() {
        vertexBufferObject = new VertexBufferObject();
    }

    public int getVertexCount() {
        if (vertexBufferObject == null)
            return 0;

//        return vertexBufferObject.getBuffer().capacity() / 3;

        return vertexBufferObject.verticies.size();
    }

    /**
     * VertexBufferObject Wrappers
     *
     * adds support for all the VertexBufferObject commands
     */
    // wrappers for VertexBufferObject
    public void addVerticies(Vertex3f... vertex3fs) {
        vertexBufferObject.setVerticies(vertex3fs);
    }

    public void addVertex(Vertex3f vertex) {
        vertexBufferObject.addVertex3f(vertex);
    }

    public void addVertex(int index, Vertex3f vertex) {
        vertexBufferObject.addVertexAt(vertex, index);
    }

    /**
     * Adds a vertex at a specific stop
     * @param vertex
     * @param index
     */
    public void addVertexAt(Vertex3f vertex, int index) {
        vertexBufferObject.addVertexAt(vertex, index);
    }

    /**
     * Adds vertex in first stop and shifts everything down
     * @param vertex
     */
    public void addVertexFirst(Vertex3f vertex) {
        vertexBufferObject.addVertexFirst(vertex);
    }

    /**
     * Adds a vertex to the bottom
     * @param vertex
     * @return
     */
    public int addVertexLast(Vertex3f vertex) {
        return vertexBufferObject.addVertexLast(vertex);
    }

    /**
     * Removes a vertex
     * @param vertex
     */
    public void removeVertex(Vertex3f vertex) {
        vertexBufferObject.removeVertex(vertex);
    }

    /**
     * removes a vertex from certian position
     * @param index
     */
    public void removeVertex(int index) {
        vertexBufferObject.removeVertex(index);
    }

    /**
     * Creates FloatBuffer and sends to GPU
     */
    public void compile() {
        vertexBufferObject.compile();
    }

    /**
     * Updates buffer on GPU
     */
    public void updateBuffer() {
        vertexBufferObject.updateBuffer();
    }

    public float[] getVerticies() {
        return vertexBufferObject.getVerticies();
    }
    // End of wrapper
    /**
     * End of VertexBufferObject wrapper
     */

    public int getVboID() {
        return vertexBufferObject.getVboID();
    }

    public void printOut() {
        vertexBufferObject.printOut();
    }

    public void destory() {
        vertexBufferObject.destroy();
    }
}
