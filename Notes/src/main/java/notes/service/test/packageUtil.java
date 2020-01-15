package notes.service.test;

import java.io.File;

import notes.RepositoryCreator;

public class packageUtil {
	
	public static void main(String[] args) {
		System.out.println(getByName("viewObject"));
	}

	public static String getByName(String pa2) {
		String projectDir = System.getProperty("user.dir");
		Package pa = RepositoryCreator.class.getPackage();
		String packagePath = pa.getName().replace(".", "\\");
		packagePath = projectDir+"\\src\\main\\java\\" + packagePath;
		getPackageAllName(packagePath,pa2);
		return packageUtil.packagePath;
	}
	
	private static String packagePath = "";
	private static String packageName = "";
	private static void getPackageAllName(String path,String packageName) {
		File file = new File(path);
		if(file.isFile()) {
			return ;
		}
		for(File sonFile:file.listFiles())
		{
			if(!sonFile.getPath().endsWith(packageName))
			{
				getPackageAllName(sonFile.getPath(),packageName);
			}
			else 
			{
				packagePath = sonFile.getPath();
				int beginIndex = packagePath.indexOf("\\src\\main\\java\\");
				String packPath = packagePath.replace("\\src\\main\\java\\", "");
				packageUtil.packageName = packPath.substring(beginIndex).replace("\\", ".");
				return;
			}
		}
	}
	
	public static String getPackageName(String paName) {
		return packageName;
	}
	
	
}
