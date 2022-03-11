package in.sts.excelutility.files;

import org.apache.log4j.Logger;

import in.sts.excelutility.dao.InsertTablesData;
import in.sts.excelutility.model.MarksModel;
import in.sts.excelutility.model.StudentModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class ReadTextTable {
	final Logger log = Logger.getLogger(ReadTextTable.class);

	public void readTable() {
		Scanner fileInput = null;
		Scanner scanner = null;

		HashSet<StudentModel> uniqueSet = new HashSet<StudentModel>();
		try {
			fileInput = new Scanner(System.in);
			System.out.println("Enter the filename: ");

			String fileName = fileInput.nextLine();
			File file = new File("F:\\" + fileName + ".txt");
			FileReader fileReader = new FileReader(file);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;

			while ((line = bufferedReader.readLine()) != null) {

				StudentModel studentModel = new StudentModel();

				String s[] = line.split("\t\t");

				studentModel.setFirstName(s[0].trim());
				studentModel.setMiddleName(s[1].trim());
				studentModel.setLastName(s[2].trim());
				studentModel.setBranch(s[3].trim());

				MarksModel marksModel = new MarksModel();

				studentModel.setMarksModel(marksModel);
				studentModel.getMarksModel().setMaths(Integer.parseInt(s[4].trim()));
				studentModel.getMarksModel().setEnglish(Integer.parseInt(s[5].trim()));
				studentModel.getMarksModel().setScience(Integer.parseInt(s[6].trim()));
				

				uniqueSet.add(studentModel);
			}

			InsertTablesData insertTablesData = new InsertTablesData();
			insertTablesData.insertTableQuery(uniqueSet, "txt");

			System.out.print(uniqueSet);
			fileReader.close();

		} catch (IOException e) {
			System.out.println("Data Not Found..!");
		} finally {
			if (fileInput != null)
				fileInput.close();

			if (scanner != null)
				scanner.close();

		}
	}

}
