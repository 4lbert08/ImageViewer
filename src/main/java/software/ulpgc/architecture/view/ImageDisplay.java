package software.ulpgc.architecture.view;

import software.ulpgc.architecture.model.Image;

public interface ImageDisplay {
    int width();
    void paint(PaintOrder... orders);
    void on(Shift shift);
    void on(Released released);

    record PaintOrder(byte[] content, int offset){}

    interface Shift {
        Shift Null = offset -> {};
        void offset(int offset);
    }

    interface Released {
        Released Null = offset -> {};
        void offset(int offset);
    }
}
