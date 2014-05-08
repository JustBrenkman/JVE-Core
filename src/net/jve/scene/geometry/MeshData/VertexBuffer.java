/*
 * Copyright (c) 2014
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those
 *  of the authors and should not be interpreted as representing official policies,
 *  either expressed or implied, of the FreeBSD Project.
 */

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
