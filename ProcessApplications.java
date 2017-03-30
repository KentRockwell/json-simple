/**
 * 
 */
package org.swe.app;

import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ProcessApplications does all the work:
 * 
 * 1. Loads and parse JSON master question and answer file to Java objects
 *    so it can be used to check applicants answers.
 *    
 * 2. Loads and parse JSON applications, checks and grades answers, 
 *    and puts applicant in accepted or rejected collection.
 *    The accepted collection is shared with the employer and the
 *    rejected collection is not.
 *    
 *    NOTES:
 *    1. Referrenced http://www.json.org for ECMA-404 The JSON Data Interchange Standard, 
 *       and recommended open source Java JSON parsers. 
 *       
 *    2. Selected json-simple because it was lightweight and easy to  build without any 
 *       external dependencies.  See github link below for source JSON parser code used.
 *       https://github.com/stleary/JSON-java/blob/master/JSONArray.java
 * 
 * @author Kent Rockwell
 * @since Mar 30, 2017 8:28:29 AM
 *
 */
public class ProcessApplications {
	private static final Logger LOGGER = Logger.getLogger(ProcessApplications.class.getName());
	private String masterFileName;
	private QuestionAnswerMaster questionAnswerMaster;
	
	private TreeMap<String, Application> acceptedApplications = new TreeMap<String, Application>();
	private TreeMap<String, Application> rejectedApplications = new TreeMap<String, Application>();


	/**
	 * @param JSON file name for question and answer master
	 */
	public ProcessApplications(final String masterFileName) {
		super();
		this.masterFileName = masterFileName;
		questionAnswerMaster = new QuestionAnswerMaster(masterFileName);
	}

	public boolean process(final String applicationFileName) {
		boolean accepted = false;
		
		Application application = new Application(applicationFileName);
		grade(application);
		if(application.getGrade() == 100) {
			acceptedApplications.put(application.getName(), application);
			LOGGER.log(Level.INFO, "Applicant " + application.getName() + " passed with a score of: " + application.getGrade());
			accepted = true;
		}else {
			rejectedApplications.put(application.getName(), application);
			LOGGER.log(Level.INFO, "Applicant " + application.getName() + " failed with a score of: " + application.getGrade());
			accepted = false;
		}
		
		return accepted;
	}
	
	/**
	 * Grade applicant answers against master question and answer file.
	 * @param application
	 */
	private void grade(Application application) {
		float correct = 0F;
		float incorrect = 0F;
		float finalGrade = 0.0F;
		
		ApplicantAnswer[] answers = application.toArray();
		int size = answers.length;
		for(int i = 0; i < size; i++) {
			if(questionAnswerMaster.isAnswerCorrect(answers[i].getId(), answers[i].getAnswer())) {
				correct++;
			} else {
				incorrect++;
			}
		}
		
		application.setCorrectAnswers((int)correct);
		application.setIncorrectAnswers((int)incorrect);
		finalGrade = ((correct/size)*100);
		application.setGrade(finalGrade);
	}
}
