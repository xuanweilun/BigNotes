/**@Title: RepositoryTest.java 
 * @Package com.brt.license.test.RepositoryTest 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xuanweilun 
 * @date 2019年8月26日 下午3:18:47 
 * @version V1.0   
 */

package notes.service.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @ClassName: RepositoryTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author xuanweilun
 * @date 2019年8月26日 下午3:18:47
 */

public class RepositoryCreator {
	
	private static final String author = "xuanweilun";
	

	public static void main(String[] args) {
		createRepositorys(entityDir,repositoryDir);
		
	}
	

	private static void createRepositorys(String entityPath,String repositoryPath) {
		if (null == entityPath || "".equals(entityPath)) {
			System.err.println("目录不能为空");
			return;
		}
		// get file list where the path has
		File dir = new File(entityPath);
		for (File file1:dir.listFiles()) {
			if (file1.isFile()) {
				String classAllName = file1.getPath();
				outputRepository(repositoryPath,classAllName);
			} else if (file1.isDirectory()) {
				String repoPath = repositoryPath + file1.getPath().replace(entityPath,"");
				createRepositorys(file1.getPath(),repoPath);
			}
		}
	}

	private static void outputRepository(String outputFilePath, String classAllName) {
		if (null == outputFilePath || "".equals(outputFilePath)) {
			System.err.println("目录不能为空");
			return;
		}
		
		String packageName = outputFilePath.replace("\\",".");
		packageName = packageName.substring(packageName.indexOf(".com") + 1,packageName.length());
		classAllName = classAllName.substring(classAllName.indexOf("\\com") + 1, classAllName.lastIndexOf(".")).replace("\\", ".");
		String className = classAllName.substring(classAllName.lastIndexOf(".")+1,classAllName.length());
		
		content = content.replace("TargetEntityPackagePath",classAllName);
		content = content.replace("packageName", packageName);
		content = content.replace("TargetEntity", className);
		content = content.replace("dateTime",dateTime);
		content = content.replace("author", author);
		try {
			File file = new File(outputFilePath);
			
			if(!file.exists()) {
				file.mkdirs();
			}
			file = new File(outputFilePath+"\\"+className+"Repository.java");
			OutputStream out = new FileOutputStream(file);
			
			try {
				out.write(content.getBytes());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}
	
	private static String content = "package packageName;\n\n\n" 
			+"import org.springframework.data.jpa.repository.JpaRepository;\n\n"
			+ "import TargetEntityPackagePath;\n\n\n\n" 
			+"/**\n"
			+ " * @ClassName: TargetEntityRepository\n"
			+ " * @Description: TODO(这里用一句话描述这个类的作用) \n"
			+ " * @author author \n"
			+ " * @date dateTime \n"
			+ " */\n"
			+"public interface TargetEntityRepository extends JpaRepository<TargetEntity, Long>{\n\n}";
	
	private static final SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd日 下午HH:mm:SS");
	private static String dateTime = s.format(new Date());
	
	// This is the path where the file's name you want to take.
	private static String entityDir = "D:\\Program Files (x86)\\sts\\sts-3.5.0.RELEASE\\Notes\\src\\main\\java\\notes\\entity";
	
	private static String repositoryDir = "D:\\Program Files (x86)\\sts\\sts-3.5.0.RELEASE\\Notes\\src\\main\\java\\notes\\repository";

}
