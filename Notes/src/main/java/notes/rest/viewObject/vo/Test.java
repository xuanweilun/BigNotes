package notes.rest.viewObject.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Administrator
 *
 */
@ApiModel("测试vo")
public class Test {
	@ApiModelProperty("1")
	private Long id;
	@ApiModelProperty("")
	private int age;
	@ApiModelProperty("")
	private String name;
	@ApiModelProperty("")
	private List<TreeNode> node;

	public Long getId(Long id) {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge(int age) {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName(String name) {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeNode>  getNode(List<TreeNode>  node) {
		return this.node;
	}

	public void setNode(List<TreeNode>  node) {
		this.node = node;
	}
	
	
}
