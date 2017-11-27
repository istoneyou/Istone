package com.stone.njubbs;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class TestService extends Service {
    public ITestAIDLImpl iTestAIDLImpl = new ITestAIDLImpl();
    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iTestAIDLImpl;
    }

    public class ITestAIDLImpl extends ITestAIDL.Stub {
        @Override
        public int printHello() throws RemoteException {
            return Process.myPid();
        }
    }
}
