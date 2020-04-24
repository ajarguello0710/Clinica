package edu.udec.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class ConsultExamPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "pk_id_consult", nullable = false)
	private Consult consult;
	
	@ManyToOne
	@JoinColumn(name = "pk_id_exam", nullable = false)
	private Exam exam;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consult == null) ? 0 : consult.hashCode());
		result = prime * result + ((exam == null) ? 0 : exam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultExamPK other = (ConsultExamPK) obj;
		if (consult == null) {
			if (other.consult != null)
				return false;
		} else if (!consult.equals(other.consult))
			return false;
		if (exam == null) {
			if (other.exam != null)
				return false;
		} else if (!exam.equals(other.exam))
			return false;
		return true;
	}
	
	

}
