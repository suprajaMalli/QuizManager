package fr.epita.quiz.launcher;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.QuestionsJDBCDao;
import fr.epita.quiz.services.TopicsJDBCDao;
import fr.epita.quiz.logger.*;

public class Launcher {

	static QuestionsJDBCDao questionJDBCDao = new QuestionsJDBCDao();
	static TopicsJDBCDao topicsJDBCDao = new TopicsJDBCDao();
	static Student student;
	private static Scanner in;
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
			
			Logger.logMessage("Please enter your name");
			Scanner scanner = new Scanner(System.in);
	
			System.out.println("Please enter your name");
			String name = scanner.nextLine();
			System.out.println("Your name is:"+name);
			
			System.out.println("Please enter your id");
			String id = scanner.nextLine();
			System.out.println("Your id is:"+id);
			
		
			System.out.println("Please enter the number to perform the operations from the below list:");
			System.out.println("1.To add a new question");
			System.out.println("2.To update a question");
			System.out.println("3.To delete a question");
			System.out.println("4.To list all the topics");
			System.out.println("5.To search the question based on the label");
			
			System.out.println("6.To write the quiz which covers the below difficulty levels");
			System.out.println("7.To write the quiz based on the topics selected");
			String answer;
			answer = scanner.nextLine();
			System.out.println("you entered: "+answer);
			
			switch(answer) {
			case "1":
				System.out.println("Please enter the following information");
				handlingCreateQuestion();
				break;
			case "2":
				System.out.println("Please enter the following information");
				handlingUpdateQuestion();
				break;
			case "3":
				System.out.println("Please enter the following information");
				handlingDeleteQuestion();
				break;
			case "4":
				topicsJDBCDao.ListTopics();
				break;
			case "5":
				System.out.println("Please enter the following information");
				handlingSearchQuestion();
				break;
			case "6":
				System.out.println("Please enter the difficulty level ");
				handlingQuestionsOnDifficulty(scanner);
				break;
			case "7":
				System.out.println("Please enter the topic from below list ");
				handlingQuestionsOnTopic(scanner);
				break;
			default:
				System.out.println("please select valid option");
			}
		
	}
	
	public static void handlingCreateQuestion() {
		in = new Scanner(System.in);
		
		System.out.println("Could you please enter which type of question do you want to add");
		System.out.println("1.MCQ");
		System.out.println("2.Open Question");
		String answer = in.nextLine();
		
		if(answer.equals("1")) {
			
			System.out.println("Please enter the question");
			String questionName = in.nextLine();
	        System.out.println("Enter the topic name: ");
	        String topicName = in.next();
	        System.out.println("Enter the difficulty");
	        int dfc = in.nextInt(); 
	        System.out.println("Please enter the choice1");
	        String choice1 = in.next();
	        System.out.println("Please enter the choice2");
	        String choice2 = in.next(); 
	        System.out.println("Please enter the choice3");
	        String choice3 = in.next(); 
	        System.out.println("Please enter the choice4");
	        String choice4 = in.next(); 
	        System.out.println("Please enter the answer");
	        String questionAnswer = in.next();
	        questionJDBCDao.createQuestion(questionName,dfc);
	        topicsJDBCDao.createQuestionTopic(questionName, topicName);
	        questionJDBCDao.createMCQQuestion(questionName, choice1, choice2, choice3, choice4, questionAnswer);
	        System.out.println("Inserted successfully");
	        
			
		}
		else if(answer.equals("2")) {
			
			System.out.print("Enter the question: ");    
	        String questionName = in.nextLine();
	        System.out.println("Enter the topic name: ");
	        String topicName = in.next();
	        System.out.println("Enter the difficulty");
	        int dfc = in.nextInt();
	        
	        questionJDBCDao.createQuestion(questionName,dfc);
	        topicsJDBCDao.createQuestionTopic(questionName, topicName);
	       System.out.println("Inserted Successfully");
		}
		else {
			System.out.println("Invalid input");
		}
		
		
	}
	
	public static void handlingUpdateQuestion() {
		
		in = new Scanner(System.in);
		 
        System.out.print("Enter the question to update: ");    
        String questionName = in.nextLine();
        
        System.out.println("Enter the new difficulty");
        String dfc = in.nextLine();
        
        System.out.print("Enter the new question ");    
        String newQuestionName = in.nextLine();
        
        System.out.println("Enter the new topic");
        String topicName = in.nextLine();
      
        questionJDBCDao.updateQuestion(questionName, newQuestionName, dfc);
        topicsJDBCDao.updateQuestionTopic(questionName, newQuestionName, topicName);
        System.out.println("Updated successfully");
	}
	
	public static void handlingSearchQuestion() {
		
		in = new Scanner(System.in);
		
		 
        System.out.print("Enter the question label to search: ");    
        String questionLabel = in.next();
        
        
        questionJDBCDao.searchQuestion(questionLabel);
        System.out.println("Searched successfully");
        
        
	}
	
	public static void handlingDeleteQuestion() {
	 in = new Scanner(System.in);
		 
        System.out.print("Enter the question to be deleted ");    
        String questionName = in.next();
        questionJDBCDao.deleteQuestion(questionName);
        topicsJDBCDao.deleteQuestionTopic(questionName);
        System.out.println("deleted successfully");
		
	}
	
	public static void handlingQuestionsOnTopic(Scanner scanner) throws FileNotFoundException, UnsupportedEncodingException {
		
		System.out.println("please select one of the topics from the below list");
		topicsJDBCDao.ListTopics();
		
		System.out.println("Please enter the topic name here:");
		
		String answer;
		
		answer = scanner.nextLine();
		
		System.out.println("the topic you selected is:"+answer);
		
		questionJDBCDao.getQuestionsByTopic(answer);
		
		
		
		
	}
	
	public static void handlingQuestionsOnDifficulty(Scanner scanner) throws FileNotFoundException, UnsupportedEncodingException {
		
		System.out.println("Please select the difficulty level");		
		System.out.println("1.Easy");
		System.out.println("2.Medium");
		System.out.println("3.Difficult");
		
		String answer;
		
		answer = scanner.nextLine();
		
		System.out.println("the difficulty you have selected is:"+answer);
		
		questionJDBCDao.getQuestionsByDifficulty(answer);
		
		
	}
	

}
