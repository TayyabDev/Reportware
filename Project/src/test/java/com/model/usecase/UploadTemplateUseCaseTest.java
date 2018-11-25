package test.java.com.model.usecase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.usecase.UploadTemplateUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import test.java.com.model.utilities.ClientProfileMockFile;
import test.java.com.model.utilities.MockAccount;
import test.java.com.model.utilities.MockTemplate;

public class UploadTemplateUseCaseTest implements UploadTemplateResultInterface {

	private static final String TEMPLATE_NAME = "Client Profile";
	private static final int ACCOUNT_ID = 8;
	private static final String USER_NAME = "bob";
	private static final String PASSWORD = "abc";

	private boolean testResult = false;

	@Override
	public void onSuccessUploadingTemplate() {
		testResult = true;
	}

	@Override
	public void onErrorUploadingTemplate(List<InsertException> errorMessages) {
		System.out.println("Error uploading template");
	}


	// We can assume that before running this usecase, that our file is verified and valid
	@SuppressWarnings("deprecation")
	@Test
	public void testUploadTemplateUseCaseUsingNormalMockFile() {

		UseCase useCase = new UploadTemplateUseCase(this, new Date(118, 10, 12), TEMPLATE_NAME,
				new ClientProfileMockFile(new MockTemplate()),
				new MockAccount(ACCOUNT_ID, USER_NAME, PASSWORD, true));

		useCase.run();

		assertTrue(testResult);
	}
}
