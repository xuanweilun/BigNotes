package notes.service.test.devUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class EasyFile {

	/**
	 * @author xuanweilun   
	 * @date 2019年10月15日 上午9:15:18 
	 * @Description: 写内容到文件上
	 */
	public static void writeContentsToFile(File outputFile,String contents) {
		try {
			OutputStream out = new FileOutputStream(outputFile);
			out.write(contents.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println("文件写出失败！路径="+outputFile.getAbsolutePath());
			e.printStackTrace();
		}
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年10月15日 上午9:15:23 
	 * @Description: 从文件中读取内容
	 */
	public static String readContentsFromFile(File inputFile) {
		byte bs[] = null;
		try {
			InputStream in = new FileInputStream(inputFile);
			bs = new byte[(int)inputFile.length()]; 
			in.read(bs);
			in.close();
		} catch (IOException e) {
			System.err.println("文件读取失败！路径="+inputFile.getAbsolutePath());
			e.printStackTrace();
		}
		String content = new String(bs);
		return content;
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年12月19日 下午12:44:42 
	 * @Description: 获取指定路径下的所有文件。除了目录
	 */
	public static List<File> getAllFilesFromPath(String filePath){
		File dir = new File(filePath);
		List<File> files = new ArrayList<File>();
		if(!dir.isDirectory()) {
			files.add(dir);
			return files;
		}
		getFilesByDir(dir,files);
		return files;
	}

	/**
	 * @author xuanweilun   
	 * @date 2019年12月19日 下午12:44:47 
	 * @Description: 获取文件夹的所有文件
	 */
	private static void getFilesByDir(File dir, List<File> files) {
		for (File file:dir.listFiles()) {
			if(file.isDirectory()) {
				getFilesByDir(file,files);
			}else {
				files.add(file);
			}
		}
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年12月24日 下午3:24:45 
	 * @Description: 获得指定包名的file路径
	 */
	public static String getPackagePathFromName(String packageName) {
		String projectDir = System.getProperty("user.dir");
		String packagePath = packageName.replace(".", "\\");
		packagePath = projectDir+ "\\src\\main\\java\\" + packagePath;
		return packagePath;
	}
	
	
}
