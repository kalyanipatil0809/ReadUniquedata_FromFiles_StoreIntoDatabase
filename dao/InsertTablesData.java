package in.sts.excelutility.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

import in.sts.excelutility.model.StudentModel;
import in.sts.excelutility.mysqlconnection.DBConnection;

public class InsertTablesData {

	public void insertData(HashSet<StudentModel> uniqueSet,String insertQuery) {
		PreparedStatement preparedStatement = null;
		try {
			Connection connection = DBConnection.connect();
			preparedStatement = connection.prepareStatement(insertQuery);
			if (preparedStatement != null) {
				for (StudentModel studentModel : uniqueSet) {
					preparedStatement.setString(1, studentModel.getFirstName());
					preparedStatement.setString(2, studentModel.getMiddleName());
					preparedStatement.setString(3, studentModel.getLastName());
					preparedStatement.setString(4, studentModel.getBranch());
					preparedStatement.setInt(5, studentModel.getMarksModel().getMaths());
					preparedStatement.setInt(6, studentModel.getMarksModel().getEnglish());
					preparedStatement.setInt(7, studentModel.getMarksModel().getScience());
					preparedStatement.executeUpdate();
				}
			}
			System.out.println("Data uploaded in database");
			connection.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void insertTableQuery(HashSet<StudentModel> uniqueSet,String fileExtension) {
		String insertQuery;
		if (fileExtension.equals("txt")) {
			insertQuery = "Insert into TextTableData  values(s_id,?,?,?,?,?,?,?)"; 
		}
		else if(fileExtension.equals("xlsx")) {
			insertQuery = "Insert into ExcelTabledata  values(stu_id,?,?,?,?,?,?,?)"; 
		}
		else {
			insertQuery = "Insert into XmlTableData  values(student_id,?,?,?,?,?,?,?)"; 
		}
		InsertTablesData insertTablesData = new InsertTablesData();
		insertTablesData.insertData(uniqueSet, insertQuery);

	} 

}
