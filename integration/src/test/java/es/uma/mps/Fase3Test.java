package es.uma.mps;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("Fase3")
public class Fase3Test {

    @Test
    public void testRegisterValidCredentials() {
        CredentialStore credentialStore = new CredentialStoreSet();
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = mock(Email.class);
        PasswordString password = mock(PasswordString.class);
        when(email.validate()).thenReturn(true);
        when(password.validate()).thenReturn(true);
        CredentialValidator.ValidationStatus status = credentialValidator.validate(email, password);

        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.VALIDATION_OK, status);
    }

    @Test
    public void testRegisterInvalidEmail() {
        CredentialStore credentialStore = new CredentialStoreSet();
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = mock(Email.class);
        PasswordString password = mock(PasswordString.class);
        when(email.validate()).thenReturn(false);
        when(password.validate()).thenReturn(true);

        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.EMAIL_INVALID, credentialValidator.validate(email, password));
    }

    @Test
    public void testRegisterInvalidPassword() {
        CredentialStore credentialStore = new CredentialStoreSet();
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = mock(Email.class);
        PasswordString password = mock(PasswordString.class);
        when(email.validate()).thenReturn(true);
        when(password.validate()).thenReturn(false);

        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.PASSWORD_INVALID, credentialValidator.validate(email,
                password));
    }

    @Test
    public void testRegisterExistingCredentials() {
        CredentialStore credentialStore = new CredentialStoreSet();
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = mock(Email.class);
        PasswordString password = mock(PasswordString.class);
        when(email.validate()).thenReturn(true);
        when(password.validate()).thenReturn(true);

        userRegistration.register(email, password);
        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.EXISTING_CREDENTIAL, credentialValidator.validate(email,
                password));
    }
}
