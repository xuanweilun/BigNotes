package notes.service.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import notes.RepositoryCreator;

/** 
 * @ClassName ViewObjectSwaggerCreator 
 * @Description viewObject 自动添加Swagger註解
 * 工具
 * @date 2019年10月05日 
 */
public class ViewObjectSwaggerCreator {
	
	/**
	 * 操作步驟：
	 *  1.將本文件放入項目中
	 *  2.修改viewObjectPackageName
	 *  3.運行本文件
	 */
	private static String viewObjectPackageName = "student_entity";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Long startTime = System.currentTimeMillis();
		System.out.println("\n----- View Object Swagger Creator -----\n");
		
		System.out.println("     start time: "+new SimpleDateFormat("yyyy.MM.dd HH:mm:SS").format(new Date(startTime)));
		generaPackageName(viewObjectPackageName);
		generaSwaggerApi(packagePath);
		Long endTime = System.currentTimeMillis();
		System.out.println("       end time: "+new SimpleDateFormat("yyyy.MM.dd HH:mm:SS").format(new Date(endTime)));
		Double spareTime = (double) (endTime - startTime);
		System.out.println("   elapsed time: "+ spareTime/1000 + " s");
		System.out.println("    affected vo: "+ numbers + " numbers");
		System.out.println("\n---------------------------------------");
	}
	
	private static String apiModelImport = "import io.swagger.annotations.ApiModel;";
	private static String apiModelPropertyImport = "import io.swagger.annotations.ApiModelProperty;";
	private static String packagePath = "";
	private static String packageName = "";
	private static String projectPath = "\\src\\main\\java\\";
	private static int numbers = 0;
	
	public static void generaSwaggerApi(String packagePath) throws ClassNotFoundException, IOException {
		//1.get dir
		File file = new File(packagePath);
		if(!file.isDirectory())
			return;
		//2.get son dir or file
		for(File viewObjectFile:file.listFiles()) {
			//3.get packageName
			int beginIndex = packagePath.indexOf(projectPath);
			String packPath = packagePath.replace(projectPath, "");
			ViewObjectSwaggerCreator.packageName = packPath.substring(beginIndex).replace("\\", ".");
			//4.swagger son dir
			if(!viewObjectFile.isFile()) {
				generaSwaggerApi(viewObjectFile.getPath());
			}else {
				//5.swagger son file
				String contents = getContentsByFile(viewObjectFile);
				String contentsCopy = new String(contents);
				String className = packageName+"."+viewObjectFile.getName().replace(".java", "");
				Class<?> voClass = Class.forName(className);
				contents = addApiModelToClass(voClass,contents);
				contents = addApiModelPropertyToClassAttributes(voClass,contents);
				if(-1 == contentsCopy.indexOf(contents)) {
					outputNewViewObject(viewObjectFile,contents);
					numbers++;
				}
			}
		}
		
	}
	
	
	private static void outputNewViewObject(File viewObjectFile,String contents) throws IOException {
		OutputStream out = new FileOutputStream(viewObjectFile);
		out.write(contents.getBytes());
		out.flush();
		out.close();
	}


	private static String addApiModelPropertyToClassAttributes(Class<?> voClass, String contents) {
		if(-1 == contents.indexOf(apiModelPropertyImport))
		{
			contents = contents.replace(apiModelImport,apiModelImport+"\n"+apiModelPropertyImport);
		}
		Field fields[] = voClass.getDeclaredFields();
		for(Field field:fields) {
			if(!field.isAnnotationPresent(ApiModelProperty.class) && Modifier.isPrivate(field.getModifiers())) {
				Class<?> type = field.getType();
				Type fieldType = field.getGenericType();
				String typeSimepleName = "";
				try{//泛型属性
					ParameterizedType pt = (ParameterizedType)fieldType;
					Type t1 =  pt.getActualTypeArguments()[0];
					String simpleTypeName = t1.getTypeName().substring(t1.getTypeName().lastIndexOf(".")+1);
					typeSimepleName = type.getSimpleName()+ "<"+simpleTypeName+"> ";
				}catch(Exception e) {
					//普通类型
					typeSimepleName = type.getSimpleName()+ " ";
				}
				String oldField = "\tprivate " + typeSimepleName + field.getName();
				contents = contents.replace(oldField, "\t@ApiModelProperty(\"\")\n"+oldField);
			}
		}
		return contents;
	}


	private static String addApiModelToClass(Class<?> voClass, String contents) {
		if(-1 == contents.indexOf(apiModelImport)) {
			if(-1 != contents.indexOf(apiModelPropertyImport)) {
				contents = contents.replace(apiModelPropertyImport,apiModelImport+"\n"+apiModelPropertyImport);
			}else {
				int las = contents.lastIndexOf("import");
				if(-1 != las) {
					String contentCopy = contents.substring(las);
					int first = contentCopy.indexOf(";");
					String lastImport = contentCopy.substring(0,first+1);
					contents = contents.replace(lastImport,lastImport+"\n\n" + apiModelImport);
				}else {
					int first = contents.indexOf(";");
					String packageName = contents.substring(0,first+1);
					contents = contents.replace(packageName,packageName+"\n\n" + apiModelImport);
				}
				
			}
			
		}
		Boolean isApiModel = voClass.isAnnotationPresent(ApiModel.class);
		if(!isApiModel) {
				contents = contents.replace("public class "+voClass.getSimpleName(),
						"@ApiModel(\"\")"+"\npublic class "+voClass.getSimpleName());
			
		}
		return contents;
	}


	private static String getContentsByFile(File file) throws IOException {
		InputStream in = new FileInputStream(file);
		byte bs[] = new byte[(int)file.length()]; 
		in.read(bs);
		in.close();
		String content = new String(bs);
		return content;
	}
	
	public static String generaPackageName(String pa2) {
		String projectDir = System.getProperty("user.dir");
		Package pa = RepositoryCreator.class.getPackage();
		String packagePath = pa.getName().replace(".", "\\");
		packagePath = projectDir+projectPath + packagePath;
		getPackageAllName(packagePath,pa2);
		return ViewObjectSwaggerCreator.packagePath;
	}
	
	
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
				
				return;
			}
		}
	}
	
	
}
