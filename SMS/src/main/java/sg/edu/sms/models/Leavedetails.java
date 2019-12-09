package sg.edu.sms.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Leavedetails {

	@Id
	private int leaveId;
	private int days;
	@DateTimeFormat(iso=ISO.DATE)
	private Date start_date;
	@DateTimeFormat(iso=ISO.DATE)
	private Date End_date;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="Staff_id")
	private Staff staff;

	public Leavedetails(int leaveId, int days, Date start_date, Date end_date, String description) {
		super();
		this.leaveId = leaveId;
		this.days = days;
		this.start_date = start_date;
		End_date = end_date;
		this.description = description;
	}

	public Leavedetails() {
		super();
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return End_date;
	}

	public void setEnd_date(Date end_date) {
		End_date = end_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((End_date == null) ? 0 : End_date.hashCode());
		result = prime * result + days;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + leaveId;
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
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
		Leavedetails other = (Leavedetails) obj;
		if (End_date == null) {
			if (other.End_date != null)
				return false;
		} else if (!End_date.equals(other.End_date))
			return false;
		if (days != other.days)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (leaveId != other.leaveId)
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Leavedetails [leaveId=" + leaveId + ", days=" + days + ", start_date=" + start_date + ", End_date="
				+ End_date + ", description=" + description + "]";
	}
	
	
	
	
}
