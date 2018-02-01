package nay;

import java.lang.reflect.*;

public class Invoke
{
	public static final Class<?> VOID = void.class;
	public static final Class<?> BYTE = byte.class;
	public static final Class<?> CHAR = char.class;

	public static final Class<?> SHORT = short.class;
	public static final Class<?> INT = int.class;
	public static final Class<?> FLOAT = float.class;
	public static final Class<?> DOUBLE = double.class;
	public static final Class<?> LONG = long.class;
	public static final Class<?> BOOLEAN = boolean.class;

	public static Object getNullPointer() {
		return null;
	}

	public static Object[] parser(String types, String vals) {
		try {
			Class<?>[] types_ = toClasses(parameter(types));
			String[] vals_ = parameter(vals);
			Object[] stack = new Object[types_.length];
			for (int i = 0; i < types_.length; ++i) {
				switch (types_[i].toString()) {
//					case "byte":params[i] = BYTE;break;
//					case "char":params[i] = CHAR;break;
					case "short":stack[i] = Short.valueOf(vals_[i]);break;
					case "int":stack[i] = Integer.valueOf(vals_[i]);break;
					case "float":stack[i] = Float.valueOf(vals_[i]);break;
					case "double":stack[i] = Double.valueOf(vals_[i]);break;
					case "long":stack[i] = Long.valueOf(vals_[i]);break;
					case "boolean":stack[i] = Boolean.valueOf(vals_[i]);break;
					default:stack[i] = types_[i].cast(vals_[i]);
				}
			}
			return stack;
		} catch (ClassNotFoundException e) {}
		return null;
	}

	public static Object[] params(Object... object) {
		return object;
	}

	public static String[] parameter(String str) {
		return str.split(";");
	}

	public static String[] parameterTypes(String... string) {
		return string;
	}

	public static Object getMember(String index, String field, Object pointer) {
		try {
			return getField(index, field).get(pointer);
		}
		catch (IllegalAccessException e) {}
		return null;
	}

	public static Field getField(String index, String field) {
		Class mClass;
		try {
			if (IHandler.getClassLookupMap().containsKey(index))
				index = IHandler.getClassLookupMap().get(index).getClassName();
			mClass = Class.forName(index);
			return mClass.getField(field);
		}
		catch (NoSuchFieldException | ClassNotFoundException | IllegalArgumentException e) {}
		return null;
	}

	public static Method getMethod(String index, String name, String...parameterTypes) {
		try {
			if (((Object)parameterTypes) != null)
				return Class.forName(index).getMethod(name, toClasses(parameterTypes));
			return Class.forName(index).getMethod(name, null);
		}
		catch (NoSuchMethodException | ClassNotFoundException e) {}
		return null;
	}

	public static Object createObject(String index, String...parameterTypes, Object...params) {
		try {
			return createObject(Class.forName(index), toClasses(parameterTypes), params);
		}
		catch (ClassNotFoundException e) {}
		return null;
	}

	public static Object createObject(Class<?> index, Class<?>...parameterTypes, Object...params) {
		try {
			if (((Object)parameterTypes) != null)
				return index.getConstructor(parameterTypes).newInstance(params);
			return index.getConstructor().newInstance(params);
		}
		catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {}
		return null;
	}

	public static Object CallObjectMethod(String index, String name, Object self, String...parameterTypes, Object...args) {
		try {
			if (((Object)parameterTypes) != null)
				return CallObjectMethod(Class.forName(index), name, self, toClasses(parameterTypes), args);

			return CallObjectMethod(Class.forName(index), name, self, null);
		}
		catch (ClassNotFoundException e) {}
		return null;
	}

	public static Object CallObjectMethod(Class<?> index, String name, Object self, Class<?>...parameterTypes, Object...args) {
		try {
			Method method = index.getMethod(name, parameterTypes);

			if (method != null)
				return method.invoke(self == null? index : self, args);
		}
		catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {}
		return null;
	}

	public static void CallVoidMethod(String index, String name, Object self, String...parameterTypes, Object...args) {
		try {
			if (((Object)parameterTypes) != null)
				CallVoidMethod(Class.forName(index), name, self, toClasses(parameterTypes), args);

			CallVoidMethod(Class.forName(index), name, self, null);
		}
		catch (ClassNotFoundException e) {}
	}

	public static void CallVoidMethod(Class<?> index, String name, Object self, Class<?>...parameterTypes, Object...args) {
		try {
			Method method = index.getMethod(name, parameterTypes);

			if (method != null)
				method.invoke(self == null? index : self, args);
		}
		catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {}
	}

	public static Class<?>[] toClasses(String[] parameterTypes) throws ClassNotFoundException {
		Class<?>[] params = new Class<?>[parameterTypes.length];
		for (int i = 0; i != parameterTypes.length; ++i) {
			switch (parameterTypes[i]) {
//				case "byte":params[i] = BYTE;break;
//				case "char":params[i] = CHAR;break;
				case "short":params[i] = SHORT;break;
				case "int":params[i] = INT;break;
				case "float":params[i] = FLOAT;break;
				case "double":params[i] = DOUBLE;break;
				case "long":params[i] = LONG;break;
				case "boolean":params[i] = BOOLEAN;break;
				default:params[i] = Class.forName(parameterTypes[i]);
			}
		}
		return params;
	}
}
