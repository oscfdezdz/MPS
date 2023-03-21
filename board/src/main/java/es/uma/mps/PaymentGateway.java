package es.uma.mps;

public interface PaymentGateway {
    boolean advertiserHasFunds(String advertiserName);

    void chargeAdvertiser(String advertiserName);
}
