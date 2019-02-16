package com.hzh.answer.dialect;

import org.hibernate.dialect.MySQLInnoDBDialect;

public class MySQL5DialectUTF8 extends MySQLInnoDBDialect {
	@Override
	public String getTableTypeString() {
		// TODO Auto-generated method stub
		return "ENGINE=INNODB DEFAULT CHARSET=UTF8";
	}
}
