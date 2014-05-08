package net.jve.scene.geometry.MeshData;

import java.nio.FloatBuffer;

/**
 * Created by ben on 06/05/14.
 */
public interface VertexData {
    /**
     * Gets the number of verticies
     * @return
     */
    public int getNumberOfVerticies();

    /**
     * Sets the verticies
     * @param verticies
     */
    public void setVerticies(float[] verticies);

    /**
     * Returns the buffer
     * @return
     */
    public FloatBuffer getBuffer();

    public void setBuffer(FloatBuffer floatBuffer);
}
