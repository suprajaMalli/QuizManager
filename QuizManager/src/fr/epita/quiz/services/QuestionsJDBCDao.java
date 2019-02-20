package fr.epita.quiz.services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionsJDBCDao {

	private static final String INSERT_QUERY = "INSERT INTO QUESTION (QUESTION,DIFFICULTY) VALUES ( ?, ?)";
	private static final String UPDATE_QUERY = "UPDATE QUESTION SET QUESTION=?,DIFFICULTY=? WHERE QUESTION=?";
	private static final String DELETE_QUERY = "DELETE FROM QUESTION WHERE QUESTION=?";
	private static final String SEARCH_QUERY = "SELECT QUESTION FROM QUESTION WHERE QUESTION like ?";
	
	
	
	public void createQuestion(String questionName, int dfc) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(INSERT_QUERY);
			stmt.setString(1, questionName);
			stmt.setInt(2, dfc);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
	public void createMCQQuestion(String questionName, String choice1, String choice2, String choice3, String choice4,String answer) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO MCQ_CHOICES_ANSWERS (QUESTION,CHOICE1,CHOICE2,CHOICE3,CHOICE4,ANSWER) VALUES ( ?, ?,?,?,?,?)");
			stmt.setString(1, questionName);
			stmt.setString(2, choice1);
			stmt.setString(3, choice2);
			stmt.setString(4, choice3);
			stmt.setString(5, choice4);
			stmt.setString(6, answer);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateQuestion(String questionName, String newQuestionName, String dfc) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(UPDATE_QUERY);
			stmt.setString(1, newQuestionName);
			stmt.setString(2, dfc);
			stmt.setString(3, questionName);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteQuestion(String questionName) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(DELETE_QUERY);
			stmt.setString(1, questionName);
			
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void searchQuestion(String question) {
	
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(SEARCH_QUERY);
			
			stmt.setString(1, "%" + question+ "%");
			
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String SearchedQuestion = rs.getString(1);
				System.out.format("%s  \n",SearchedQuestion);
				
			}
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void getQuestionsByTopic(String topic) throws FileNotFoundException, UnsupportedEncodingException {
		
		String selectQuery = "select Question from QUESTION_TOPIC_ASSOCIATION where topic_name=?";
		
		PrintWriter writer;
		
			writer = new PrintWriter("outputFile/QuestionsBasedOnTopic.txt", "UTF-8");
			
			
		try  {
			Connection connection = getConnection();
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			
			selectStatement.setString(1,topic);
			
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()) {
				
				String question = rs.getString(1);
				
				System.out.format("%s  \n",question);
				
				
				writer.println(question);
			}
			selectStatement.close();
			connection.close();
			writer.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
public void getQuestionsByDifficulty(String difficulty) throws FileNotFoundException, UnsupportedEncodingException {
		
		String selectQuery = "select Question from QUESTION where difficulty=?";
		PrintWriter writer;
		
		writer = new PrintWriter("outputFile/QuestionsBasedOnDifficulty.txt", "UTF-8");
		
		try  {
			Connection connection = getConnection();
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			
			selectStatement.setString(1,difficulty);
			
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()) {
				
				String question = rs.getString(1);
				
				System.out.format("%s  \n",question);
				writer.println(question);
			}
			selectStatement.close();
			connection.close();
			writer.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	private Connection getConnection() throws SQLException{
		Configuration conf = Configuration.getInstance();
		String jdbcUrl = conf.getConfigurationValue("jdbc.url");
		String user = conf.getConfigurationValue("jdbc.user");
		String password = conf.getConfigurationValue("jdbc.password");
		
		
		return DriverManager.getConnection(jdbcUrl, user, password);
	}
}
