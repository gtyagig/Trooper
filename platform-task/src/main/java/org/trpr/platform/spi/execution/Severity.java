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

package org.trpr.platform.spi.execution;

/**
 * The <code>Severity</code> quantifies the outcome of execution of a unit of work such as a Task or Validation. It is an enumeration
 * that defines possible values for this type.
 * 
 * @author Ashok Ayengar
 * @author Regunath B
 * @version 1.0, 16/05/2012
 */

public enum Severity {

	/**
	 * Supported enumeration values for this type
	 */
	INFO (10),
	WARNING(20),
	ERROR(30),
	FATAL(40);
	
	/** Holds the Severity code*/
	private int code;
	
	/**
	 * Constructor.
	 */
	private Severity(int code) {
		this.code = code;
	}
	
	/**
	 * Returns the Severity code.
	 * Uses of this method is not recommended for comparing or otherwise analyzing execution outcomes.
	 * Use the enumeration types defined instead.
	 * @return int
	 */
	public int getCode() {
		return this.code;
	}
	
}
