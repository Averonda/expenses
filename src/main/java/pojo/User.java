package pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        name="users", 
        uniqueConstraints=
            @UniqueConstraint(columnNames={"username"})
    )
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name="id_generator", sequenceName = "users_user_id_seq", allocationSize = 1)
	@Column(name="user_id")
	private int userID;
	@Column(name="username")
	private String userName;
	@Column
	private String password;
	@Column(name="is_approver")
	private boolean isApprover;
	
	
	
	public User() {
		super();
	}
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public User(int userID, String userName, String password, boolean isApprover) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.isApprover = isApprover;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isApprover() {
		return isApprover;
	}
	public void setApprover(boolean isApprover) {
		this.isApprover = isApprover;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + "]";
	}
	
	
}
