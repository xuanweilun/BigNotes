package notes.service.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/** 
 * @ClassName ViewObjectSwaggerCreator 
 * @Description viewObject 自动添加Swagger註解工具
 * @date 2019年10月05日 
 */
public class EasyControllerSwagger {
	
	/*
	 * 操作步驟：
	 *  1.將本文件放入項目中
	 *  2.修改viewObjectPackageName
	 *  3.運行本文件
	 */
	private static String controllerPackageName = "notes.service.test.devUtils";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Long startTime = System.currentTimeMillis();
		System.out.println("\n----- View Object Swagger Creator -----\n");
		
		System.out.println("     start time: "+new SimpleDateFormat("yyyy.MM.dd HH:mm:SS").format(new Date(startTime)));
		generaPackageName();
		generaSwaggerApi(packagePath);
		Long endTime = System.currentTimeMillis();
		System.out.println("       end time: "+new SimpleDateFormat("yyyy.MM.dd HH:mm:SS").format(new Date(endTime)));
		Double spareTime = (double) (endTime - startTime);
		System.out.println("   elapsed time: "+ spareTime/1000 + " s");
		System.out.println("    affected vo: "+ numbers + " numbers");
		System.out.println("\n---------------------------------------");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static final String projectPath = "\\src\\main\\java\\";
	private static String packagePath = "";
	private static String packageName = "";
	private static int numbers = 0;
	
	public static void generaSwaggerApi(String filePath) throws ClassNotFoundException, IOException {
		//1.get dir
		File file = new File(filePath);
		if(file.isDirectory())
		{
			//2.get son dir or file
			for(File controllerFile:file.listFiles()) {
				//3.get packageName
				int beginIndex = filePath.indexOf(projectPath);
				String packPath = filePath.replace(projectPath, "");
				packageName = packPath.substring(beginIndex).replace("\\", ".");
				//4.swagger son direct
				generaSwaggerApi(controllerFile.getPath());
			}
		}else{
			String contents = getContentsByFile(file);
			String contentsCopy = new String(contents);
			String className = packageName+"."+file.getName().replace(".java", "");
			Class<?> voClass = Class.forName(className);
			boolean isController = voClass.isAnnotationPresent(Controller.class);
			boolean isRestController = voClass.isAnnotationPresent(RestController.class);
			if(isController || isRestController) {
				contents = addApiToClass(voClass,contents);
				contents = addApiOperationToMethods(voClass,contents);
				contents = addApiImplicitParamToMethod(voClass,contents);
				contents = updateSwaggerApiImport(contents);
				if(-1 == contentsCopy.indexOf(contents)) {
					outputNewViewObject(file,contents);
					numbers++;
				}
			}
		}
		
		
	}
	
	private static final String swaggerAnnotationImport = "import io.swagger.annotations";
	private static final String springWebBindAnnotationImport = "import org.springframework.web.bind.annotation";
	
	private static final String apiImport = "import io.swagger.annotations.Api;";
	private static final String apiOperationImport = "import io.swagger.annotations.ApiOperation;";
	private static final String apiImplicitParamImport = "import io.swagger.annotations.ApiImplicitParam;";
	private static final String apiImplicitParamsImport = "import io.swagger.annotations.ApiImplicitParams;";
	private static final String pathVariableImport = "import org.springframework.web.bind.annotation.PathVariable;";
	private static final String requestParamImport = "import org.springframework.web.bind.annotation.RequestParam;";
	
	private static final String api = "@Api";
	private static final String apiOperation = "@ApiOperation";
	private static final String apiImplicitParam = "@ApiImplicitParam";
	private static final String apiImplicitParams = "@ApiImplicitParams";
	private static final String pathVariable = "@PathVariable";
	private static final String requestParam = "@RequestParam";
	
	private static String updateSwaggerApiImport(String contents) {
		//1.update apiImport
		contents = updateImport(contents,apiImport,api,swaggerAnnotationImport);
		//2.update apiOperationImport
		contents = updateImport(contents,apiOperationImport,apiOperation,swaggerAnnotationImport);
		//3.update apiImplicitParamImport
		contents = updateImport(contents,apiImplicitParamImport,apiImplicitParam,swaggerAnnotationImport);
		//4.update apiImplicitParamsImport
		contents = updateImport(contents,apiImplicitParamsImport,apiImplicitParams,swaggerAnnotationImport);
		//5.update pathVariableImport
		contents = updateImport(contents,pathVariableImport,pathVariable,springWebBindAnnotationImport);
		//6.update requestParamImport
		contents = updateImport(contents,requestParamImport,requestParam,springWebBindAnnotationImport);
		return contents;
	}

	private static String updateImport(String contents,String importFile,String annotation,String importType) {
		int apiOperationImportIndex = contents.indexOf(importFile);
		int apiOperationIndex = contents.indexOf(annotation);
		//if api not exist and import exsit 
		if(-1 == apiOperationIndex && -1 != apiOperationImportIndex) {
			contents = contents.replace(importFile,"");
		}
		//if api exist and import not exist add import
		if(-1 != apiOperationIndex && -1 == apiOperationImportIndex) {
			String lastSwaggerImport = getLastSwaggerImport(contents,importType);
			//if
			if(null != lastSwaggerImport) {
				contents = contents.replace(lastSwaggerImport,lastSwaggerImport+"\n"+importFile);
			}else {
				int indexOfPackageEnd = contents.indexOf(";");
				if(-1 != indexOfPackageEnd) {
					String packageName = contents.substring(0,indexOfPackageEnd+1);
					contents = contents.replace(packageName,packageName+"\n\n" + importFile);
				}
				
			}
		}
		return contents;
	}

	private static String getLastSwaggerImport(String contents, String importType) {
		int lastImportIndex = contents.lastIndexOf((importType));
		if(-1 == lastImportIndex) {
			return null;
		}
		String subContent = contents.substring(lastImportIndex);
		int indexOfend = subContent.indexOf(";");
		String lastSwaggerImport = subContent.substring(0,indexOfend+1);
		return lastSwaggerImport;
	}


	private static String addApiImplicitParamToMethod(Class<?> voClass, String contents) {
		for(Method method:voClass.getDeclaredMethods()) 
		{
			if(!method.isAnnotationPresent(RequestMapping.class))
			{
				continue;
			}
			//no swagger annotation to method
			if(!method.isAnnotationPresent(ApiImplicitParams.class) && !method.isAnnotationPresent(ApiImplicitParam.class)) 
			{
				contents = insertApiImplicitParamToMethod(method,contents);
			}
			// one swagger annotation to method
			else if(!method.isAnnotationPresent(ApiImplicitParams.class) && method.isAnnotationPresent(ApiImplicitParam.class)) 
			{
				contents = updateApiImplicitParamToMethod(method,contents);
			}
			// many param annotation to method
			else if(method.isAnnotationPresent(ApiImplicitParams.class) && !method.isAnnotationPresent(ApiImplicitParam.class))
			{
				contents = updateApiImplicitParamToMethodForParams(method,contents);
			}
			
		}
		return contents;
	}



	private static String updateApiImplicitParamToMethodForParams(Method method, String contents) {
		Parameter[] parameters = method.getParameters();
		Map<String,Parameter> parameterMap = getPathVariableOrRequestParam(parameters);
		//1.del all params
		if(0 == parameterMap.size())
		{
			contents = deleteImplictiParams(method,contents);
		}
		//2.keep one param
		else if(1 == parameterMap.size()) 
		{
			Parameter parameter = parameters[0];
			ApiImplicitParam api = method.getAnnotation(ApiImplicitParam.class);
			String paramName = parameter.getName();
			String parameterType = getParameterTypeName(parameter);
			boolean isPathVariable = parameter.isAnnotationPresent(PathVariable.class);
			boolean isRequestParam = parameter.isAnnotationPresent(RequestParam.class);
			String apiName = api.name();
			String apiType = api.dataType();
			String apiParamType = api.paramType();
			String apiIm = getImplicitParamFromParams(method,parameter,contents);
			String apiImCopy = new String(apiIm);
			apiIm = apiIm.replace("name=\""+apiName+"\"","name=\""+paramName+"\"");
			apiIm = apiIm.replace("dataType=\""+apiType+"\"","dataType=\""+parameterType+"\"");
			if(isPathVariable) {
				apiIm = apiIm.replace("paramType=\""+apiParamType+"\"","paramType=\"path\"");
			}
			if(isRequestParam) {
				apiIm = apiIm.replace("paramType=\""+apiParamType+"\"","paramType=\"query\"");
			}
			contents = contents.replace(apiImCopy, apiIm);
		}
		//3.update params
		else 
		{
			
		}
		
		return null;
	}

	private static String getImplicitParamFromParams(Method method, Parameter parameter, String contents) {
		//1.get ApiImplicitParams
		String returnType = getReturnType(method);
		String oldMethod = "\tpublic " + returnType + method.getName();
		int oldMethodIndex = contents.lastIndexOf(oldMethod);
		String contentFirst = contents.substring(0,oldMethodIndex);
		String apis =  contentFirst.substring(contentFirst.lastIndexOf("@ApiImplicitParams"),contentFirst.length()-1);
		//2.get apiImplicitParam
		String paramName = parameter.getName();
		int indexOfName = apis.indexOf("name=\""+paramName+"\"");
		String apisHead = apis.substring(0,indexOfName);
		apisHead = apisHead.substring(apisHead.lastIndexOf("@ApiImplicitParam"));
		String apisTail = apis.substring(indexOfName+("name=\""+paramName+"\"").length(),apis.length());
		apisTail = apisTail.substring(apisTail.indexOf(")"));
		return apisHead+apisTail;
	}

	private static String deleteImplictiParams(Method method, String contents) {
		String methodTypeName = getReturnType(method);
		String oldMethod = "\tpublic " + methodTypeName + method.getName();
		int oldMethodIndex = contents.lastIndexOf(oldMethod);
		String contentFirst = contents.substring(0,oldMethodIndex);
		String contentLast = contents.substring(oldMethodIndex+1,contents.length());
		contentFirst = contentFirst.substring(0,contentFirst.lastIndexOf("@ApiImplicitParams"));
		contents = contentFirst + contentLast;
		return contents;
	}

	private static String updateApiImplicitParamToMethod(Method method, String contents) {
		Parameter[] parameters = method.getParameters();
		Map<String,Parameter> parameterMap = getPathVariableOrRequestParam(parameters);
		//1.del parameter
		if(0 == parameterMap.size()){
			contents = deleteImplictiParam(method,contents);
		}
		//2.update parameter
		else if(1 == parameterMap.size()) {
			Parameter parameter = parameters[0];
			ApiImplicitParam api = method.getAnnotation(ApiImplicitParam.class);
			String paramName = parameter.getName();
			String parameterType = getParameterTypeName(parameter);
			boolean isPathVariable = parameter.isAnnotationPresent(PathVariable.class);
			boolean isRequestParam = parameter.isAnnotationPresent(RequestParam.class);
			String apiName = api.name();
			String apiType = api.dataType();
			String apiParamType = api.paramType();
			String apiIm = getImplicitParam(method,contents);
			String apiImCopy = new String(apiIm);
			apiIm = apiIm.replace("name=\""+apiName+"\"","name=\""+paramName+"\"");
			apiIm = apiIm.replace("dataType=\""+apiType+"\"","dataType=\""+parameterType+"\"");
			if(isPathVariable) {
				apiIm = apiIm.replace("paramType=\""+apiParamType+"\"","paramType=\"path\"");
			}
			if(isRequestParam) {
				apiIm = apiIm.replace("paramType=\""+apiParamType+"\"","paramType=\"query\"");
			}
			contents = contents.replace(apiImCopy, apiIm);
		}else {//3.add parameter
			ApiImplicitParam api = method.getAnnotation(ApiImplicitParam.class);
			String methodTypeName = getReturnType(method);
			String oldMethod = "\tpublic " + methodTypeName + method.getName();
			String oldApiImplicitParam = getImplicitParam(method,contents);
			contents = deleteImplictiParam(method,contents);
			List<String> parameterApis = new ArrayList<String>();
			for(Parameter parameter :parameters) {
				if(parameter.isAnnotationPresent(RequestBody.class) || parameter.getName().equals(api.name())) 
					continue;
				//one path or query parameter
				String parameterApi = getParameterApi(parameter);
				parameterApis.add(parameterApi);
			}
			String parameterApiss = "\t@ApiImplicitParams({\n";
			parameterApiss = parameterApiss + "\t\t" +oldApiImplicitParam + ",\n";
			for(String parameterApi:parameterApis) {
				parameterApiss = parameterApiss +"\t"+ parameterApi + ",\n";
			}
			parameterApiss = parameterApiss.substring(0,parameterApiss.lastIndexOf(",")) + "\n\t\t})";
			
			contents = contents.replace(oldMethod,parameterApiss +"\n"+ oldMethod);
		}
		return contents;
	}


	private static Map<String,Parameter> getPathVariableOrRequestParam(Parameter[] parameters) {
		Map<String,Parameter> parameterMap = new HashMap<String,Parameter>();
		for(Parameter parameter:parameters) {
			if(!parameter.isAnnotationPresent(RequestBody.class)) {
				parameterMap.put(parameter.getName(),parameter);
			}
		}
		return parameterMap;
	}


	private static String getImplicitParam(Method method, String contents) {
		String methodTypeName = getReturnType(method);
		String oldMethod = "\tpublic " + methodTypeName + method.getName();
		int oldMethodIndex = contents.lastIndexOf(oldMethod);
		String contentFirst = contents.substring(0,oldMethodIndex);
		String apis =  contentFirst.substring(contentFirst.lastIndexOf("@ApiImplicitParam"),contentFirst.length()-1);
		return apis;
	}


	private static String deleteImplictiParam(Method method, String contents) {
		String methodTypeName = getReturnType(method);
		String oldMethod = "\tpublic " + methodTypeName + method.getName();
		int oldMethodIndex = contents.lastIndexOf(oldMethod);
		String contentFirst = contents.substring(0,oldMethodIndex);
		String contentLast = contents.substring(oldMethodIndex+1,contents.length());
		contentFirst = contentFirst.substring(0,contentFirst.lastIndexOf("@ApiImplicitParam"));
		contents = contentFirst + contentLast;
		return contents;
	}
	


	private static String insertApiImplicitParamToMethod(Method method, String contents) {
		Parameter[] parameters = method.getParameters();
		//no parameter
		if(0 == parameters.length)
		{
			return contents;
		}
		//one requestBody parameter
		String methodTypeName = getReturnType(method);
		String oldMethod = "\tpublic " + methodTypeName + method.getName();
		if(1 == parameters.length)
		{
			Parameter parameter = parameters[0];
			boolean isRequestBodyAnnotation = parameter.isAnnotationPresent(RequestBody.class);
			if(isRequestBodyAnnotation) return contents;
			//one path or query parameter
			String parameterApi = getParameterApi(parameter);
			contents = contents.replace(oldMethod,parameterApi +"\n"+ oldMethod);
			return contents;
		}else {
			List<String> parameterApis = new ArrayList<String>();
			for(Parameter parameter :parameters) {
				if(parameter.isAnnotationPresent(RequestBody.class)) 
					continue;
				//one path or query parameter
				String parameterApi = getParameterApi(parameter);
				parameterApis.add(parameterApi);
			}
			if(1 == parameterApis.size()) {
				contents = contents.replace(oldMethod,parameterApis.remove(0) +"\n"+ oldMethod);
			} else if(parameterApis.size()>1) {
				String parameterApiss = "\t@ApiImplicitParams({\n";
				for(String parameterApi:parameterApis) {
					parameterApiss = parameterApiss +"\t"+ parameterApi + ",\n";
				}
				parameterApiss = parameterApiss.substring(0,parameterApiss.lastIndexOf(",")) + "\n\t\t})";
				
				contents = contents.replace(oldMethod,parameterApiss +"\n"+ oldMethod);
			}
			
		}
		return contents;
	}


	private static String getParameterApi(Parameter parameter) {
		boolean isPathVariablePresent = parameter.isAnnotationPresent(PathVariable.class);
		boolean isRequestParamPresent = parameter.isAnnotationPresent(RequestParam.class);
		String parameterType = getParameterTypeName(parameter);
		String parameterApi = "";
		if(isPathVariablePresent) {
			parameterApi = "\t@ApiImplicitParam(paramType=\"path\","
					+ "name=\""+parameter.getName()+"\",value=\"\""
					+ ",dataType=\""+parameterType+"\",required=true)";
		}else if(isRequestParamPresent) {
			parameterApi = "\t@ApiImplicitParam(paramType=\"query\","
					+ "name=\""+parameter.getName()+"\",value=\"\""
					+ ",dataType=\""+parameterType+"\",required=false)";
		}
		return parameterApi;
		
	}


	private static String getParameterTypeName(Parameter parameter) {
		Class<?> type = parameter.getType();
		Type fieldType = parameter.getParameterizedType();
		String typeSimepleName = "";
		try{//泛型属性
			ParameterizedType pt = (ParameterizedType)fieldType;
			Type t1 =  pt.getActualTypeArguments()[0];
			String simpleTypeName = t1.getTypeName().substring(t1.getTypeName().lastIndexOf(".")+1);
			typeSimepleName = type.getSimpleName()+ "<"+simpleTypeName+"> ";
		}catch(Exception e) {
			//普通类型
			typeSimepleName = type.getSimpleName();
		}
		return typeSimepleName;
	}


	private static String getReturnType(Method method) {
		Class<?> type = method.getReturnType();
		Type fieldType = method.getGenericReturnType();
		String typeName = "";
		try{//泛型属性
			ParameterizedType pt = (ParameterizedType)fieldType;
			Type t1 =  pt.getActualTypeArguments()[0];
			typeName = t1.getTypeName().substring(t1.getTypeName().lastIndexOf(".")+1);
			typeName = type.getSimpleName()+ "<"+typeName+"> ";
		}catch(Exception e) {
			//普通类型
			typeName = type.getSimpleName()+ " ";
		}
		return typeName;
	}


	private static void outputNewViewObject(File viewObjectFile,String contents) throws IOException {
		OutputStream out = new FileOutputStream(viewObjectFile);
		out.write(contents.getBytes());
		out.flush();
		out.close();
	}


	private static String addApiOperationToMethods(Class<?> voClass, String contents) {
		Method[] methods = voClass.getDeclaredMethods();
		//1.import apiOperation 
		if(-1 == contents.indexOf(apiOperationImport))
		{
			boolean canAddApiOperationImport = false;
			for(Method method:methods) {
				if(method.isAnnotationPresent(RequestMapping.class)) {
					canAddApiOperationImport = true;
					break;
				}
			}
			if(true == canAddApiOperationImport) {
				contents = contents.replace(apiImport,apiImport+"\n"+apiOperationImport);
			}else {
				return contents;
			}
		}
		//2.add apiOperation to method
		for(Method method:methods) {
			if(!method.isAnnotationPresent(ApiOperation.class) && method.isAnnotationPresent(RequestMapping.class)) {
				Class<?> type = method.getReturnType();
				Type fieldType = method.getGenericReturnType();
				String typeName = "";
				try{//泛型属性
					ParameterizedType pt = (ParameterizedType)fieldType;
					Type t1 =  pt.getActualTypeArguments()[0];
					typeName = t1.getTypeName().substring(t1.getTypeName().lastIndexOf(".")+1);
					typeName = type.getSimpleName()+ "<"+typeName+"> ";
				}catch(Exception e) {
					//普通类型
					typeName = type.getSimpleName();
				}
				String oldMethod = "\tpublic " + typeName+ " " + method.getName();
				if(method.isAnnotationPresent(ApiImplicitParams.class)) {
					String contentsCopy = contents.substring(0,contents.indexOf(oldMethod));
					oldMethod =  contentsCopy.substring(contentsCopy.lastIndexOf(apiImplicitParams)) + oldMethod;
				}
				else if(method.isAnnotationPresent(ApiImplicitParam.class) && method.isAnnotationPresent(ApiImplicitParams.class)) {
					String contentsCopy = contents.substring(0,contents.indexOf(oldMethod));
					oldMethod =  contentsCopy.substring(contentsCopy.lastIndexOf(apiImplicitParams)) + oldMethod;
				}
				else if(method.isAnnotationPresent(ApiImplicitParam.class) && !method.isAnnotationPresent(ApiImplicitParams.class)) {
					String contentsCopy = contents.substring(0,contents.indexOf(oldMethod));
					oldMethod =  contentsCopy.substring(contentsCopy.lastIndexOf(apiImplicitParam)-1) + oldMethod;
				}else {
					
				}
				contents = contents.replace(oldMethod, "\t@ApiOperation(value=\"\",notes=\"\")\n"+oldMethod);
			}
		}
		return contents;
	}


	private static String addApiToClass(Class<?> controllerClass, String contents) {
		
		Boolean isApi = controllerClass.isAnnotationPresent(Api.class);
		if(!isApi) {
				contents = contents.replace("public class "+controllerClass.getSimpleName(),
						"@Api(tags=\""+controllerClass.getSimpleName()+"\")"+"\npublic class "+controllerClass.getSimpleName());
			
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
	
	public static String generaPackageName() {
		String projectDir = System.getProperty("user.dir");
		packagePath = controllerPackageName.replace(".", "\\");
		packagePath = projectDir+projectPath + packagePath;
		return EasyControllerSwagger.packagePath;
	}
	
	
	
	
}
