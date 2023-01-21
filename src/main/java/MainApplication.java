public class MainApplication {

    private boolean isLoggedIn = false;

    public void Start(){
        ApplicationInterface applicationInterface = new ApplicationInterface();
        if (isLoggedIn){
            System.out.println("User is logged in");
        }else {
            applicationInterface.showLogInPage();
            System.out.println("User is not logged in");
        }
    }

}
