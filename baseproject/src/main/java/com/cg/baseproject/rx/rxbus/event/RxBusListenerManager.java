package com.cg.baseproject.rx.rxbus.event;


public class RxBusListenerManager {
	private static RxBusListenerManager mRxBusListenerManager;

	public static RxBusListenerManager getInstance() {
		if (mRxBusListenerManager == null) {
			mRxBusListenerManager = new RxBusListenerManager();
		}
		return mRxBusListenerManager;
	}

    //声明一个注册监听状态的监听器引用
    public   RxBusListener.IRxBusListener mIRxBusListener;
    //回调接口的监听器
    public  void setRxBusListener(RxBusListener.IRxBusListener iRxBusListener){
        mIRxBusListener = iRxBusListener;
    }
    
    public void triggerRxBusListener(int state){
        if(mIRxBusListener != null){
            mIRxBusListener.onRxBusStateChanged(state);
        }
    }
}
