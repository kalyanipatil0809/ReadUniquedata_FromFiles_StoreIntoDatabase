package in.sts.excelutility.files;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import in.sts.excelutility.dao.InsertTablesData;
import in.sts.excelutility.model.MarksModel;
import in.sts.excelutility.model.StudentModel;

public class ReadXMLFile {
	final Logger log = Logger.getLogger(ReadXMLFile.class);

	public void readXML() {

		Scanner fileInput = null;
		try {
			fileInput = new Scanner(System.in);
			System.out.println("Enter the filename: ");
			String fileName = fileInput.nextLine();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("C:\\Users\\kalyani.patil\\eclipse-workspace\\ExcelUtility\\" + fileName + ".xml");

			NodeList list = document.getElementsByTagName("student");

			HashSet<StudentModel> uniqueSet = new HashSet<StudentModel>();

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				Element element = (Element) node;

				NodeList marksList = element.getElementsByTagName("marks");

				StudentModel studentModel = new StudentModel();
				MarksModel marksModel = new MarksModel();

				studentModel.setMarksModel(marksModel);

				studentModel.setFirstName(element.getElementsByTagName("firstName").item(0).getTextContent());
				studentModel.setMiddleName(element.getElementsByTagName("middleName").item(0).getTextContent());
				studentModel.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
				studentModel.setBranch(element.getElementsByTagName("branch").item(0).getTextContent());

				for (int j = 0; j < marksList.getLength(); j++) {
					Node marksNode = marksList.item(j);
					Element element1 = (Element) marksNode;

					studentModel.getMarksModel().setMaths(
							Integer.parseInt(element1.getElementsByTagName("maths").item(0).getTextContent()));
					studentModel.getMarksModel().setEnglish(
							Integer.parseInt(element1.getElementsByTagName("english").item(0).getTextContent()));
					studentModel.getMarksModel().setScience(
							Integer.parseInt(element1.getElementsByTagName("science").item(0).getTextContent()));
				}

				uniqueSet.add(studentModel);
			}

			System.out.println("Unique data from XML file: ");

			InsertTablesData insertTablesData = new InsertTablesData();
			insertTablesData.insertTableQuery(uniqueSet, "xml");

			for (StudentModel students : uniqueSet) {
				System.out.println(students);
			}
		}

		catch (SAXException exception) {
			System.out.println("Error in parsin.." + exception.getMessage());

		} catch (IOException exception) {
			System.out.println("Data Not Found..!");

			readXML();

		} catch (ParserConfigurationException exception) {
			System.out.println("Parsing error.." + exception.getMessage());
		}

		finally {
			if (fileInput != null)
				fileInput.close();

		}

	}

}
