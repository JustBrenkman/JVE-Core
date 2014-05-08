package net.jve.scene.geometry.MeshData;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;

/**
 * Created by ben on 06/05/14.
 */
public class VertexBufferObject extends VertexArray {

    /**
     * Float buffer, buffer of all the information
     */
    public FloatBuffer buffer;

    /**
     * OpenGL buffer int for rendering
     */
    private int bufferHandler;
//    private int indecyHandler;

    public VertexBufferObject() {
        bufferHandler = glGenBuffers();
//        indecyHandler = glGenBuffers();
    }

    public FloatBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(FloatBuffer floatBuffer) {
        buffer = floatBuffer;
    }

    /**
     * Creates float buffer and sends to the GPU
     * @return
     */
    public FloatBuffer compile() {
        //Creates a buffer
        setBuffer(BufferUtil.createFloatBuffer(getVerticies()));

        updateBuffer();

        return getBuffer();
    }

    /**
     * Updates float buffer on the gpu
     */
    public void updateBuffer() {
//        if (getBuffer() == null)
//            return;

        glBindBuffer(GL_ARRAY_BUFFER, bufferHandler);
        glBufferData(GL_ARRAY_BUFFER, getBuffer(), GL_STATIC_DRAW);
    }

    /**
     * Gets Buffer Handler
     * @return
     */
    public int getVboID() {
        return bufferHandler;
    }

    /**
     * Caution will destroy all data for garbage collection
     */
    public void destroy() {
        glDeleteBuffers(bufferHandler);
//        glDeleteBuffers(indecyHandler);
    }
}
