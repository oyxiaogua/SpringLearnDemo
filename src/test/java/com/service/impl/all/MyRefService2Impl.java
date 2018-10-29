package com.service.impl.all;

import com.service.InterfaceMyRefService1;
import com.service.InterfaceMyRefService2;

public class MyRefService2Impl implements InterfaceMyRefService2 {
	private InterfaceMyRefService1 myRefService1;

	public void printName2() {
		myRefService1.printName();
	}

	public InterfaceMyRefService1 getMyRefService1() {
		return myRefService1;
	}

	public void setMyRefService1(InterfaceMyRefService1 myRefService1) {
		this.myRefService1 = myRefService1;
	}

}
