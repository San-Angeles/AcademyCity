package nay;

public class Warpper
{
	private final String className;
	private Object pointer = null;
	
	public Warpper(String className) {
		this.className = className;
	}
	
	public Warpper(String className, String...parameterTypes, Object...params) {
		this.className = className;
		this.pointer = Invoke.createObject(className, parameterTypes, params);
	}
	
	public String getClassName() {
		return className;
	}
	
	public <T extends Object> T getPointer() {
		return (T)pointer;
	}
	
	public void CallMethod(String methodName, String...parameterTypes, Object...params) {
		Invoke.CallVoidMethod(className, methodName, pointer, parameterTypes, params);
	}
	
	public <T extends Object> T GetObject(String methodName, String...parameterTypes, Object...params) {
		return (T)Invoke.CallObjectMethod(className, methodName, pointer, parameterTypes, params);
	}
	
	public <T extends Object> T getMember(String index) {
		return (T)Invoke.getMember(className, index, pointer);
	}
}
