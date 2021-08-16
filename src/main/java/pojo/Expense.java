package pojo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity(name = "expenses")
public class Expense implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "expenses_expense_id_seq", allocationSize = 1)
	@Column(name = "expense_id")
	private int expenseID;
	@Column(name = "submitted_by")
	private int submittedBy;
	@Column(name = "approved_by")
	private int approvedBy;
	@Column(name = "is_approved")
	private boolean isApproved;
	@Column(name = "expense_amount")
	private double expenseAmount;
//	@Column(name="reciept_pic")
//	private WrappedMaterializedBlobType recieptPicture;
	@Column(name = "reimbursement_type")
	private String reimbersementType;
	@Column
	private String details;
	@CreationTimestamp
	@Column(name = "date_submitted")
	private Timestamp dateSubmitted; // must use "now" when saving...

	public Expense() {
		super();
	}

	public Expense(int expenseID, int submittedBy, int approvedBy, boolean isApproved, double expenseAmount,
			String reimbersementType, String details, Timestamp dateSubmitted) {
		super();
		this.expenseID = expenseID;
		this.submittedBy = submittedBy;
		this.approvedBy = approvedBy;
		this.isApproved = isApproved;
		this.expenseAmount = expenseAmount;
//		this.recieptPicture = recieptPicture;
		this.reimbersementType = reimbersementType;
		this.details = details;
		this.dateSubmitted = dateSubmitted;
	}

	
	public Expense(int submittedBy, int approvedBy, boolean isApproved, double expenseAmount, String reimbersementType,
			String details) {
		super();
		this.submittedBy = submittedBy;
		this.approvedBy = approvedBy;
		this.isApproved = isApproved;
		this.expenseAmount = expenseAmount;
		this.reimbersementType = reimbersementType;
		this.details = details;
	}

	public int getExpenseID() {
		return expenseID;
	}

	public void setExpenseID(int expenseID) {
		this.expenseID = expenseID;
	}

	public int getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(int submittedBy) {
		this.submittedBy = submittedBy;
	}

	public int getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public double getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public String getReimbersementType() {
		return reimbersementType;
	}

	public void setReimbersementType(String reimbersementType) {
		this.reimbersementType = reimbersementType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public void updateTimeStamp() {
		this.dateSubmitted.setTime(dateSubmitted.getTime());
	}

	@Override
	public String toString() {
		return "Expense [expenseID=" + expenseID + ", submittedBy=" + submittedBy + ", approvedBy=" + approvedBy
				+ ", isApproved=" + isApproved + ", expenseAmount=" + expenseAmount + ", reimbersementType="
				+ reimbersementType + ", details=" + details + ", dateSubmitted=" + dateSubmitted + "]";
	}

//	@PrePersist
//	protected void onCreate() {
//		this.dateSubmitted.setTime(dateSubmitted.getTime());
//	}
//
//	@PreUpdate
//	protected void onUpdate() {
//		this.dateSubmitted.setTime(dateSubmitted.getTime());
//	}

}
