package stx.pattern;

public class Singleton {
/*    private static Singleton instance;
    
    private Singleton(){
    	System.out.println("Singleton object is instantiated");
    }
    
    public static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    
    public static Singleton getInstanceUsingDoubleLocking(){
        if(instance == null){
            synchronized (Singleton.class) {
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }*/
    
    private static class SingletonHelper{
        //private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    	private static final Singleton INSTANCE = new Singleton();
    }
    
    public static Singleton getBillPughSingletonInstance(){
        return SingletonHelper.INSTANCE;
    }
    
    public static void main(String args[]) {
    	Singleton.getBillPughSingletonInstance();
    }
}
