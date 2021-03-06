/*
 * Copyright 2012-2015, the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trpr.platform.batch.impl.spring.job.ha;

import org.springframework.beans.factory.FactoryBean;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.retry.ExponentialBackoffRetry;

/**
 * The <code>CuratorClientFactory</code> class is a Spring factory bean for creating the Curator {@link CuratorFramework} instance relevant for Trooper.
 * 
 * @author Regunath B
 * @version 1.0, 05 Oct 2012
 */
public class CuratorClientFactory implements FactoryBean<CuratorFramework> {
	
	/** The Trooper namespace identifier in Curator, ZK*/
	private static final String TRPR_NAMESPACE = "Trooper";
	
	/** Convenient defaults for retry policy values*/
	private static final int DEFAULT_RETRY_COUNT = 10;
	private static final int DEFAULT_BASE_SLEEP_MS = 1000;
	
	/** The ZK connect string*/
	private String zkConnectString;
	
	/** Variables for optionally setting retry policy values*/
	private int connectionRetryCount = DEFAULT_RETRY_COUNT;
	private int baseSleepMillis = DEFAULT_BASE_SLEEP_MS;
	
	/**
	 * Interface method implementation. Constructs and returns as instance of {@link CuratorFramework} for Trooper batch runtime.
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	public CuratorFramework getObject() throws Exception {
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.namespace(TRPR_NAMESPACE)
				.retryPolicy(new ExponentialBackoffRetry(this.baseSleepMillis, this.connectionRetryCount))
				.connectString(this.zkConnectString).build();
		client.start();
		return client;
	}

	/**
	 * Interface method implementation. Returns type of {@link CuratorFramework}
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	public Class getObjectType() {
		return CuratorFramework.class;
	}

	/**
	 *  Interface method implementation. Returns true
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	public boolean isSingleton() {
		return true;
	}

	/** Start Getter/Setter methods*/	
	public String getZkConnectString() {
		return this.zkConnectString;
	}
	public void setZkConnectString(String zkConnectString) {
		this.zkConnectString = zkConnectString;
	}
	public int getConnectionRetryCount() {
		return this.connectionRetryCount;
	}
	public void setConnectionRetryCount(int connectionRetryCount) {
		this.connectionRetryCount = connectionRetryCount;
	}
	public int getBaseSleepMillis() {
		return this.baseSleepMillis;
	}
	public void setBaseSleepMillis(int baseSleepMillis) {
		this.baseSleepMillis = baseSleepMillis;
	}
	/** End Getter/Setter methods*/

}
