package net.jve.scene.geometry.MeshData;

import net.jve.math.Vertex3f;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.LinkedList;

/**
 * Created by ben on 02/05/14.
 */
public class MeshBuilder {

    private LinkedList<Vertex3f> verticies = new LinkedList<Vertex3f>();

    public MeshBuilder() {

    }

    public void addVertex(Vertex3f vertex) {
        verticies.add(vertex);
    }

    public void addVertex(float x, float y, float z) {
        this.addVertex(new Vertex3f(x, y, z));
    }

    /**
     * Complies list of verticies to a FloatBuffer
     * @return
     */
    public FloatBuffer compile() {
        FloatBuffer buffer = BufferUtil.createVector3Buffer(verticies.size() * 3);

        for (Vertex3f v : verticies) {
            buffer.put(new float[] {v.getX(), v.getY(), v.getZ()});
        }
        return buffer;
    }

    public void clearAll() {
        verticies.clear();
    }
}
