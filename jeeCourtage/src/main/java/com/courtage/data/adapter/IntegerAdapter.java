package com.courtage.data.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class IntegerAdapter extends XmlAdapter<Integer, String> {

	@Override
	public Integer marshal(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return Integer.parseInt(arg0);
	}

	@Override
	public String unmarshal(Integer arg0) throws Exception {
		// TODO Auto-generated method stub
		return arg0.toString();
	}

	 


}
