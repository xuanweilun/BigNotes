
/**@Title: Course_Student_Relation.java 
 * @Package com.brt.license.entity.student_entity 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xwl 
 * @date 2019年4月18日 下午5:38:34 
 * @version V1.0   
 */
 
package notes.entity.student;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**@ClassName: Course_Student_Relation 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author xwl
 * @date 2019年4月18日 下午5:38:34  
 */

@Entity
@Table(name="T_COURSE_STUDENT",schema="BRTLIC")
public class Course_Student_Relation {
	@Id
	@GeneratedValue(generator="sequence_generator")
	@GenericGenerator(
			name="sequence_generator",
			strategy="org.hibernate.id.enhanced.SequenceStyleGenerator"
	)
	private long id;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="STUDENT_ID")
	private Student student;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="COURSE_ID")
	private Course course;
}
