package app.java.com.view.interfaces;

import java.io.File;

public interface UploadTemplateView {
    void onSuccessTemplateCreated();

    boolean isFileValid(File file);

    void onErrorUploadingFile();

}