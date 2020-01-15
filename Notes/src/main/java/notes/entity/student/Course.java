/**@Title: Course.java 
 * @Package com.brt.license.entity 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xwl 
 * @date 2019年4月16日 下午5:04:38 
 * @version V1.0   
 */

package notes.entity.student;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: Course
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author xwl
 * @date 2019年4月16日 下午5:04:38
 */
@Entity
@Table(name = "T_COURSE", schema = "BRTLIC")
public class Course {
	@Id
	@GeneratedValue(generator="sequenceGenerator")
	@GenericGenerator(
			name="sequenceGenerator",
			strategy="org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters={@Parameter(name = "sequence_name", value = "SEQ_COURSE")}
			)
	private Long id; // id
	@ApiModelProperty("")
	private String name; // 姓名
	@ApiModelProperty("")
	private String credit; // 学分
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="T_COURSE_STUDENT",
			joinColumns={@JoinColumn(name="course_id",referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="student_id",referencedColumnName="id")})
	@ApiModelProperty("")
	private Set<Student> students; 
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
