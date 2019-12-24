package stx.annotation.baeldung;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectToJsonConverter {
    public String convertToJson(Object object) throws JsonSerializationException {
        try {
            checkIfSerializable(object);
            initializeObject(object);
            return getJsonString(object);
        } catch (Exception e) {
            throw new JsonSerializationException(e.getMessage());
        }
    }
    
	public void checkIfSerializable(Object obj) throws JsonSerializationException {
		if(Objects.isNull(obj)) {
			throw new JsonSerializationException("The object to serialize is null");
		}
		
		Class<?> clazz = obj.getClass();
		if(!clazz.isAnnotationPresent(JsonSerializable.class)) {
	        throw new JsonSerializationException("The class "
	                + clazz.getSimpleName() 
	                + " is not annotated with JsonSerializable");
		}
	}
	
	public void initializeObject(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = obj.getClass();
		for(Method method:clazz.getDeclaredMethods()) {
			if(method.isAnnotationPresent(Init.class)) {
				method.setAccessible(true);
				Annotation annotation = (Annotation)method.getAnnotation(Init.class);
				Init init = (Init)annotation;
				System.out.println(init.greeting());
				method.invoke(obj);
			}
		}
	}
	
	private String getJsonString(Object obj) throws Exception {
		Class<?> clazz = obj.getClass();
		Map<String, String> jsonElementsMap = new HashMap<>();
		for(Field field:clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(JsonElement.class)) {
				field.setAccessible(true);
				System.out.println(field.getName());
				jsonElementsMap.put(field.getName(), (String)field.get(obj));
			}
		}
	    String jsonString = jsonElementsMap.entrySet().stream()
	            .map(entry -> "\"" + entry.getKey() + "\":\""
	              + entry.getValue() + "\"")
	            .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
	}
}