package com.tuya.smart.config;

public class ClientConfig {
	private int socketTimeout = 50000;
	private int connectionTimeout = 50000;
	private int maxErrorRetry = 3;
	private int maxConnections = 50;

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getSocketTimeout() {
		return this.socketTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getConnectionTimeout() {
		return this.connectionTimeout;
	}

	public void setMaxErrorRetry(int maxErrorRetry) {
		this.maxErrorRetry = maxErrorRetry;
	}

	public int getMaxErrorRetry() {
		return this.maxErrorRetry;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public int getMaxConnections() {
		return this.maxConnections;
	}
}
