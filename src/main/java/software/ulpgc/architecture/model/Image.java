package software.ulpgc.architecture.model;

import java.io.File;
import java.io.IOException;

public interface Image {
    byte[] content() throws IOException;
    Image previousImage();
    Image nextImage();

    public static enum Format {
        png,
        jpeg,
        jpg;
    }
}
