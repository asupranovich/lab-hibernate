package com.itechart.lab.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HelloWorldJDBC {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/test3";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "abc123";

	public static void main(String[] args) {
		List<Student> students = getStudents();
		PrintUtils.printStudents(students);
		System.exit(0);
	}

	private static List<Student> getStudents() {

		List<Student> students = new ArrayList<>();

		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT ID, FIRSTNAME, LASTNAME FROM STUDENT");) {

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("ID"));
				student.setFirstName(rs.getString("FIRSTNAME"));
				student.setLastName(rs.getString("LASTNAME"));
				students.add(student);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return students;
	}

	private static Connection getConnection() throws Exception {
		Class.forName(DB_DRIVER);
		Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		return connection;
	}
}
