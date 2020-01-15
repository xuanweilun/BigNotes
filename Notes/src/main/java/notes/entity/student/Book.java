/**@Title: Book.java 
 * @Package com.brt.license.entity 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xwl 
 * @date 2019年4月15日 下午3:09:27 
 * @version V1.0   
 */

package notes.entity.student;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * @ClassName: Book
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author xwl
 * @date 2019年4月15日 下午3:09:27
 */
@Entity
@Table(name = "T_BOOK", schema = "BRTLIC")
public class Book {
	@Id
	@GeneratedValue(generator = "sequenceGenerator")
	@GenericGenerator(name = "sequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "SEQ_BOOK") })
	private Long id;
	private String name;
	private String author;

	@ManyToOne(optional=true,targetEntity=Student.class)
	@JoinColumn(name="STUDENT_ID")
	private Student student;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
