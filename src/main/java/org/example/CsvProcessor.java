package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvProcessor {

	public File generateCsvFile(List<User> users) {
		File csvOutputFile = new File("users.csv");
		try (FileWriter out = new FileWriter(csvOutputFile);
		     CSVPrinter printer = new CSVPrinter(out, CSVFormat.Builder.create().setHeader(
				     "First Name", "Last Name", "Age", "Email").build())) {
			users.forEach(user -> {
				try {
					printer.printRecord(
							user.getFirstName(),
							user.getLastName(),
							user.getAge(),
							user.getEmail());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return csvOutputFile;
	}

	public List<User> parseCvsFile(File file) {
		try {
			Reader in = new FileReader(file);
			CSVParser parser = CSVFormat.Builder.create().setDelimiter(',').setHeader().build().parse(in);

			List<User> users = new ArrayList<>();

			for (CSVRecord row : parser) {
				User user = new User();
				//Reading by Header name
				user.setFirstName(row.get("First Name"));
				user.setLastName(row.get("Last Name"));
				//Or by column index
				user.setAge(Integer.parseInt(row.get(2)));
				user.setEmail(row.get(3));
				users.add(user);
			}
			return users;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

}
