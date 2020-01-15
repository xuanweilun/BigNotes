package notes.service.test.timerDemo;

public interface SchemaSwitchService {

	/**
	 * @author xuanweilun   
	 * @date 2019年11月11日 上午11:09:34 
	 * @Title: save 
	 * @Description: 保存周期
	 * @param schemaId
	 * @param cycle void
	 * @throws
	 */
	void save(Long schemaId,Cycle cycle);
	
	/**
	 * @author xuanweilun   
	 * @date 2019年11月11日 上午11:09:46 
	 * @Title: update 
	 * @Description: 修改周期
	 * @param schemaId
	 * @param cycle void
	 * @throws
	 */
	void update(Long schemaId,Cycle cycle);
	
}
