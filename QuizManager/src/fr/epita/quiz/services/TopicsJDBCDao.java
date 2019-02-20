package fr.epita.quiz.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.epita.quiz.datamodel.Questions;
import fr.epita.quiz.datamodel.Topics;


public class TopicsJDBCDao {
	
	Topics topic;
	Questions question;
	
	public void createQuestionTopic(String questionName,String topicName) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("insert into QUESTION_TOPIC_ASSOCIATION (Question,topic_name) values(?,?)");
			stmt.setString(1, questionName);
			stmt.setString(2, topicName);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateQuestionTopic(String questionName, String newQuestionName, String topicName) {
		
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("UPDATE QUESTION_TOPIC_ASSOCIATION SET QUESTION=?,TOPIC_NAME=? WHERE QUESTION=?");
			stmt.setString(1, newQuestionName);
			stmt.setString(2, topicName);
			stmt.setString(3, questionName);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteQuestionTopic(String questionName) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("DELETE QUESTION_TOPIC_ASSOCIATION WHERE QUESTION=?");
			stmt.setString(1, questionName);
			
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ListTopics() {
		
		
		String sqlCommand = "select * from topics_v1";
		try  {
			Connection connection = getConnection();
			PreparedStatement selectStatement = connection.prepareStatement(sqlCommand);
			
			
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()) {
				int topicID = rs.getInt(1);
				String topicName = rs.getString(2);
				
				System.out.format("%s %s \n",topicID,topicName);
			}
			selectStatement.close();
			connection.close();

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
