package mm.com.gic.ces.application.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_experience")
public class Experience implements Serializable{
	private static final long serialVersionUID = 1L;    
    private int exp_ID;				   //Experience ID
    private String exam_ID;            //EXAM ID
    private int exp_Year;              //Experience Year
    private String exp_Company;		   //Experience Company
    private String exp_Position;       //Experience Position
    
    //ID
  	@Id
  	@GeneratedValue
  	@Column(name = "EXP_ID", unique = true, nullable = false)
    public int getExp_ID() {
		return exp_ID;
	}

	public void setExp_ID(int exp_ID) {
		this.exp_ID = exp_ID;
	}
    //EXAM ID
	@Column(name = "EXAM_ID",length=20)
	public String getExam_ID() {
		return exam_ID;
	}

	public void setExam_ID(String exam_ID) {
		this.exam_ID = exam_ID;
	}
    //Experience Year
	@Column(name = "EXP_YEAR")
	public int getExp_Year() {
		return exp_Year;
	}

	public void setExp_Year(int exp_Year) {
		this.exp_Year = exp_Year;
	}

	@Column(name = "EXP_COMPANY",length=100)
	public String getExp_Company() {
		return exp_Company;
	}

	public void setExp_Company(String exp_Company) {
		this.exp_Company = exp_Company;
	}

	@Column(name = "EXP_POSITION",length=100)
	public String getExp_Position() {
		return exp_Position;
	}

	public void setExp_Position(String exp_Position) {
		this.exp_Position = exp_Position;
	}

}
