package net.jve.app;

import net.jve.core.JVE;
import net.jve.render.RenderEngine;

/**
 * Created by ben on 06/05/14.
 */
public abstract class Application {

    JVE jve;

    private Application app;

    public void start(RenderEngine renderEngine, Application application) {
        jve = new JVE(renderEngine, this);


        jve.createDisplay();

        this.initialize();

        jve.mainLoop();
    }

    public void start() {
        start(new RenderEngine(), this);
    }

    public abstract void initialize();

    public abstract void render();

    public abstract void update();
}
