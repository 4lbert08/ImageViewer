package software.ulpgc.architecture.control;

public class NextImageCommand implements Command{
    private  final ImagePresenter presenter;

    public NextImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.showNextImage();
    }
}
