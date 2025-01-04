package software.ulpgc.architecture.control;

import software.ulpgc.architecture.view.ImageDisplay;

public class PreviousImageCommand implements Command{
    private final ImagePresenter presenter;

    public PreviousImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.showPreviousImage();
    }
}
