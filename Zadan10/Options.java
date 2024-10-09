public class Options {
    private Account account;

    public Options(Account account) {
        this.account = account;
    }

    public boolean withdraw(double amount) {
        return account.withdraw(amount);
    }

    public double checkBalance() {
        return account.getBalance();
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public Account getAccount() {
        return account;
    }
}
