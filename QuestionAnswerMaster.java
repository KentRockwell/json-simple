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
 * QuestionAnswerMaster loads JSON question and answer JSON master file, 
 * parses the JSON elements and maps them to QuestionAnswer object, and 
 * then stores each  QuestionAnswer collection in a collection.  
 * The QuestionAnswer is used to grade applicants answers.
 * 
 * @author Kent Rockwell
 * @since Mar 30, 2017 6:33:15 AM
 *
 */
public class QuestionAnswerMaster {
	private static final Logger LOGGER = Logger.getLogger(QuestionAnswerMaster.class.getName());

	private TreeMap<String, QuestionAnswer> questionsAnswers = new TreeMap<String, QuestionAnswer>();
	private String fileName;

	/**
	 * 
	 * @param JSON file name for question and answer master
	 */
	public QuestionAnswerMaster(final String fileName) {
		super();
		this.fileName = fileName;
		init();
	}
	
	/**
	 * Initialize question and answer collection from JSON file.
	 */
	private void init() {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(fileName));
			JSONObject jsonObject = (JSONObject) obj;
			LOGGER.log(Level.INFO, obj.toString());
			Object master = jsonObject.get("master");
			LOGGER.log(Level.INFO, master.toString());
			JSONObject jsonObject2 = (JSONObject) master;
			Object questionkeys = jsonObject2.get("questionkeys");
			LOGGER.log(Level.INFO, questionkeys.toString());
			JSONObject jsonObject3 = (JSONObject) questionkeys;
			Object questionkey = jsonObject3.get("questionkey");
			LOGGER.log(Level.INFO, questionkey.toString());
			
			JSONArray array = (JSONArray) questionkey;
			int size = array.size();
			for (int i = 0; i < size; i++) {
				JSONObject id = (JSONObject) array.get(i);
				String idstr = (String)id.get("id");
				String qstr = (String)id.get("Question");
				String astr = (String)id.get("Answer");
				QuestionAnswer quesansw = new QuestionAnswer(idstr, qstr, astr);			
				add(quesansw);
				LOGGER.log(Level.INFO, quesansw.toString());
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
	 * Add a QuestionAnswer to the collection.
	 * @param questionAnswer
	 */
	private void add(QuestionAnswer questionAnswer) {
		questionsAnswers.put(questionAnswer.getId(), questionAnswer);
	}
	
	/**
	 * Compare applicants answer to master answer.
	 * 
	 * @param id Question id
	 * @param answer The applicants answer to question.
	 * @return true if correct and false if incorrect, or question not found in collection.
	 */
	public boolean isAnswerCorrect(final String id, final String answer) {
		boolean results = false;
		QuestionAnswer qa = questionsAnswers.get(id);
		if(qa == null) {
			LOGGER.log(Level.SEVERE, "Question not found for id=" + id);
			results = false;
		} else if(qa.getAnswer().equals(answer)) {
			results = true;
		}
		
		return results;
	}
}
