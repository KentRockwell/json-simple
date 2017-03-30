/**
 * 
 */
package org.swe.app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Application is a Plain Old Jova Object used to map JSON application files to Java.
 * 
 * @author Kent Rockwell
 * @since Mar 30, 2017 8:11:25 AM
 *
 */
public class Application {
	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
	private String fileName;
	private String name;
	private TreeMap<String, ApplicantAnswer> applicantAnswers = new TreeMap<String, ApplicantAnswer>();
	private int correctAnswers = 0;
	private int incorrectAnswers = 0;
	private float grade = 0;

	/**
	 * Need a valid JSON file name to load or the class is not usable.
	 */
	public Application(final String fileName) {
		super();
		this.fileName = fileName;
		init();
	}
	
	private void init() {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(fileName));
			JSONObject jsonObject = (JSONObject) obj;
			LOGGER.log(Level.INFO, obj.toString());
			Object objectName = jsonObject.get("Name");
			this.name = objectName.toString();
			LOGGER.log(Level.INFO, "Name=" + this.name);
			Object questions = jsonObject.get("Questions");
			LOGGER.log(Level.INFO, questions.toString());
			JSONArray array = (JSONArray) questions;
			int size = array.size();
			for (int i = 0; i < size; i++) {
				JSONObject question = (JSONObject) array.get(i);
				String questionId = (String)question.get("id");
				String answer = (String)question.get("Answer");
				ApplicantAnswer applicatantAnswer = new ApplicantAnswer(questionId, answer);
				add(applicatantAnswer);			
			}
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "FileNotFoundException: " + e.toString());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IOException: " + e.toString());
		} catch (ParseException e) {
			LOGGER.log(Level.SEVERE, "ParseException: " + e.toString());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception: " + e.toString());
		}
	}
	/**
	 * Add a ApplicantAnswer to the collection.
	 * 
	 * @param An applicants answer.
	 */
	private void add(final ApplicantAnswer answer) {
		applicantAnswers.put(answer.getId(), answer);
	}
	
	public ApplicantAnswer[] toArray() {
		synchronized (this) {
			ApplicantAnswer[] answers = new ApplicantAnswer[applicantAnswers.size()];
			answers = applicantAnswers.values().toArray(answers);
			return answers;
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the correctAnswers
	 */
	public int getCorrectAnswers() {
		return correctAnswers;
	}

	/**
	 * @param correctAnswers the correctAnswers to set
	 */
	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	/**
	 * @return the incorrectAnswers
	 */
	public int getIncorrectAnswers() {
		return incorrectAnswers;
	}

	/**
	 * @return the grade
	 */
	public float getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(float grade) {
		this.grade = grade;
	}

	/**
	 * @param incorrectAnswers the incorrectAnswers to set
	 */
	public void setIncorrectAnswers(int incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Application [fileName=" + fileName + ", name=" + name
				+ ", applicantAnswers=" + applicantAnswers
				+ ", correctAnswers=" + correctAnswers + ", incorrectAnswers="
				+ incorrectAnswers + "]";
	}

}
