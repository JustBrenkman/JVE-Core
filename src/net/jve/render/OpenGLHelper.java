package net.jve.render;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

/**
 * Created by ben on 07/05/14.
 */
public class OpenGLHelper {

    public static String getOpenGLVersion() {
        return glGetString(GL_VERSION);
    }
}
