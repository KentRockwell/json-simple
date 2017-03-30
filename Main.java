/**
 * 
 */
package org.swe.app;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Assignment:
 * Being able to automatically filter out applications from unqualified 
 * applicants can save busy hiring managers a lot of time.  They only 
 * need to spend time looking at applicants who meet their minimum 
 * qualifications.  Why waste time reading through delivery driver applications 
 * from people who don’t have a vehicle if you require drivers to use their own vehicle?
 * 
 * Implement code that determines whether a job application meets 
 * a set of minimum qualifications.  The job application will be a list of questions,
 * each of which has a question id and an answer.  The qualifications will be a list of 
 * question ids, each associated with a list of acceptable answers.  If an application 
 * fails to answer any one of these questions with an acceptable answer, the application 
 * should be rejected.  Otherwise the application should be accepted.  The employer
 *  should be able to view only the accepted applications.
 *  
 *  Implement a solution that:
 *  Contains a list of Questions with an acceptable answer for each question:
 *  [ { Id: "id1", Question: "string", "Answer": "string" }, { Id: "id2", Question: "string", "Answer": "string" }, … ]
 *  Receives job applications where each application is a JSON document conforming to this design:
 *  { Name: "string",  Questions: [ {  Id: "id10", Answer: "string" }, {  Id: "id20", Answer: "string" }, … ] }
 *  
 *  The program should decide to accept or reject each application.
 *  Accepted applications must answer all questions correctly.
 *  Accepted applications must be shown to the employer.
 *  not accepted applications must not be shown to the employer.
 *  
 * 
 * @author Kent Rockwell
 * @since Mar 30, 2017 6:32:55 AM
 *
 */
public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	/**
	 * 
	 */
	private Main() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String masterFileName = "../SoftwareEngineerApplications/data/qAndA.txt";
		ProcessApplications processApps = new ProcessApplications(masterFileName);
		
		final String applicationFileName1 = "../SoftwareEngineerApplications/data/application1.txt";
		processApps.process(applicationFileName1);
		
		final String applicationFileName2 = "../SoftwareEngineerApplications/data/application2.txt";
		processApps.process(applicationFileName2);
	}

}
