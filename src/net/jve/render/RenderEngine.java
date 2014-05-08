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
