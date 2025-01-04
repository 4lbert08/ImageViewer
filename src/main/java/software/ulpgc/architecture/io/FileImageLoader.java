package software.ulpgc.architecture.io;

import software.ulpgc.architecture.model.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import java.util.List;


import static java.util.Objects.requireNonNull;

public class FileImageLoader implements ImageLoader{
    public static final List<Image.Format> VALID_EXTENSIONS = Arrays.stream(Image.Format.values()).toList();
    protected final File[] images;

    public FileImageLoader(File folder) {
        this.images = requireNonNull(folder.listFiles(this::isImage));
    }

    private boolean isImage(File file) {
        String fileName = file.getName().toLowerCase();
        return VALID_EXTENSIONS.stream()
                .anyMatch(e -> fileName.endsWith(e.name().toLowerCase()));
    }

    public Image load() {
        return this.imageAt(0);
    }

    private Image imageAt(int index) {
        return new Image() {
            int localIndex = index;

            public byte[] content() throws IOException {
                return Files.readAllBytes(images[localIndex].toPath());
            }

            public Image nextImage() {
                localIndex = (localIndex + 1) % images.length;
                return imageAt(localIndex);
            }

            public Image previousImage() {
                localIndex = (localIndex - 1 + images.length) % images.length;
                return imageAt(localIndex);
            }

            public String toString() {
                return images[localIndex].getAbsolutePath() + "\n";
            }
        };
    }
}