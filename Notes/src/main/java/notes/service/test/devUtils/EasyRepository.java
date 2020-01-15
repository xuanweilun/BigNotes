/**@Title: RepositoryTest.java 
 * @Package com.brt.license.test.RepositoryTest 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xuanweilun 
 * @date 2019年8月26日 下午3:18:47 
 * @version V1.0   
 */

package notes.service.test.devUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/** 
 * @ClassName RepositoryCreator 
 * @Description Repository 自动创建工具
 * @date 2019年9月29日 
 */
public class EasyRepository {
	
	/*
	 * 使用方法：
	 * 	1.将本文件放入项目中
	 *  2.修改 @author（创建人）名、实体包、仓库包
	 *  3.运行本文件
	 *  4.刷新项目
	 *  
	 * */
	
	private static final String author = "xuanweilun";
	private static final String entityPackageName = "com.brt.xwl.entity";
	private static final String repositoryPackageName = "com.brt.xwl.repository";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		EasyRepository.startCreateRepositorys();
	}
	public static void startCreateRepositorys() {
		Long startTime = System.currentTimeMillis();
		System.out.println("\n-----------Repository Creator----------\n");
		
		System.out.println("    start time: "+new SimpleDateFormat("yyyy.MM.dd. HH:mm:SS").format(new Date(startTime)));
		//1.get filePath
		String src = System.getProperty("user.dir")+"\\src\\main\\java\\";
		//2.get entity path
		String entityPath = src + entityPackageName.replace(".", "\\");
		String repositoryPath = src + repositoryPackageName.replace(".", "\\");
		createRepositorys(entityPath,repositoryPath);
		Long endTime = System.currentTimeMillis();
		System.out.println("      end time: "+new SimpleDateFormat("yyyy.MM.dd. HH:mm:SS").format(new Date(endTime)));
		Long spareTime = endTime - startTime;
		System.out.println("  elapsed time: "+ spareTime + " ms");
		System.out.println("       numbers: "+ repositoryNum + " repositorys");
		System.out.println("        author: " + author);
		System.out.println("\n---------------------------------------");
	}
	private static void createRepositorys(String entityPath,String repositoryPath) {
		// get file list where the path has
		File dir = new File(entityPath);
		for (File file1:dir.listFiles()) {
			if (file1.isFile()) {
				String filePath = file1.getPath();
				createRepository(repositoryPath,filePath);
			} else if (file1.isDirectory()) {
				String repoPath = repositoryPath + file1.getPath().replace(entityPath,"");
				createRepositorys(file1.getPath(),repoPath);
			}
		}
	}
	private static void createRepository(String outputFilePath, String classPath) {
		//1.get value
		String packageName = outputFilePath.replace("\\","."); 
		packageName = packageName.substring(packageName.indexOf(repositoryPackageName),packageName.length());
		String classPackage = classPath.replace("\\","."); 
		classPackage = classPackage.substring(classPackage.indexOf(entityPackageName), classPackage.lastIndexOf("."));
		String classSimpleName = classPackage.substring(classPackage.lastIndexOf(".")+1,classPackage.length());
		//2.set content
		String contentCopy = new String(content);
		content = content.replace("className",classPackage);
		content = content.replace("packageName", packageName);
		content = content.replace("classSimpleName", classSimpleName);
		content = content.replace("time",time);
		content = content.replace("creator", author);
		//3.out put
		try {
			File file = new File(outputFilePath);
			if(!file.exists()) {
				file.mkdirs();
			}
			//
			file = new File(outputFilePath+"\\"+classSimpleName+"Repository.java");
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
		//
		repositoryNum++;
		content = contentCopy;
	}
	private static String 
	content = "package packageName;\n\n\n" 
			+ "import org.springframework.data.jpa.repository.JpaRepository;\n\n"
			+ "import className;\n\n\n\n" 
			+ "/**\n"
			+ " * @ClassName: classSimpleNameRepository\n"
			+ " * @Description: TODO(这里用一句话描述这个类的作用)\n"
			+ " * @author creator\n"
			+ " * @date time\n"
			+ " */\n"
			+ "public interface classSimpleNameRepository extends JpaRepository<classSimpleName, Long>{"
			+ "\n\n"
			+ "}";
	private static final String dateTime = new SimpleDateFormat("yyyy年MM月dd日 下午HH:mm:SS").format(new Date());
	private static final String time = dateTime.substring(0,dateTime.length()-1); 
	private static int repositoryNum;

}
