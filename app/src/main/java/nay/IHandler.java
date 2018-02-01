package nay;

import java.util.*;
import java.lang.reflect.*;
import java.security.*;

public final class IHandler
{
	public static class IMethod
	{
		public final String className;
		public final String index;
		public final List<String> parameterTypes = new ArrayList<>();
		
		private final Method function;
		
		public IMethod(String className, String index, String[] parameterTypes) {
			for (String in : parameterTypes)
				if (CLASSES.containsKey(in))
					this.parameterTypes.add(CLASSES.get(in).getClassName());
				else
					this.parameterTypes.add(in);
			
			function = Invoke.getMethod(
				CLASSES.containsKey(this.className = className) ?
					CLASSES.get(className).getClassName() : className,
				this.index = index,
				this.parameterTypes.toArray(new String[] {})
			);
		}

		public Object invoke(String pointer, Object...params) {
			if (IHandler.getFieldLookupMap().containsKey(pointer))
				return invoke(IHandler.getFieldLookupMap().get(pointer), params);
			return null;
		}
		
		public Object invoke(Object pointer, Object...params) {
			try {
				return function.invoke(pointer, params);
			}
			catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {}
			return null;
		}
		
		public Method getFunction() {
			return function;
		}
	}
	
	private static final Map<String, Warpper> CLASSES = new HashMap<>();
	
	public static void registerClass(String index, String source) {
		IHandler.CLASSES.put(index, new Warpper(source));
	}
	
	public static Map<String, Warpper> getClassLookupMap() {
		return CLASSES;
	}
	
	public static void injectField(String index, String field, Object value, Object receiver) {
		try {
			Invoke.getField(index, field).set(receiver, value);
		}
		catch (IllegalArgumentException | IllegalAccessException e) {}
	}
	
	private static final Map<String, Object> STATIC_FIELDS = new HashMap<>();
	
	public static void registerField(String index, String source, Object pointer) {
		String[] temp = source.split("\\.");
		if (temp.length == 2)
			IHandler.STATIC_FIELDS.put(index, Invoke.getMember(temp[0], temp[1], pointer));
	}
	
	public static Map<String, Object> getFieldLookupMap() {
		return STATIC_FIELDS;
	}
	
	private static final Map<String, IMethod> STATIC_METHODS = new HashMap<>();
	
	public static void registerMethod(String index, String source, String... parameterTypes) {
		String[] temp = source.split("\\.");
		if (temp.length == 2)
			IHandler.STATIC_METHODS.put(index, new IHandler.IMethod(temp[0], temp[1], parameterTypes));
	}
	
	public static Map<String, IMethod> getMethodLookupMap() {
		return STATIC_METHODS;
	}
}
