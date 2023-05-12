package es.uma.mps;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("Fase4")
public class Fase4Test {
    @Test
    public void testRegisterValidCredentials() {
        CredentialStore credentialStore = new CredentialStoreSet();
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = new Email("mps@uma.es");
        PasswordString password = new PasswordString("hello.123;");
        CredentialValidator.ValidationStatus status = credentialValidator.validate(email, password);

        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.VALIDATION_OK, status);
    }

    @Test
    public void testRegisterInvalidEmail() {
        CredentialStore credentialStore = new CredentialStoreSet();
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = new Email("c@h@uma.es");
        PasswordString password = new PasswordString("hello.123;");

        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.EMAIL_INVALID, credentialValidator.validate(email, password));
    }

    @Test
    public void testRegisterInvalidPassword() {
        CredentialStore credentialStore = new CredentialStoreSet();
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = new Email("mps@uma.es");
        PasswordString password = new PasswordString("hello");

        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.PASSWORD_INVALID, credentialValidator.validate(email,
                password));
    }

    @Test
    public void testRegisterExistingCredentials() {
        CredentialStore credentialStore = new CredentialStoreSet();
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = new Email("mps@uma.es");
        PasswordString password = new PasswordString("hello.123;");

        userRegistration.register(email, password);
        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.EXISTING_CREDENTIAL, credentialValidator.validate(email,
                password));
        assertThrows(CredentialExistsException.class, () -> credentialStore.store(email, password));
    }
}
