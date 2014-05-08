package net.jve.core.lwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ben on 06/05/14.
 */
public class LWJGLDisplay {

    // Logger
    public Logger logger = LoggerFactory.getLogger(LWJGLDisplay.class);

    private boolean isCreated = false;

    public LWJGLDisplay() {

    }

    public void createDisplay() {
        logger.debug("Display being created.");

        if (setDisplayMode(800, 600, false)) {
            try {
                Display.create();
                setCreated(true);
            } catch (LWJGLException e) {
                logger.error("Display could not be created!");
            }
        }
    }

    /**
     * Set the display mode to be used
     *
     * @param width
     *            The width of the display required
     * @param height
     *            The height of the display required
     * @param fullscreen
     *            True if we want fullscreen mode
     */
    private boolean setDisplayMode(int width, int height, boolean fullscreen) {

        // return if requested DisplayMode is already set
        if ((Display.getDisplayMode().getWidth() == width)
                && (Display.getDisplayMode().getHeight() == height)
                && (Display.isFullscreen() == fullscreen)) {
            return false;
        }

        try {
            DisplayMode targetDisplayMode = null;

            if (fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for (int i = 0; i < modes.length; i++) {
                    DisplayMode current = modes[i];

                    if ((current.getWidth() == width)
                            && (current.getHeight() == height)) {
                        if ((targetDisplayMode == null)
                                || (current.getFrequency() >= freq)) {
                            if ((targetDisplayMode == null)
                                    || (current.getBitsPerPixel() > targetDisplayMode
                                    .getBitsPerPixel())) {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }

                        // if we've found a match for bpp and frequence against
                        // the
                        // original display mode then it's probably best to go
                        // for this one
                        // since it's most likely compatible with the monitor
                        if ((current.getBitsPerPixel() == Display
                                .getDesktopDisplayMode().getBitsPerPixel())
                                && (current.getFrequency() == Display
                                .getDesktopDisplayMode().getFrequency())) {
                            targetDisplayMode = current;
                            break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width, height);
            }

            if (targetDisplayMode == null) {
                System.out.println("Failed to find value mode: " + width + "x"
                        + height + " fs=" + fullscreen);
                return false;
            }

            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullscreen);

            return true;
        } catch (LWJGLException e) {
            System.out.println("Unable to setup mode " + width + "x" + height
                    + " fullscreen=" + fullscreen + e);
        }
        return true;
    }

    /**
     * Sets the Display's title
     * @param title display title
     */
    public void setTitle(String title) {
        if (isCreated()) {
            Display.setTitle(title);
            logger.info("Display title set to: " + title);
        }
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean isCreated) {
        this.isCreated = isCreated;
    }

    public void destroy() {
        Display.destroy();
    }
}
