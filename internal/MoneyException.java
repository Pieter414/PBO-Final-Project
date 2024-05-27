package PBOFINALPROJECTHURA.internal;

public class MoneyException extends Exception{
    public MoneyException() {
        super("Not enough money");
    }

    public MoneyException(String message) {
        super(message);
    }
}