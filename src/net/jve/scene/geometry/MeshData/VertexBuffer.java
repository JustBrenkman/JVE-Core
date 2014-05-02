package net.jve.scene.geometry.MeshData;

import java.nio.*;

/**
 * Created by ben on 02/05/14.
 */
public class VertexBuffer {

    public enum VertexType {
        /**
         * Half precision floating point.
         * 2 bytes, signed.
         */
        Half(2),

        /**
         * Single precision floating point.
         * 4 bytes, signed
         */
        Float(4),

        /**
         * Double precision floating point.
         * 8 bytes, signed. May not
         * be supported by all GPUs.
         */
        Double(8),

        /**
         * 1 byte integer, signed.
         */
        Byte(1),

        /**
         * 1 byte integer, unsigned.
         */
        UnsignedByte(1),

        /**
         * 2 byte integer, signed.
         */
        Short(2),

        /**
         * 2 byte integer, unsigned.
         */
        UnsignedShort(2),

        /**
         * 4 byte integer, signed.
         */
        Int(4),

        /**
         * 4 byte integer, unsigned.
         */
        UnsignedInt(4);


        private int componentSize = 0;

        VertexType(int compSize) {
            this.componentSize = compSize;
        }

        /**
         * Returns the size in bytes of this data type.
         *
         * @return Size in bytes of this data type.
         */
        public int getComponentSize(){
            return componentSize;
        }
    }

    private Buffer bufferData;

    public VertexBuffer() {

    }

    public Buffer getBuffer() {
        return bufferData;
    }

    /**
     * Returns a safe read-only version of this VertexBuffer's data.  The
     * contents of the buffer will reflect whatever changes are made on
     * other threads (eventually) but these should not be used in that way.
     * This method provides a read-only buffer that is safe to _read_ from
     * a separate thread since it has its own book-keeping state (position, limit, etc.)
     *
     * @return A rewound native buffer in the specified {@link VertexType format}
     *         that is safe to read from a separate thread from other readers.
     */
    public Buffer getReadOnlyData() {

        if (bufferData == null) {
            return null;
        }

        // Create a read-only duplicate().  Note: this does not copy
        // the underlying memory, it just creates a new read-only wrapper
        // with its own buffer position state.

        // Unfortunately, this is not 100% straight forward since Buffer
        // does not have an asReadOnlyBuffer() method.
        Buffer result;
        if( bufferData instanceof ByteBuffer ) {
            result = ((ByteBuffer)bufferData).asReadOnlyBuffer();
        } else if( bufferData instanceof FloatBuffer ) {
            result = ((FloatBuffer)bufferData).asReadOnlyBuffer();
        } else if( bufferData instanceof ShortBuffer ) {
            result = ((ShortBuffer)bufferData).asReadOnlyBuffer();
        } else if( bufferData instanceof IntBuffer ) {
            result = ((IntBuffer)bufferData).asReadOnlyBuffer();
        } else {
            throw new UnsupportedOperationException( "Cannot get read-only view of buffer type:" + bufferData );
        }

        // Make sure the caller gets a consistent view since we may
        // have grabbed this buffer while another thread was reading
        // the raw data.
        result.rewind();

        return result;
    }


    public void setBufferData(Buffer buffer) {
        if (buffer == null)
            new NullPointerException("Cannot have a null buffer!");

        this.bufferData = buffer;
    }
}
