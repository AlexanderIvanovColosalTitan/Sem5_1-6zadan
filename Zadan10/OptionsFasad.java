public class OptionsFasad {
    private static Options options;

    public OptionsFasad(Account account) {
        this.options = new Options(account);
    }

    public boolean withdraw(double amount) {
        return options.withdraw(amount);
    }

    public double checkBalance() {
        return options.checkBalance();
    }

    public void deposit(double amount) {
        options.deposit(amount);
    }

    public static String getAccountNumber() {
        return options.getAccount().getAccountNumber();
    }
}
