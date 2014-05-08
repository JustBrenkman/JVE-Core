package net.jve.scene.geometry.MeshData;

import net.jve.math.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Created by ben on 02/05/14.
 */
public class BufferUtil {

    /**
     * Create a new FloatBuffer of an appropriate size to hold the specified
     * number of Vector3f object data.
     *
     * @param vertices
     *            number of vertices that need to be held by the newly created
     *            buffer
     * @return the requested new FloatBuffer
     */
    public static FloatBuffer createVector3Buffer(int vertices) {
        FloatBuffer vBuff = createFloatBuffer(3 * vertices);
        return vBuff;
    }

    /**
     * Create a new FloatBuffer of an appropriate size to hold the specified
     * number of Vector3f object data only if the given buffer if not already
     * the right size.
     *
     * @param buf
     *            the buffer to first check and rewind
     * @param vertices
     *            number of vertices that need to be held by the newly created
     *            buffer
     * @return the requested new FloatBuffer
     */
    public static FloatBuffer createVector3Buffer(FloatBuffer buf, int vertices) {
        if (buf != null && buf.limit() == 3 * vertices) {
            buf.rewind();
            return buf;
        }

        return createFloatBuffer(3 * vertices);
    }

    /**
     * Generate a new FloatBuffer using the given array of float primitives.
     * @param data array of float primitives to place into a new FloatBuffer
     */
    public static FloatBuffer createFloatBuffer(float... data) {

        FloatBuffer buff = BufferUtils.createFloatBuffer(data.length * 3);
        buff.clear();
        for (int i = 0; i < data.length; i++) {
            buff.put(data[i]);
            System.out.println("Buff: " + buff.get(i));
        }
        buff.flip();
        return buff;
    }

    public static FloatBuffer createFloatBuffer(int size)
    {
        return BufferUtils.createFloatBuffer(size);
    }

    public static FloatBuffer createFlippedBuffer(Vector3f[] vertices) {
        FloatBuffer buffer = createFloatBuffer(vertices.length * 3);

        for (int i = 0; i < vertices.length; i++) {
            buffer.put(vertices[i].getX());
            buffer.put(vertices[i].getY());
            buffer.put(vertices[i].getZ());
        }

        buffer.flip();

        return buffer;
    }
}
