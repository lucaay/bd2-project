public class MainApplication {

    private boolean isLoggedIn = true;
    public void Start(){
        if (isLoggedIn){
            SignUpPage signUpPage = new SignUpPage();
        }else {
            LogInPage loginPage = new LogInPage();
        }
    }

}
