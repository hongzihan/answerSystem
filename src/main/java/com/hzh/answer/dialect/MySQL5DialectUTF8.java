package com.hzh.answer.dialect;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class MySQL5DialectUTF8 extends MySQL5InnoDBDialect {
	@Override
	public String getTableTypeString() {
		// TODO Auto-generated method stub
		return "ENGINE=INNODB DEFAULT CHARSET=UTF8";
	}
}
