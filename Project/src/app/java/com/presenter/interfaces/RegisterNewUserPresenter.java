package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.CreateAccountView;
import app.java.com.view.interfaces.RegisterNewUserView;

public interface RegisterNewUserPresenter {

//	void registerNewUser(User newUser);

    void attachView(RegisterNewUserView view);

    void unbindView();
}
