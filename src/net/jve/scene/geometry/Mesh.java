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
