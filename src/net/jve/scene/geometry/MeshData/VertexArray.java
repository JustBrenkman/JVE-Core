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

import net.jve.math.Vertex3f;

import java.util.LinkedList;

/**
 * Created by ben on 06/05/14.
 */
public class VertexArray {

    public LinkedList<Vertex3f> verticies = new LinkedList<Vertex3f>();

    /**
     * Adds vertex to array
     * @param vertex
     * @return
     */
    public int addVertex3f(Vertex3f vertex) {
        verticies.add(vertex);

        return verticies.size();
    }

    public void setVerticies(Vertex3f...vertex3fs) {
        for (int i = 0; i <vertex3fs.length; i++) {
            addVertex3f(vertex3fs[i]);
        }
    }

    /**
     * Adds a vertex at a specific stop
     * @param vertex
     * @param index
     */
    public void addVertexAt(Vertex3f vertex, int index) {
        verticies.add(index, vertex);
    }

    /**
     * Adds vertex in first stop and shifts everything down
     * @param vertex
     */
    public void addVertexFirst(Vertex3f vertex) {
        verticies.addFirst(vertex);
    }

    /**
     * Adds a vertex to the bottom
     * @param vertex
     * @return
     */
    public int addVertexLast(Vertex3f vertex) {
        verticies.addLast(vertex);

        return verticies.size();
    }

    /**
     * Removes a vertex
     * @param vertex
     */
    public void removeVertex(Vertex3f vertex) {
        verticies.remove(vertex);
    }

    /**
     * removes a vertex from certian position
     * @param index
     */
    public void removeVertex(int index) {
        verticies.remove(index);
    }

    /**
     * Returns a float array of all the verticies
     * @return
     */
    public float[] getVerticies() {
        float[] vert = new float[verticies.size() * 3];

        for (int i = 0; i < vert.length; i += 3) {
            vert[i] = verticies.get(i / 3).getX();
            vert[i + 1] = verticies.get(i / 3).getY();
            vert[i + 2] = verticies.get(i / 3).getZ();

        }

        return vert;
    }

    public void printOut() {
        for (Vertex3f verticy : verticies) {
            System.out.println("Vertex: " + verticy.getX() + ", " + verticy.getY() + ", " + verticy.getZ());
        }
    }
}
