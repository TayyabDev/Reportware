package app.java.com.view.interfaces;

public interface UploadTemplateView {
    void onSuccessTemplateCreated();


    boolean isFileValid(String filePath);

    void onErrorUploadingFile();

}
