package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvProcessorTest {

	@Test
	void testCsvProcessing() {
		List<User> users = new ArrayList<>();
		users.add(new User("John", "Doe", 25, "john@oril.co"));
		users.add(new User("Jane", "Doe", 21, "jane@oril.co"));

		CsvProcessor csvProcessor = new CsvProcessor();
		File file = csvProcessor.generateCsvFile(users);

		assertNotNull(file);
		assertTrue(file.isFile());
		assertEquals("users.csv", file.getName());

		List<User> parsedUsers = csvProcessor.parseCvsFile(file);
		assertFalse(parsedUsers.isEmpty());
		assertEquals(users.size(), parsedUsers.size());
		assertEquals(users.get(0).getFirstName(), parsedUsers.get(0).getFirstName());
		assertEquals(users.get(1).getEmail(), parsedUsers.get(1).getEmail());

		assertTrue(file.delete());
	}

}
