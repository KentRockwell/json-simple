/**
 * 
 */
package org.swe.app;

/**
 * @author Kent Rockwell
 * @since Mar 30, 2017 8:44:40 AM
 *
 */
public class ApplicantAnswer {
	private String id;
	private String answer;

	/**
	 * 
	 */
	public ApplicantAnswer() {
		super();
	}

	/**
	 * @param id
	 * @param answer
	 */
	public ApplicantAnswer(String id, String answer) {
		super();
		this.id = id;
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
		return "ApplicantAnswers [id=" + id + ", answer=" + answer + "]";
	}

}
