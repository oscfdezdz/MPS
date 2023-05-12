package es.uma.mps;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@Tag("Fase1")
class Fase1Test {

    @Test
    @DisplayName("Checks that valid user registration works")
    void validUserRegistration() {
        CredentialStore credentialStore = mock(CredentialStore.class);
        CredentialValidator credentialValidator = mock(CredentialValidator.class);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = mock(Email.class);
        PasswordString password = mock(PasswordString.class);
        when(credentialValidator.validate(email, password)).thenReturn(CredentialValidator.ValidationStatus.VALIDATION_OK);

        userRegistration.register(email, password);

        verify(credentialStore, times(1)).store(email, password);
    }

    @Test
    @DisplayName("Checks that invalid user registration works")
    void invalidEmailUserRegistration() {
        CredentialValidator credentialValidator = mock(CredentialValidator.class);
        CredentialStore credentialStore = mock(CredentialStore.class);
        UserRegistration userRegistration = new UserRegistration(credentialValidator, credentialStore);
        Email email = mock(Email.class);
        PasswordString password = mock(PasswordString.class);
        when(credentialValidator.validate(email, password)).thenReturn(CredentialValidator.ValidationStatus.EMAIL_INVALID);

        userRegistration.register(email, password);

        verify(credentialStore, never()).store(email, password);
    }
}
