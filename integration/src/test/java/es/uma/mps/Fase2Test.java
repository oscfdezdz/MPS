package es.uma.mps;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Tag("Fase2")
public class Fase2Test {

    @Test
    public void testRegisterValidCredentials() {
        CredentialStore credentialStore = mock(CredentialStore.class);
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = mock(Email.class);
        PasswordString password = mock(PasswordString.class);
        when(email.validate()).thenReturn(true);
        when(password.validate()).thenReturn(true);
        when(credentialStore.credentialExists(email, password)).thenReturn(false);
        CredentialValidator.ValidationStatus status = credentialValidator.validate(email, password);

        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.VALIDATION_OK, status);
    }

    @Test
    public void testRegisterInvalidEmail() {
        CredentialStore credentialStore = mock(CredentialStore.class);
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = mock(Email.class);
        PasswordString password = mock(PasswordString.class);
        when(email.validate()).thenReturn(false);
        when(password.validate()).thenReturn(true);
        CredentialValidator.ValidationStatus status = credentialValidator.validate(email, password);

        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.EMAIL_INVALID, status);
    }

    @Test
    public void testRegisterInvalidPassword() {
        CredentialStore credentialStore = mock(CredentialStore.class);
        CredentialValidator credentialValidator = new CredentialValidatorImpl(credentialStore);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = mock(Email.class);
        PasswordString password = mock(PasswordString.class);
        when(email.validate()).thenReturn(true);
        when(password.validate()).thenReturn(false);
        CredentialValidator.ValidationStatus status = credentialValidator.validate(email, password);

        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.PASSWORD_INVALID, status);
    }

    @Test
    public void testRegisterExistingCredentials() {
        CredentialStore credentialStore = mock(CredentialStore.class);
        Email email = mock(Email.class);
        PasswordString password = mock(PasswordString.class);
        CredentialStore spy = spy(credentialStore);
        when(spy.credentialExists(email, password)).thenReturn(true);
        CredentialValidator credentialValidator = new CredentialValidatorImpl(spy);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        when(email.validate()).thenReturn(true);
        when(password.validate()).thenReturn(true);
        when(credentialStore.credentialExists(email, password)).thenReturn(false);
        CredentialValidator.ValidationStatus status = credentialValidator.validate(email, password);

        userRegistration.register(email, password);

        assertEquals(CredentialValidator.ValidationStatus.EXISTING_CREDENTIAL, status);
    }
}
