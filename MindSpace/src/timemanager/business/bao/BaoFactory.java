package timemanager.business.bao;

public class BaoFactory {
    public SignInImpl getSignIn () {
        return new SignInImpl();
    }
    public SignUpImpl getSignUp() {
        return new SignUpImpl();
    }
}
