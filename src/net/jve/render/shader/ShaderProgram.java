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

package net.jve.render.shader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by ben on 08/05/14.
 */
public final class ShaderProgram {

    public Logger logger = LoggerFactory.getLogger(ShaderProgram.class);
    /**
     * Maps loaded shaders
     */
    private static LinkedList<ShaderResource> shadersList;

    /**
     * Maps uniforms to name
     */
    public HashMap<String, Uniform> uniforms = new HashMap<String, Uniform>();

    /**
     * Maps attributes
     */
    public HashMap<String, Uniform> attributes = new HashMap<String, Uniform>();

    /**
     * Shader resource, each shaderprogram contains one or multiply sources,
     * one for each type of Shader {@link ShaderType}
     *
     * VERTEX - vertex shader
     *
     * FRAGMENT - fragment shader
     *
     * GEOMETRY - geometry shader
     */
    public class ShaderResource {

        ShaderType type;
        String language;
        String name;
        String source;
        String defines;
        int id;

        public ShaderResource(ShaderType type) {
            super();
            this.type = type;

            this.id = glCreateProgram();

            if (id == -1) {
                logger.error("Shader creation failed: Could not find valid memory location in VRAM");
                System.exit(1);
            }

            if (type == null) {
                throw new IllegalArgumentException("ShaderType cannot be null");
            }
        }

        public ShaderResource() {
            super();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setType(ShaderType type) {
            this.type = type;
        }

        public ShaderType getType() { return this.type;}

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getLanguage() {
            return language;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setSource(String source) {
            if (source == null)
                throw new IllegalArgumentException("Shader source cannot be null");

            this.source = source;
        }

        public void setDefines(String defines) {
            if (defines == null)
                throw new IllegalArgumentException("Defines cannot be null");

            this.defines = defines;
        }

        public String getSource() {
            return source;
        }

        public String getDefines() {
            return defines;
        }
    }

    public ShaderProgram() {
        shadersList = new LinkedList<ShaderResource>();
        uniforms = new HashMap<String, Uniform>();
    }

    private void addShaderSource(ShaderType type, String name, String source, String defines, String language) {
        ShaderResource resource = new ShaderResource(type);

        resource.setSource(source);
        resource.setName(name);
        resource.setLanguage(language);

        if (defines != null)
            resource.setDefines(defines);

        shadersList.add(resource);
    }

    public void addShaderSource(ShaderType type, String source) {
        ShaderResource resource = new ShaderResource(type);

        resource.setSource(source);
//        resource.setName(name);
//        resource.setLanguage(language);

//        if (defines != null)
//            resource.setDefines(defines);

        shadersList.add(resource);
    }

    public void compileShader(ShaderResource shader) {

        if (shader.getId() == -1) {
            logger.error("Unable to bind shader, dosn't exist");
        }

        glLinkProgram(shader.getId());

        if(glGetProgrami(shader.getId(), GL_LINK_STATUS) == 0) {
            logger.error(glGetProgramInfoLog(shader.getId(), 1024));
            System.exit(1);
        }

        glValidateProgram(shader.getId());

        if (glGetProgrami(shader.getId(), GL_VALIDATE_STATUS) == 0) {
            logger.error(glGetProgramInfoLog(shader.getId(), 1024));
            System.exit(1);
        }
    }

    public void compileShaders() {
        // TODO cut down on case switches, too many on compileShader(ShaderType type) and compileShaders()
        for (ShaderResource shaderResource : shadersList) {
            switch (shaderResource.getType()) {
                case VERTEX:
                    compileShader(shaderResource);
                    break;

                case FRAGMENT:
                    compileShader(shaderResource);
                    break;

                case GEOMETRY:
                    compileShader(shaderResource);
                    break;

                default:
                    break;
            }
        }
    }

    public void bindProgram() {

    }

    private void bindShader(ShaderType type) {

    }

    public int makeID() {
        return glCreateProgram();
    }
}
