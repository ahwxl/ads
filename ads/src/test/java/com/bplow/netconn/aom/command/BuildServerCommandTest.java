/**
 * www.bplow.com
 */
package com.bplow.netconn.aom.command;

import org.junit.Test;


/**
 * @desc 
 * @author wangxiaolei
 * @date 2016年5月28日 下午3:27:24
 */
public class BuildServerCommandTest {
	
	private BuildServerCommand command = new BuildServerCommand();
	
	@Test
	public void test(){
		command.execute();
	}

}
