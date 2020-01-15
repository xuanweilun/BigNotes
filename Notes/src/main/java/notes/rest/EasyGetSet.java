package notes.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EasyGetSet {
	
	/*
	 * 
	 */
	private static String pojoPackageName = "notes.rest.viewObject";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static int numbers = 0;
	private static String packagePath = "";
	private static String projectPath = "\\src\\main\\java\\";
	private static String packageName = "";
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Long startTime = System.currentTimeMillis();
		getDirect();
		startBuildGetSet(packagePath);
		System.out.println("     start time: "+new SimpleDateFormat("yyyy.MM.dd HH:mm:SS").format(new Date(startTime)));
		Long endTime = System.currentTimeMillis();
		System.out.println("       end time: "+new SimpleDateFormat("yyyy.MM.dd HH:mm:SS").format(new Date(endTime)));
		Double spareTime = (double) (endTime - startTime);
		System.out.println("   elapsed time: "+ spareTime/1000 + " s");
		System.out.println("    affected vo: "+ numbers + " numbers");
		System.out.println("\n---------------------------------------");
	}

	
	
	private static void startBuildGetSet(String path) throws IOException, ClassNotFoundException {
		//1.get direct
		
		//2.
		File file = new File(path);
		if(!file.isDirectory())
			return;
		//2.get son dir or file
		for(File pojoFile:file.listFiles()) {
			//3.get packageName
			int beginIndex = path.indexOf(projectPath);
			String packPath = path.replace(projectPath, "");
			packageName = packPath.substring(beginIndex).replace("\\", ".");
			//4.swagger son dir
			if(!pojoFile.isFile()) {
				path = pojoFile.getPath();
				startBuildGetSet(path);
			}else {
				//5.swagger son file
				String contents = getContentsByFile(pojoFile);
				String contentsCopy = new String(contents);
				String className = packageName+"."+pojoFile.getName().replace(".java", "");
				Class<?> pojoClass = Class.forName(className);
				contents = deleteOldGetSet(pojoClass,contents);
				contents = addNewGetSet(pojoClass,contents);
				if(-1 == contentsCopy.indexOf(contents)) {
					outputFile(pojoFile,contents);
					numbers++;
				}
			}
		}
	}
	
	
	
	private static String addNewGetSet(Class<?> pojoClass, String contents) {
		
		Field [] fields = pojoClass.getDeclaredFields();
		Map<String,String> methodMap = new HashMap<String,String>();
		
		for(Method method:pojoClass.getMethods()) {
			methodMap.put(method.getName(), method.getName());
		}
		
		for(Field field:fields) {
			String fieldName = field.getName();
			Class<?> type = field.getType();
			Type fieldType = field.getGenericType();
			String typeName = "";
			if(-1 != type.getTypeName().indexOf("."))
			{
				int lastIndex = type.getTypeName().lastIndexOf(".");
				
				try {
					ParameterizedType pt = (ParameterizedType)fieldType;
					Type t1 =  pt.getActualTypeArguments()[0];
					String simpleTypeName = t1.getTypeName().substring(t1.getTypeName().lastIndexOf(".")+1);
					typeName = type.getSimpleName()+ "<"+simpleTypeName+"> ";
				}catch(Exception e) {
					typeName = type.getTypeName().substring(lastIndex + 1);
				}
			}else {
				typeName = type.getTypeName();
			}
			String setFieldName = String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
			String b = methodMap.get("get"+setFieldName);
			if(null == b || b.equals("")) {
				contents = configureGetMethod(fieldName,setFieldName,typeName,contents);
			}
			b = methodMap.get("set"+setFieldName);
			if(null == b || b.equals("")) {
//				contents = getGetMethod(fieldName,setFieldName,typeName,contents);
			}
			
		}
		
		return contents;
	}
	
	



	



	private static String getGetMethod(String fieldName, String setFieldName,String typeName, String contents,String getMethod) {
		String setMethod = "\tpublic void set" + setFieldName+ "("+typeName+" "+fieldName+") {\n"
				+ "\t\tthis." + fieldName + " = " + fieldName + ";\n"
				+ "\t}";
		
		contents = contents.replace(getMethod, getMethod +"\n\n"+setMethod);
		System.out.println(contents);
		
		
		return contents;
	}



	private static String configureGetMethod(String fieldName ,String setFieldName, String typeName, String contents) {
		String getMethod = "\tpublic "+typeName+" get" + setFieldName+ "("+typeName+" "+fieldName+") {\n"
				+ "\t\treturn this." + fieldName + ";\n"
				+ "\t}";
		int lastSetIndex = contents.lastIndexOf("public void set");
		
		if(-1 != lastSetIndex) {
			String contentsCopy = contents.substring(lastSetIndex);
			int lastSet = contentsCopy.indexOf("}");
			String lastSetMethod = contentsCopy.substring(0,lastSet+1);
			contents = contents.replace(lastSetMethod, lastSetMethod + "\n\n" + getMethod);
		}else {
			int lastPrivateField = contents.lastIndexOf("private");
			String contentsCopy = contents.substring(lastPrivateField);
			int lastSet = contentsCopy.indexOf(";");
			String lastSetMethod = contentsCopy.substring(0,lastSet+1);
			contents = contents.replace(lastSetMethod, lastSetMethod + "\n\n" + getMethod);
		}
		
		return contents;
	}



	private static String deleteOldGetSet(Class<?> pojoClass, String contents) {
		// TODO Auto-generated method stub
		return contents;
	}



	private static void outputFile(File file,String contents) throws IOException {
		OutputStream out = new FileOutputStream(file);
		out.write(contents.getBytes());
		out.flush();
		out.close();
	}
	
	
	private static String getContentsByFile(File file) throws IOException {
		InputStream in = new FileInputStream(file);
		byte bs[] = new byte[(int)file.length()]; 
		in.read(bs);
		in.close();
		String content = new String(bs);
		return content;
	}
	
	
	public static void getDirect() {
		String projectDir = System.getProperty("user.dir");
		packagePath = pojoPackageName.replace(".", "\\");
		packagePath = projectDir+projectPath + packagePath;
	}

}
