package net.jve.scene.geometry;

import net.jve.scene.geometry.MeshData.VertexBuffer;

/**
 * Created by ben on 02/05/14.
 */
public class Mesh {

    public VertexBuffer vertexBuffer = null;

    public Mesh() {

    }

    public int getVertexCount() {
        if (vertexBuffer == null)
            return 0;

        return vertexBuffer.getBuffer().capacity() / 3;
    }
}
