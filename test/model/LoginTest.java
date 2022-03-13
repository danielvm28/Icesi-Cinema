package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import exception.InvalidIDException;

class LoginTest {
	
	// Setup stages
	public void setupStage1() {
		
		try {
			String ids = "1004585666\n20054208\n31558795\n100";
			File file = new File("data/loginData.txt");
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			fos.write(ids.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// Tests if the user can successfully log in with a valid ID
	@Test
	void acceptanceTest() {
		String id = "1004585666";
		
		assertDoesNotThrow(() -> {
			IcesiCinema.login(id);
		});
	}
	
	// Tests if the user gets rejected and cannot log in with an invalid ID
	@Test
	void rejectTest() {
		String id = "1007365222";
		
		assertThrows(InvalidIDException.class, () -> {
			IcesiCinema.login(id);
		});
	}
}
