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
	
}
