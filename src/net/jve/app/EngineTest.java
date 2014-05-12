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

package net.jve.app;

import net.jve.math.Vertex3f;
import net.jve.render.OpenGLHelper;
import net.jve.render.shader.Shader;
import net.jve.scene.geometry.Mesh;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.lwjgl.opengl.GL11.glColor3f;

/**
 * Created by ben on 06/05/14.
 *
 *
 */
public class EngineTest extends Application {

    public Mesh mesh;
    public static Logger logger;

    public EngineTest() {}

    public static void main(String[] args) {
        EngineTest test = new EngineTest();
        logger = LoggerFactory.getLogger(EngineTest.class);
        test.start();
    }

    @Override
    public void initialize() {

        logger.info(OpenGLHelper.getOpenGLVersion());

        mesh = new Mesh();

        mesh.addVerticies(new Vertex3f(0, 0, 0), new Vertex3f(0, 1, 0), new Vertex3f(1, 0, 0), new Vertex3f(0, 0, 0), new Vertex3f(0, -1, 0), new Vertex3f(-1, 0, 0));
        mesh.compile();

        Shader shader = new Shader();

        shader.loadProgram("basic");

        shader.compileShader();
    }

    @Override
    public void render() {
        glColor3f(0.5f,0.5f,1.0f);
        jve.getRenderEngine().renderMesh(mesh);
    }

    @Override
    public void update() {

    }
}
