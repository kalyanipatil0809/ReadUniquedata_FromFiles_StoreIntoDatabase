package in.sts.excelutility.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.sts.excelutility.dao.InsertTablesData;
import in.sts.excelutility.model.MarksModel;
import in.sts.excelutility.model.StudentModel;

public class ReadExcelTable {
	final Logger log = Logger.getLogger(ReadExcelTable.class);

	public void readExcel() {
		Scanner fileInput = null;
		ArrayList<StudentModel> arrayList = new ArrayList<StudentModel>();
		try {
			fileInput = new Scanner(System.in);
			System.out.println("Enter the filename: ");
			String fileName = fileInput.nextLine();
			FileInputStream inputStream = new FileInputStream("F:\\" + fileName + ".xlsx");

			Workbook workBook = new XSSFWorkbook(inputStream);
			DataFormatter dataFormatter = new DataFormatter();
			Iterator<Sheet> sheet1 = workBook.sheetIterator();

			while (sheet1.hasNext()) {
				Sheet sh = sheet1.next();
				System.out.println("Sheet Name is: " + sh.getSheetName());

				Iterator<Row> iterator = sh.iterator();
				while (iterator.hasNext()) {
					Row row = iterator.next();
					Iterator<Cell> cellIterator = row.iterator();

					StudentModel studentModel = new StudentModel();
					MarksModel marksModel = new MarksModel();

					int count = 1;

					String firstName = null;
					String middleName = null;
					String lastName = null;
					String branch = null;
					String mathsMarks = null;
					String englishMarks = null;
					String scienceMarks = null;
					
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (count) {
						case 1:
							firstName = dataFormatter.formatCellValue(cell);
							studentModel.setFirstName(firstName);
							count++;
							break;

						case 2:
							middleName = dataFormatter.formatCellValue(cell);
							studentModel.setMiddleName(middleName);
							count++;
							break;

						case 3:
							lastName = dataFormatter.formatCellValue(cell);
							studentModel.setLastName(lastName);
							count++;
							break;

						case 4:
							branch = dataFormatter.formatCellValue(cell);
							studentModel.setBranch(branch);
							count++;
							break;

						case 5:
							mathsMarks = dataFormatter.formatCellValue(cell);
							marksModel.setMaths(Integer.parseInt(mathsMarks));
							count++;
							break;

						case 6:
							englishMarks = dataFormatter.formatCellValue(cell);
							marksModel.setEnglish(Integer.parseInt(englishMarks));
							count++;
							break;

						case 7:
							scienceMarks = dataFormatter.formatCellValue(cell);
							marksModel.setScience(Integer.parseInt(scienceMarks));
							count++;
							break;
					}
					}
					studentModel.setMarksModel(marksModel);
					arrayList.add(studentModel);
				}
				
			}

			HashSet<StudentModel> uniqueSet = new HashSet<StudentModel>(arrayList);
			
			InsertTablesData insertTablesData = new InsertTablesData();
			insertTablesData.insertTableQuery(uniqueSet,"xlsx");
			
		for (StudentModel studentList : uniqueSet) {
				System.out.println(studentList);
			}
	
			workBook.close();
		} catch (FileNotFoundException exception) {
			System.out.println("Please enter the correct file name..!");

			readExcel();

		} catch (IOException exception) {
			System.out.println("Data Not Found");
		} finally {
			if (fileInput != null)
				fileInput.close();
		}

	}
}