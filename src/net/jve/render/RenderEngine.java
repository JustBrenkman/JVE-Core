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

package net.jve.render;

import net.jve.scene.geometry.Mesh;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;

/**
 * Created by ben on 06/05/14.
 */
public class RenderEngine {

    Logger logger = LoggerFactory.getLogger(RenderEngine.class);

    public void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

//        glEnable(GL_DEPTH_CLAMP);

        glEnable(GL_TEXTURE_2D);

        glEnable(GL_ARRAY_BUFFER_BINDING);

//        glEnable(GL_FRAMEBUFFER_SRGB);


        ////////////////////////////////////

//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();
//        GL11.glOrtho(0, 800, 0, 600, 1, -1);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        //////////////////////////////////////////
    }

    public void renderMesh(Mesh mesh) {
        if (mesh.getVboID() == -1) {
            logger.error("Vertex Buffer Object does'nt exist on GPU");
            return;
        }

//        glEnableVertexAttribArray(0);

        glEnableClientState(GL_VERTEX_ARRAY);
        glBindBuffer(GL_ARRAY_BUFFER, mesh.getVboID());
        glVertexPointer(3, GL_FLOAT, 0, 0L);
//        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * 4, 0);

        glDrawArrays(GL_TRIANGLES, 0, mesh.getVertexCount());

        glDisableClientState(GL_VERTEX_ARRAY);

        glDisableVertexAttribArray(0);


        /////////////////////////////// SHADER CODE ////////////////////////

//        glEnableVertexAttribArray(0);
//
//        glBindBuffer(GL_ARRAY_BUFFER, mesh.getVboID());
//
//        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * 4, 0);
//        glDrawArrays(GL_TRIANGLES, 0, mesh.getVertexCount());
//
//        glDisableVertexAttribArray(0);

        ////////////////////////////// SHADER CODE /////////////////////////
    }

    public void clearBuffers() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
