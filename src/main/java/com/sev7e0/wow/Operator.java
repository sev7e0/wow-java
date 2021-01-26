package com.sev7e0.wow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  Opreator.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-07-12 12:08
 **/

public class Operator {

	/**  
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(Operator.class);

	public static void main(String[] args) {
		LOG.info("Operator");

		int o = 0x0001;//十六进制
		int loveu = 0x0208;//十六进制
		int d = 0x01FF;//十六进制
		int e = 000010;//八进制
		int i = 9;//十进制
		//2 => 0010

		LOG.info("{}",o>>2 );
		LOG.info("{}",o|2 );
		LOG.info("{}",e|2 ); // 0010 | 2 // 八进制 表示8
		LOG.info("{}",i|2 ); // 1001 | 0010
		LOG.info("{}",o&2 );
		LOG.info("{}",loveu );
		LOG.info("{}",d );

	}

}
