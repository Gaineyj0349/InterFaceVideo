package com.gainwise.interfacevideo;

public class CrashHandler {

    //declare the handler variables
    private Thread.UncaughtExceptionHandler defaultHandler;
    private Crashable method;


    public CrashHandler(Crashable method) {
       // initialize the handler variables
        this.method = method;
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(ourHandler);
        }


        private Thread.UncaughtExceptionHandler ourHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //custom code gets executed here

                method.executeOnCrash();

                // rethrow back to default handler VERY IMPORTANT
                defaultHandler.uncaughtException(t,e);
                }
        };


    interface Crashable{
        void executeOnCrash();
    }












}
