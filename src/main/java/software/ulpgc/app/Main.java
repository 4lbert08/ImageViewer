package software.ulpgc.app;

import software.ulpgc.architecture.control.ImagePresenter;
import software.ulpgc.architecture.control.NextImageCommand;
import software.ulpgc.architecture.control.PreviousImageCommand;
import software.ulpgc.architecture.io.FileImageLoader;
import software.ulpgc.architecture.io.ImageLoader;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File folder = new File("C:\\Users\\User\\Universidad\\3º\\IS2\\projecto imageviewer\\imagenes");
        ImageLoader loader = new FileImageLoader(folder);
        MainFrame mainFrame = new MainFrame();
        ImagePresenter presenter = new ImagePresenter(mainFrame.getDisplay(), loader);
        mainFrame
                .put("<", new PreviousImageCommand(presenter))
                .put(">", new NextImageCommand(presenter))
                .setVisible(true);
    }
}
