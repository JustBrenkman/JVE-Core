package net.jve.app;

import net.jve.math.Vertex3f;
import net.jve.render.OpenGLHelper;
import net.jve.scene.geometry.Mesh;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ben on 06/05/14.
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

        mesh.addVerticies(new Vertex3f(0, 0, 0), new Vertex3f(0, 100, 0), new Vertex3f(100, 0, 0));
        mesh.compile();
    }

    @Override
    public void render() {
//        glColor3f(0.5f,0.5f,1.0f);
        jve.getRenderEngine().renderMesh(mesh);
    }

    @Override
    public void update() {

    }
}
