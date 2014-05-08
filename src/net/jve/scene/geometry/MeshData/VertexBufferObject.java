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
