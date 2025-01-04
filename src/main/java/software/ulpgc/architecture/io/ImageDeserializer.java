package software.ulpgc.architecture.io;

import software.ulpgc.architecture.model.Image;

public interface ImageDeserializer {
    Object deserialize(byte[] bytes);
}
