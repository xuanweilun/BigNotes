package notes.rest.viewObject.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * @author Administrator
 *
 */
@ApiModel("")
public class TreeNode {
	@ApiModelProperty("")
	private Long parentId;
	@ApiModelProperty("")
	private String name;
	@ApiModelProperty("")
	private List<TreeNode> children;


	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	
}
