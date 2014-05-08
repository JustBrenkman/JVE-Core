package net.jve.core;

import net.jve.app.Application;
import net.jve.core.lwjgl.LWJGLDisplay;
import net.jve.render.RenderEngine;
import org.lwjgl.opengl.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ben on 06/05/14.
 */
public class JVE {

    public Logger logger = LoggerFactory.getLogger(JVE.class);

    private RenderEngine renderEngine;
    private LWJGLDisplay display;

    private Application app;

    public JVE(RenderEngine renderEngine, Application application) {
        this.setRenderEngine(renderEngine);
        this.app = application;
    }

    public void createDisplay() {
        if (display == null)
            display = new LWJGLDisplay();

        if (display.isCreated()) {
            logger.error("Display already created");
            return;
        }

        display.createDisplay();

        initGL();
    }

    public void mainLoop() {
        while(!Display.isCloseRequested()) {

            app.update();

            renderEngine.clearBuffers();

            app.render();

            Display.update();
        }
        display.destroy();
    }

    public RenderEngine getRenderEngine() {
        return renderEngine;
    }

    public void setRenderEngine(RenderEngine renderEngine) {
        this.renderEngine = renderEngine;
    }

    public void initGL() {
        if (renderEngine == null) {
            logger.warn("Opengl cannot be initialized");
            return;
        }

        renderEngine.initGL();
    }
}
