package com.cg.baseproject.rx.rxbus.event;

public class RxBusListener {
	public interface IRxBusListener {
		public void onRxBusStateChanged(int state);
	}
}
