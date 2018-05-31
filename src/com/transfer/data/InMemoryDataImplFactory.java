package com.transfer.data;

public class InMemoryDataImplFactory {
	private static InMemoryDataImpl inMemoryDataImpl;
	public static InMemoryData getInstance() {
		if(inMemoryDataImpl == null) {
			synchronized (InMemoryDataImplFactory.class) {
				if(inMemoryDataImpl == null) {
					inMemoryDataImpl = new InMemoryDataImpl();
				}
			}
		}
		return inMemoryDataImpl;
	}
}
