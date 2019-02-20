package fr.epita.quiz.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

	private static PrintWriter writer;
	private static boolean initialized = false;
	
	public static void logMessage(String message) {
		
		if(!initialized) {
			try {
				writer =  new PrintWriter(new FileWriter(new File("application.log"),true));
			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println("error while initializing logger message");
				writer = new PrintWriter(System.out);
			}
			initialized = true;
			
		
		}
		writer.println(message);
	}
}
