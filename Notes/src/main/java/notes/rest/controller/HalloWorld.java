package notes.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@Api(tags="HalloWorld")
public class HalloWorld {
	
	@Value("${aaa}/schemas/{schemaId}/criterias/{criteriaId}/disableInternalComment")
	private String api = "";
	private static Long address;
	@Value("${aaa}")
	private Long addresss;
//	@Value("${systemManagerId}")
//	private Long systemManager;
	/**
	 * 
	 * 2019年10月9日 上午10:23:23
	 */
	@RequestMapping(method=RequestMethod.GET,value="/say")
	@ApiOperation(value="",notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="path",name="id",value="",dataType="Long",required=true),
		@ApiImplicitParam(paramType="query",name="name",value="",dataType="Long",required=false)
		})
	public String sayHalloWorld(@PathVariable Long id,@RequestParam Long name) {
		System.out.println(api.replace("{schemaId}", new Long(1).toString()).replace("{criteriaId}", new Long(2).toString()));
		System.out.println(address);
		System.out.println(addresss);
		return address.toString();
	}
	@GetMapping
	@ApiOperation(value="",notes="")
	public String sayHalloWorl(@PathVariable Long id,@RequestParam Long name) {
		System.out.println(api.replace("{schemaId}", new Long(1).toString()).replace("{criteriaId}", new Long(2).toString()));
		System.out.println(address);
		System.out.println(addresss);
		return address.toString();
	}
	public static void main(String[] args) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
		
		System.out.println(String.format("attachment; filename=\"%s\"", "xuanweilun"));
	}
	

}
