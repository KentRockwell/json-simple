/**
 * 
 */
package org.swe.app;

/**
 *  QuestionAnswer is a Plain Old Jova Object used to map JSON 
 *  Question and Answer master files JSON array element to Java.
 * 
 * @author Kent Rockwell
 * @since Mar 30, 2017 6:34:12 AM
 *
 */
public class QuestionAnswer {
	private String id;
	private String question;
	private String answer;

	/**
	 * 
	 */
	public QuestionAnswer() {
		super();
	}

	/**
	 * @param id
	 * @param question
	 * @param answer
	 */
	public QuestionAnswer(String id, String question, String answer) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionAnswer [id=" + id + ", question=" + question
				+ ", answer=" + answer + "]";
	}

}
