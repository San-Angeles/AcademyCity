package com.sanangeles.academycity.kit;

import com.sanangeles.academycity.*;

public class Group<T extends Object> extends Object
{
	private final Object[] set;

	public Group(T... set) {
		this.set = set;
	}

	public Object get(int index) {
		if (index >= set.length)
			return 0;
		else
			return set[index];
	}

	public int length() {
		return this.set.length;
	}

	@Override
	public String toString() {
		StringBuilder mStringBuilder = new StringBuilder();
		for (int i = 0; i < set.length; ++i) {
			mStringBuilder.append(set[i].toString());
			if (i < set.length-1)
				mStringBuilder.append(", ");
		}
		return mStringBuilder.toString();
	}
	
	public static Group<?> turn(String... set) {
		Object[] values = new Object[set.length];
		for (int i = 0; i < set.length; ++i)
			values[i] = Runner.returnObject(set[i]);
			
		return new Group<Object>(values);
	}
	
	public static Object[] back(Group... groups) {
		int len = 0;
		for (Group e : groups)
			len += e.length();
		Object[] objects = new Object[len];
		for (int i = 0; i < groups.length; ++i)
			for (int e = 0; e < groups[i].length(); ++e)
				objects[i*e] = groups[i].get(e);
		return objects;
	}
}
