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

import net.jve.core.util.PathManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by ben on 09/05/14.
 */
public class Shader {

    public ShaderProgram shaderProgram;

    private ArrayList<String> vertexFileExtensions = new ArrayList<String>();
    private ArrayList<String> fragmentFileExtensions = new ArrayList<String>();
    private ArrayList<String> geometryFileExtensions = new ArrayList<String>();

    public Shader() {
        shaderProgram = new ShaderProgram();

        addFileExtensions();
    }

    /**
     * Loads single source
     * @param type type of shader
     * @param loc location / filename of shader
     */
    private void loadShader(ShaderType type, String loc) {

        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;
        final String INCLUDE_DIRECTIVE = "#include";

        try
        {
            shaderReader = new BufferedReader(new FileReader(PathManager.getLibraryPath() + "/shader/" + loc));
            String line;

            while((line = shaderReader.readLine()) != null)
            {
                if(line.startsWith(INCLUDE_DIRECTIVE))
                {
                    shaderSource.append(loadShader(line.substring(INCLUDE_DIRECTIVE.length() + 2, line.length() - 1)));
                }
                else
                    shaderSource.append(line).append("\n");
            }

            shaderReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        shaderProgram.addShaderSource(type, shaderSource.toString());
//        return shaderSource.toString();
    }

    /**
     * Loads single source
     * @param loc location / filename of shader
     */
    private String loadShader(String loc) {
        return null;
    }

    /**
     * Loads a pair of shaders
     * @param loc location / filename of shader
     */
    public void loadProgram(String loc) {

        if (PathManager.fileExists(loc + ".vs"))
            loadShader(ShaderType.VERTEX, loc + ".vs");

        if (PathManager.fileExists(loc + ".fs"))
            loadShader(ShaderType.FRAGMENT, loc + "fs");

        ///////////////////////// EXTRA CODE ///////////////////////////////////

//        String vertex;
//        String fraqext;
//
//        for (String vertexFileExtension : vertexFileExtensions) {
//            if (PathManager.fileExists(loc + vertexFileExtension)) {
//                vertex = vertexFileExtension;
//                break;
//            }
//        }
//
//        for (String fragmentFileExtension : fragmentFileExtensions) {
//            if (PathManager.fileExists(loc + fragmentFileExtension)) {
//                fraqext = fragmentFileExtension;
//                break;
//            }
//        }

        //////////////////////// EXTRA CODE /////////////////////////////////////
    }


    private void addFileExtensions() {
        vertexFileExtensions.add("vs");
        vertexFileExtensions.add("vert");

        fragmentFileExtensions.add("fs");
        fragmentFileExtensions.add("frag");

        geometryFileExtensions.add("gs");
        geometryFileExtensions.add("geom");
    }

    public void compileShader() {
        shaderProgram.compileShaders();
    }

    public void bind() {

    }

    public void unbind() {

    }
}
